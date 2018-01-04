package br.com.hotmart.desafiohotmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotmart.desafiohotmart.entity.ChatMessage;
import br.com.hotmart.desafiohotmart.entity.Usuario;
import br.com.hotmart.desafiohotmart.enumerations.ReturnTypeEnum;
import br.com.hotmart.desafiohotmart.service.ChatService;
import br.com.hotmart.desafiohotmart.service.UsuarioService;
import br.com.hotmart.desafiohotmart.utils.WebSocketUtils;
import br.com.hotmart.desafiohotmart.vo.ChatInfoVO;
import br.com.hotmart.desafiohotmart.vo.ChatMessageVO;
import br.com.hotmart.desafiohotmart.vo.LastChatInfoVO;
import br.com.hotmart.desafiohotmart.vo.MessageVO;
import br.com.hotmart.desafiohotmart.vo.ResponseVO;

/**
 * Classe responsável por conter as regras de chat da aplicação.
 * 
 * @author Tiago
 *
 */
@RestController
@RequestMapping("chat")
public class ChatController {
	
	@Autowired
	private ChatService chatMessageService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private SimpMessageSendingOperations simpMessageSendingOperations;
	
	/**
	 * Responsável por obter as informações de conversas de um determinado usuário.
	 * 
	 * @param idUser
	 * @return
	 */
	@GetMapping("/getChatInfoByIdUser/{idUser}/{idUserActive}")
	public ResponseVO<ChatInfoVO> getChatInfoByIdUser(@PathVariable("idUser") Long idUser,
			@PathVariable("idUserActive") Long idUserActive){
		
		return new ResponseVO<>(ReturnTypeEnum.SUCESSO, chatMessageService.getChatInfoByIdUser(idUser, idUserActive));
		
	}
	
	/**
	 * Responsável por retornar uma lista de mensagens trocadas entre usuários.
	 * 
	 * @param idUsuarioOrigem
	 * @param idUsuarioDestino
	 * @return
	 */
	@GetMapping("/getMensagensAtivasByIdUsuarioOrigemAndUsuarioDestino/{idUsuarioOrigem}/{idUsuarioDestino}")
	public ResponseVO<List<ChatMessageVO>> getMensagensAtivasByIdUsuarioOrigemAndUsuarioDestino(@PathVariable("idUser") Long idUsuarioOrigem,
			@PathVariable("idUserActive") Long idUsuarioDestino){
		
		if(idUsuarioOrigem != null && idUsuarioDestino != null){
			
			return new ResponseVO<>(ReturnTypeEnum.SUCESSO, chatMessageService.findMessagesByUserOrigemAndUserDestino(idUsuarioOrigem, idUsuarioDestino));
			
		}
		
		return new ResponseVO<>(ReturnTypeEnum.ERRO, "O usuário de origem e o usuário de destino são obrigatórios.");
		
	}
	
	@GetMapping("/getLastChatInfo/{idUsuarioOrigem}")
	public ResponseVO<LastChatInfoVO> getLastChatInfo(@PathVariable("idUser") Long idUsuarioOrigem){
		
		return null;
		
	}
	
	/**
	 * Responsável por controlar o envio de mensagens trocadas entre os usuários.
	 * 
	 * @param messageVO
	 * @param message
	 * @param principal
	 * @return
	 */
	@MessageMapping("/chat")
	public void sendMessage(@Payload MessageVO messageVO, Message<?> message){
		
		Usuario usuarioDestino = usuarioService.findById(messageVO.getIdUsuarioDestino());
		
		ChatMessage chatMessage = new ChatMessage(WebSocketUtils.getUsuarioVOBySessionId(StompHeaderAccessor.wrap(message).getSessionId()),
				usuarioDestino, messageVO.getMessage());
		
		chatMessageService.save(chatMessage);
		
		simpMessageSendingOperations.convertAndSendToUser(usuarioDestino.getUsername(), "/chatHotmart", chatMessage.toChatMessageVO());
		
	}

}
