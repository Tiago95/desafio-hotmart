package br.com.hotmart.desafiohotmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotmart.desafiohotmart.builder.MessageBuilder;
import br.com.hotmart.desafiohotmart.service.UsuarioService;
import br.com.hotmart.desafiohotmart.vo.ContatosVO;
import br.com.hotmart.desafiohotmart.vo.ResponseVO;

/**
 * Classe responsável por controlar e gerenciar os pedidos de contatos.
 * 
 * @author tiago
 *
 */
@RestController
@RequestMapping("contacts")
public class ContactsController {
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/getAllContactsByIdUser/{idUser}")
	public ResponseVO<List<ContatosVO>> getAllContactsByUser(@PathVariable("idUser") Long idUser){
		
		MessageBuilder<List<ContatosVO>> messageBuilder = new MessageBuilder<>();
		
		if(idUser != null && idUser > 0){
			
			List<ContatosVO> listaContatosVO = usuarioService.getContatosVOByUsuario(idUser);
			
			return messageBuilder.setVoReturn(listaContatosVO).build();
			
		}else{
			
			messageBuilder.addErrorMessage("O parametro id do usuário não pode ser nulo");
			
		}
		
		return messageBuilder.build();
		
	}
	
}
