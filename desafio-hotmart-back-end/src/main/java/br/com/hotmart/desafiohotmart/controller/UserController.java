package br.com.hotmart.desafiohotmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotmart.desafiohotmart.builder.MessageBuilder;
import br.com.hotmart.desafiohotmart.entity.Usuario;
import br.com.hotmart.desafiohotmart.enumerations.ReturnTypeEnum;
import br.com.hotmart.desafiohotmart.enumerations.StatusSolicitacaoAmizadeEnum;
import br.com.hotmart.desafiohotmart.service.PedidoAmizadeService;
import br.com.hotmart.desafiohotmart.service.UsuarioBloqueadoService;
import br.com.hotmart.desafiohotmart.service.UsuarioService;
import br.com.hotmart.desafiohotmart.vo.ResponseVO;
import br.com.hotmart.desafiohotmart.vo.UsuarioVO;

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
	private UsuarioService usuarioService;
	
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
	
	/**
	 * Responsável por buscar um usuário de acordo com um id.
	 * 
	 * @param idUsuario
	 * @return
	 */
	@GetMapping("/findById/{idUsuario}")
	public ResponseVO<UsuarioVO> findById(@PathVariable("idUsuario") Long idUsuario){
		
		if(idUsuario != null){
			
			Usuario usuario = usuarioService.findById(idUsuario);
			
			if(usuario != null){
				
				return new ResponseVO<UsuarioVO>(ReturnTypeEnum.SUCESSO, usuario.toUsuarioVO());
				
			}
			
			return new ResponseVO<UsuarioVO>(ReturnTypeEnum.SUCESSO);
			
		}
		
		return new ResponseVO<UsuarioVO>(ReturnTypeEnum.ERRO, "O parametro id do usuário é obrigatório.");
		
	}

}
