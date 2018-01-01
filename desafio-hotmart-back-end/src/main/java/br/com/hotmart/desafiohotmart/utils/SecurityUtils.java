package br.com.hotmart.desafiohotmart.utils;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.com.hotmart.desafiohotmart.entity.Usuario;
import br.com.hotmart.desafiohotmart.vo.UsuarioVO;

/**
 * Classe utilitária responsável por conter os métodos
 * uteis relativos a segurança.
 * 
 * @author Tiago
 *
 */
public class SecurityUtils {
	
	/**
	 * Construtor default privado por ser uma classe utilitária
	 * 
	 */
	public SecurityUtils() {
		super();
	}
	
	/**
	 * Responsável por retornar um usuário vo de acordo com um usuário logado (Principal)
	 * 
	 * @param userPrincipal
	 * @return
	 */
	public static UsuarioVO getUsuarioVOByUserPrincipal(Principal userPrincipal){
		
		if(userPrincipal != null && userPrincipal instanceof UsernamePasswordAuthenticationToken
				&& ((UsernamePasswordAuthenticationToken) userPrincipal).getPrincipal() instanceof Usuario){
			
		  return ((Usuario)((UsernamePasswordAuthenticationToken) userPrincipal).getPrincipal()).toUsuarioVO(); 
			
		}
		
		return null;
		
	}

}
