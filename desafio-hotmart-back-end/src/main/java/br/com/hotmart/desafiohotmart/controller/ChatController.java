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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
	@ApiOperation(value = "Obtenção de informações do chat", nickname = "getChatInfoByIdUser", notes="Responsável por obter todos as informações do chat. Informações como os usuários que játrocaram mensagens e as mensagens")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "idUser", value = "Id do usuário solicitante", required = true, dataType = "Long", paramType = "path"),
        @ApiImplicitParam(name = "idUserActive", value = "Id do usuário ativo no chat, ou seja, id do usuário que está conversando com o usuário solicitante", required = false, dataType = "Long", paramType = "path")
    })
	@ApiResponses({ 
	      @ApiResponse(code = 200, message = "Success", response = ResponseVO.class)
	})
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
	@ApiOperation(value = "Obtenção de mensagens trocados por usuários", nickname = "getMensagensAtivasByIdUsuarioOrigemAndUsuarioDestino", notes="Responsável por obter todos as conversas que foram trocadas entre determinados usuários.")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "idUsuarioOrigem", value = "Id do usuário de origem da mensagem, ou seja, o usuário que mandou a mensagens", required = true, dataType = "Long", paramType = "path"),
        @ApiImplicitParam(name = "idUsuarioDestino", value = "Id do usuário de destino da mensagem, ou seja, o usuário que recebeu a mensagens", required = true, dataType = "Long", paramType = "path")
    })
	@ApiResponses({ 
	      @ApiResponse(code = 200, message = "Success", response = ResponseVO.class)
	})
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
	@ApiOperation(value = "Obtenção das últimas informações do chat", nickname = "getLastChatInfo", notes="Responsável por obter as últimas informações do chat, como quantidade de mensagens não lidas, últimas mensagens, entre outros.")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "idUsuarioOrigem", value = "Id do usuário de origem da mensagem, ou seja, o usuário que mandou a mensagens", required = true, dataType = "Long", paramType = "path")
    })
	@ApiResponses({ 
	      @ApiResponse(code = 200, message = "Success", response = ResponseVO.class)
	})
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
	@ApiOperation(value = "Envio mensagens de chat", nickname = "sendMessage", notes="Responsável por interceptar todas as mensagens trocadas pelos usuários.")
	@ApiResponses({ 
	    @ApiResponse(code = 200, message = "Success", response = Void.class)
	})
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
	@ApiOperation(value = "Atualização mensagens recebidas", nickname = "atualizarMensagensRecebida", notes="Responsável por atualizar o status de recebida de algumas determinadas mensagens.")
	@ApiResponses({ 
	    @ApiResponse(code = 200, message = "Success", response = ResponseVO.class)
	})
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
	@ApiOperation(value = "Atualização mensagem recebida", nickname = "atualizarMensagemRecebida", notes="Responsável por atualizar o status de recebida de uma determinada mensagem.")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "idChatMensagem", value = "Id da mensagem", required = true, dataType = "Long", paramType = "path")
    })
	@ApiResponses({ 
	    @ApiResponse(code = 200, message = "Success", response = ResponseVO.class)
	})
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
	@ApiOperation(value = "Atualização mensagens lidas", nickname = "atualizarMensagensLida", notes="Responsável por atualizar o status de lida de algumas determinadas mensagens.")
	@ApiResponses({ 
	    @ApiResponse(code = 200, message = "Success", response = ResponseVO.class)
	})
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
	@ApiOperation(value = "Atualização mensagem lida", nickname = "atualizarMensagemLida", notes="Responsável por atualizar o status de lida de uma determinada mensagem.")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "idChatMensagem", value = "Id da mensagem", required = true, dataType = "Long", paramType = "path")
    })
	@ApiResponses({ 
	    @ApiResponse(code = 200, message = "Success", response = ResponseVO.class)
	})
	@PostMapping("/atualizarMensagemLida/{idChatMensagem}")
	public ResponseVO<Void> atualizarMensagemLida(@PathVariable("idChatMensagem") Long idChatMensagem){
		
		if(idChatMensagem != null){
			
			chatMessageService.atualizarMensagemLida(idChatMensagem);
			
			return new ResponseVO<>();
			
		}
		
		return new ResponseVO<>(ReturnTypeEnum.ERRO, "O id da mensagem é obrigatório.");
		
	}

}
