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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
	@ApiOperation(value = "Bloqueio de usuário", nickname = "bloquearUsuario", notes="Responsável por bloquear um usuário.")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "idUsuario", value = "Id do usuário solicitante", required = true, dataType = "Long", paramType = "path"),
        @ApiImplicitParam(name = "idUsuarioBloqueado", value = "Id do usuário que está sendo bloqueado", required = true, dataType = "Long", paramType = "path")
    })
	@ApiResponses({ 
	    @ApiResponse(code = 200, message = "Success", response = ResponseVO.class)
	})
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
	@ApiOperation(value = "Desbloqueio de usuário", nickname = "desbloquearUsuario", notes="Responsável por desbloquear um usuário.")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "idUsuario", value = "Id do usuário solicitante", required = true, dataType = "Long", paramType = "path"),
        @ApiImplicitParam(name = "idUsuarioBloqueado", value = "Id do usuário que está sendo desbloqueado", required = true, dataType = "Long", paramType = "path")
    })
	@ApiResponses({ 
	    @ApiResponse(code = 200, message = "Success", response = ResponseVO.class)
	})
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
	@ApiOperation(value = "Solicitação de amizade", nickname = "solicitarAmizade", notes="Responsável por solicitar um pedido de amizade.")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "idUsuario", value = "Id do usuário solicitante", required = true, dataType = "Long", paramType = "path"),
        @ApiImplicitParam(name = "idUsuarioAmigo", value = "Id do usuário que está recebendo o pedido de amizade", required = true, dataType = "Long", paramType = "path")
    })
	@ApiResponses({ 
	    @ApiResponse(code = 200, message = "Success", response = ResponseVO.class)
	})
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
	@ApiOperation(value = "Atualização do pedido de amizade", nickname = "atualizarPedidoAmizade", notes="Responsável por atualizar um pedido de amizade.(ACEITO ou RECUSADO)")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "idUsuario", value = "Id do usuário solicitante", required = true, dataType = "Long", paramType = "path"),
        @ApiImplicitParam(name = "idUsuarioAmigo", value = "Id do usuário que está alterando o pedido de amizade", required = true, dataType = "Long", paramType = "path"),
        @ApiImplicitParam(name = "statusSolicitacaoAmizade", value = "Status da solicitação de amizade (ACEITO / RECUSADO)", required = true, dataType = "StatusSolicitacaoAmizadeEnum", paramType = "path")
    })
	@ApiResponses({ 
	    @ApiResponse(code = 200, message = "Success", response = ResponseVO.class)
	})
	@PostMapping("/atualizarPedidoAmizade/{idUsuario}/{idUsuarioAmigo}/{statusSolicitacaoAmizade}")
	public ResponseVO<Void> atualizarPedidoAmizade(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idUsuarioAmigo") Long idUsuarioAmigo,
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
	@ApiOperation(value = "Obtenção de usuário", nickname = "findById", notes="Responsável por buscar um usuário de acordo com um id.")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "idUsuario", value = "Id do usuário que deverá ser carregado", required = true, dataType = "Long", paramType = "path")
    })
	@ApiResponses({ 
	    @ApiResponse(code = 200, message = "Success", response = ResponseVO.class)
	})
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
	
	/**
	 * Responsável por alterar o ststaus de conectado do usuário.
	 * 
	 * @param idUsuario
	 * @param conectado
	 * @return
	 */
	@ApiOperation(value = "Atualização status conectado de usuário", nickname = "atualizarStatusConectadoUsuario", notes="Responsável por alterar o ststaus de conectado do usuário.")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "idUsuario", value = "Id do usuário que deverá ser atualizado", required = true, dataType = "Long", paramType = "path"),
        @ApiImplicitParam(name = "conectado", value = "Status da conxão (conectado / desconectado)", required = true, dataType = "boolean", paramType = "path")
    })
	@ApiResponses({ 
	    @ApiResponse(code = 200, message = "Success", response = ResponseVO.class)
	})
	@PostMapping("/atualizarStatusConectadoUsuario/{idUsuario}/{conectado}")
	public ResponseVO<Void> atualizarStatusConectadoUsuario(@PathVariable("idUsuario") Long idUsuario,
			@PathVariable("conectado") boolean conectado) {

		usuarioService.updateUserConnected(idUsuario, conectado);

		return new ResponseVO<>();
	}

}
