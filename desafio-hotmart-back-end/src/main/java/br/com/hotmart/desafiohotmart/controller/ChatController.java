package br.com.hotmart.desafiohotmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotmart.desafiohotmart.entity.ChatMessage;
import br.com.hotmart.desafiohotmart.entity.Usuario;
import br.com.hotmart.desafiohotmart.service.ChatMessageService;
import br.com.hotmart.desafiohotmart.service.UsuarioService;
import br.com.hotmart.desafiohotmart.utils.WebSocketUtils;
import br.com.hotmart.desafiohotmart.vo.MessageVO;

/**
 * Classe responsável por conter as regras de chat da aplicação.
 * 
 * @author Tiago
 *
 */
@RestController
public class ChatController {
	
	@Autowired
	private ChatMessageService chatMessageService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private SimpMessageSendingOperations simpMessageSendingOperations;
	
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
