package br.com.hotmart.desafiohotmart.socket.security;

import java.io.UnsupportedEncodingException;
import java.util.Collections;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.hotmart.desafiohotmart.entity.Usuario;
import br.com.hotmart.desafiohotmart.service.UsuarioService;

/**
 * Responsável por realizar a autenticação da comunicação do web socket.
 * 
 * @author Tiago
 *
 */
@Component
public class WebSocketAuthenticatorService {
	
	@Autowired
	private UsuarioService usuarioService;	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * Responsável por obter a autenticação do usuário em uma comunicação do web socket.
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws AuthenticationException
	 */
	public UsernamePasswordAuthenticationToken getAuthenticatedOrFail(final String  username, final String password) {
	        
		Usuario usuario = validateCredentials(username, password);        

        return new UsernamePasswordAuthenticationToken(
        		usuario,
                null,
                Collections.singleton((GrantedAuthority) () -> "USER")
        );
        
	}

	/**
	 * Responsável por validar as credenciais recebidas como parametro.
	 *  
	 * @param username
	 * @param password
	 */
	private Usuario validateCredentials(final String username, final String password) {
		
		validateCredentialsInputParameters(username, password);
		
		return validateCredentialsByUser(username, password);
		
	}

	/**
	 * Responsável por validar as credenciais do usuário de acordo com o usuário
	 * cadastrado na base de dados.
	 * 
	 * @param username
	 * @param password
	 */
	private Usuario validateCredentialsByUser(final String username, final String password) {
		
		Usuario usuario = usuarioService.findByLogin(username);
		
		if(usuario == null){
			
			throw new UsernameNotFoundException("Usuário não encontrado");
			
		}
		
        if (!passwordEncoder.matches(getDecodePasswordUser(username, password), usuario.getPassword())) {
        	
            throw new BadCredentialsException("Senha inválida para " + username);
            
        }
        
        return usuario;
        
	}
	
	/**
	 * Responsável por validar as credenciais recebidas como parametro.
	 * 
	 * @param username
	 * @param password
	 */
	private void validateCredentialsInputParameters(final String username, final String password) {
		
		if (StringUtils.isBlank(username)) {
			
		    throw new AuthenticationCredentialsNotFoundException("Usuário não informado.");
		    
		}
		
		if (StringUtils.isBlank(password)) {
			
		    throw new AuthenticationCredentialsNotFoundException("Senha não informada.");
		    
		}
		
	}
	
	/**
	 * Responsável por obter a senha do usuário decodificada.
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	private String getDecodePasswordUser(final String username, final String password){
		
		if(Base64.isBase64(password)){			
			
			try {
				
				return new String(Base64.decodeBase64(password), "UTF-8");
				
			} catch (UnsupportedEncodingException e) {
	
				throw new BadCredentialsException("Não foi possível decodificar a senha do usuário: " + username);
				
			}
			
		}
		
		return password;
		
	}

}
