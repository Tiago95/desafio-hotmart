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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotmart.desafiohotmart.entity.ChatMessage;
import br.com.hotmart.desafiohotmart.entity.Usuario;
import br.com.hotmart.desafiohotmart.enumerations.ReturnTypeEnum;
import br.com.hotmart.desafiohotmart.service.ChatService;
import br.com.hotmart.desafiohotmart.service.UsuarioBloqueadoService;
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
	
	@Autowired
	private UsuarioBloqueadoService usuarioBloqueadoService;
	
	/**
	 * Responsável por obter as informações de conversas de um determinado usuário.
	 * 
	 * @param idUser
	 * @return
	 */
	@GetMapping({"/getChatInfoByIdUser/{idUser}/{idUserActive}", "/getChatInfoByIdUser/{idUser}"})
	public ResponseVO<ChatInfoVO> getChatInfoByIdUser(@PathVariable("idUser") Long idUser,
			@PathVariable(name = "idUserActive", required = false) Long idUserActive){
		
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
	public ResponseVO<List<ChatMessageVO>> getMensagensAtivasByIdUsuarioOrigemAndUsuarioDestino(@PathVariable("idUsuarioOrigem") Long idUsuarioOrigem,
			@PathVariable("idUsuarioDestino") Long idUsuarioDestino){
		
		if(idUsuarioOrigem != null && idUsuarioDestino != null){
			
			return new ResponseVO<>(ReturnTypeEnum.SUCESSO, chatMessageService.findMessagesByUserOrigemAndUserDestino(idUsuarioOrigem, idUsuarioDestino));
			
		}
		
		return new ResponseVO<>(ReturnTypeEnum.ERRO, "O usuário de origem e o usuário de destino são obrigatórios.");
		
	}
	
	/**
	 * Responsável por obter as últimas informações do chat.
	 * 
	 * @param idUsuarioOrigem
	 * @return
	 */
	@GetMapping("/getLastChatInfo/{idUsuarioOrigem}")
	public ResponseVO<LastChatInfoVO> getLastChatInfo(@PathVariable("idUsuarioOrigem") Long idUsuarioOrigem){
		
		if(idUsuarioOrigem != null){
			
			return new ResponseVO<>(ReturnTypeEnum.SUCESSO, chatMessageService.getLastChatInfo(idUsuarioOrigem));
			
		}
		
		return new ResponseVO<>(ReturnTypeEnum.ERRO, "O usuário de origem é obrigatório.");
		
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
		
		Usuario usuarioOrigem = WebSocketUtils.getUsuarioVOBySessionId(StompHeaderAccessor.wrap(message).getSessionId());
		
		if(usuarioBloqueadoService.countByUsuarioPrincipalAndUsuarioBloqueado(usuarioDestino, usuarioOrigem) <= 0){
			
			ChatMessage chatMessage = new ChatMessage(usuarioOrigem, usuarioDestino, messageVO.getMessage());
			
			chatMessageService.save(chatMessage);
			
			simpMessageSendingOperations.convertAndSendToUser(usuarioDestino.getUsername(), "/chatHotmart", chatMessage.toChatMessageVO());
			
		}		
		
	}
	
	/**
	 * Responsável por atualizar o estado de recebida da mensagem
	 * 
	 * @param idsChatMensagem
	 * @return
	 */
	@PostMapping("/atualizarMensagensRecebida")
	public ResponseVO<Void> atualizarMensagensRecebida(@RequestBody List<Long> idsChatMensagem){
		
		if(idsChatMensagem != null && !idsChatMensagem.isEmpty()){
			
			chatMessageService.atualizarMensagemRecebida(idsChatMensagem);
			
			return new ResponseVO<>();
			
		}
		
		return new ResponseVO<>(ReturnTypeEnum.ERRO, "O id da mensagem é obrigatório.");
		
	}
	
	/**
	 * Responsável por atualizar o estado de lida da mensagem
	 * 
	 * @param idChatMensagem
	 * @return
	 */
	@PostMapping("/atualizarMensagemRecebida/{idChatMensagem}")
	public ResponseVO<Void> atualizarMensagemRecebida(@PathVariable("idChatMensagem") Long idChatMensagem){
		
		if(idChatMensagem != null){
			
			chatMessageService.atualizarMensagemRecebida(idChatMensagem);
			
			return new ResponseVO<>();
			
		}
		
		return new ResponseVO<>(ReturnTypeEnum.ERRO, "O id da mensagem é obrigatório.");
		
	}
	
	/**
	 * Responsável por atualizar o estado de recebida da mensagem
	 * 
	 * @param idsChatMensagem
	 * @return
	 */
	@PostMapping("/atualizarMensagensLida")
	public ResponseVO<Void> atualizarMensagensLida(@RequestBody List<Long> idsChatMensagem){
		
		if(idsChatMensagem != null && !idsChatMensagem.isEmpty()){
			
			chatMessageService.atualizarMensagemLida(idsChatMensagem);
			
			return new ResponseVO<>();
			
		}
		
		return new ResponseVO<>(ReturnTypeEnum.ERRO, "O id da mensagem é obrigatório.");
		
	}
	
	/**
	 * Responsável por atualizar o estado de recebida da mensagem
	 * 
	 * @param idChatMensagem
	 * @return
	 */
	@PostMapping("/atualizarMensagemLida/{idChatMensagem}")
	public ResponseVO<Void> atualizarMensagemLida(@PathVariable("idChatMensagem") Long idChatMensagem){
		
		if(idChatMensagem != null){
			
			chatMessageService.atualizarMensagemLida(idChatMensagem);
			
			return new ResponseVO<>();
			
		}
		
		return new ResponseVO<>(ReturnTypeEnum.ERRO, "O id da mensagem é obrigatório.");
		
	}

}
