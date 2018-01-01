package br.com.hotmart.desafiohotmart.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotmart.desafiohotmart.entity.ChatMessage;
import br.com.hotmart.desafiohotmart.service.ChatMessageService;
import br.com.hotmart.desafiohotmart.utils.SecurityUtils;
import br.com.hotmart.desafiohotmart.utils.WebSocketUtils;
import br.com.hotmart.desafiohotmart.vo.ChatMessageVO;
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
	
	/**
	 * Responsável por controlar o envio de mensagens trocadas entre os usuários.
	 * 
	 * @param messageVO
	 * @param message
	 * @param principal
	 * @return
	 */
	@MessageMapping("/chat")
	@SendToUser("/chatHotmart")
	public ChatMessageVO sendMessage(@Payload MessageVO messageVO,
			Message<?> message, Principal principal){
		
		ChatMessage chatMessage = new ChatMessage(WebSocketUtils.getUsuarioVOBySessionId(StompHeaderAccessor.wrap(message).getSessionId()).toUsuario(),
				SecurityUtils.getUsuarioVOByUserPrincipal(principal).toUsuario(), messageVO.getMessage());
		
		chatMessageService.save(chatMessage);
		
		return chatMessage.toChatMessageVO();
		
	}

}
