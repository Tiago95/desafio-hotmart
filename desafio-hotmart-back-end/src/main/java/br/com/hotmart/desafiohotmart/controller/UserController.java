package br.com.hotmart.desafiohotmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotmart.desafiohotmart.builder.MessageBuilder;
import br.com.hotmart.desafiohotmart.enumerations.StatusSolicitacaoAmizadeEnum;
import br.com.hotmart.desafiohotmart.service.PedidoAmizadeService;
import br.com.hotmart.desafiohotmart.service.UsuarioBloqueadoService;
import br.com.hotmart.desafiohotmart.vo.ResponseVO;

/**
 * Controlador das funções de usuário.
 * 
 * @author Tiago
 *
 */
@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UsuarioBloqueadoService usuarioBloqueadoService;
	
	@Autowired
	private PedidoAmizadeService pedidoAmizadeService;
	
	/**
	 * Responsável por bloquear um usuário.
	 * 
	 * @param idUsuario
	 * @param idUsuarioBloqueado
	 * @return
	 */
	@PostMapping("/bloquear/{idUsuario}/{idUsuarioBloqueado}")
	public ResponseVO<Void> bloquearUsuario(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idUsuarioBloqueado") Long idUsuarioBloqueado){
		
		MessageBuilder<Void> messageBuilder = new MessageBuilder<>();
		
		try{
			
			usuarioBloqueadoService.bloquearUsuario(idUsuario, idUsuarioBloqueado);
			
		}catch(Exception e){
			
			messageBuilder.addErrorMessage(e.getMessage());
			
		}
		
		return messageBuilder.build();
		
	}
	
	/**
	 * Responsável por desbloquear um usuário.
	 * 
	 * @param idUsuario
	 * @param idUsuarioBloqueado
	 * @return
	 */
	@PostMapping("/desbloquear/{idUsuario}/{idUsuarioBloqueado}")
	public ResponseVO<Void> desbloquearUsuario(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idUsuarioBloqueado") Long idUsuarioBloqueado){
		
		MessageBuilder<Void> messageBuilder = new MessageBuilder<>();
		
		try{
			
			usuarioBloqueadoService.desbloquearUsuario(idUsuario, idUsuarioBloqueado);
			
		}catch(Exception e){
			
			messageBuilder.addErrorMessage(e.getMessage());
			
		}
		
		return messageBuilder.build();
		
	}
	
	/**
	 * Responsável por solicitar um pedido de amizade.
	 * 
	 * @param idUsuario
	 * @param idUsuarioAmigo
	 * @return
	 */
	@PostMapping("/solicitarAmizade/{idUsuario}/{idUsuarioAmigo}")
	public ResponseVO<Void> solicitarAmizade(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idUsuarioAmigo") Long idUsuarioAmigo){
		
		MessageBuilder<Void> messageBuilder = new MessageBuilder<>();
		
		try{
			
			pedidoAmizadeService.solicitarPedidoAmizade(idUsuario, idUsuarioAmigo);
			
		}catch(Exception e){
			
			messageBuilder.addErrorMessage(e.getMessage());
			
		}
		
		return messageBuilder.build();
		
	}
	
	/**
	 * Responsável por atualizar um pedido de amizade.
	 * 
	 * @param idUsuario
	 * @param idUsuarioAmigo
	 * @param statusSolicitacaoAmizade
	 * @return
	 */
	@PostMapping("/atualizarPedidoAmizade/{idUsuario}/{idUsuarioAmigo}/{statusSolicitacaoAmizade}")
	public ResponseVO<Void> solicitarAmizade(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idUsuarioAmigo") Long idUsuarioAmigo,
			@PathVariable("statusSolicitacaoAmizade") StatusSolicitacaoAmizadeEnum statusSolicitacaoAmizade){
		
		MessageBuilder<Void> messageBuilder = new MessageBuilder<>();
		
		try{
			
			pedidoAmizadeService.atualizarPedidoAmizade(idUsuario, idUsuarioAmigo, statusSolicitacaoAmizade);
			
		}catch(Exception e){
			
			messageBuilder.addErrorMessage(e.getMessage());
			
		}
		
		return messageBuilder.build();
		
	}

}
