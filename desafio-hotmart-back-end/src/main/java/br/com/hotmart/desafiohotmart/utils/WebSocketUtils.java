package br.com.hotmart.desafiohotmart.utils;

import java.security.Principal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.com.hotmart.desafiohotmart.entity.Usuario;

/**
 * Classe utilitária responsável por conter métodos utilitário para web socket
 * 
 * @author Tiago
 *
 */
public class WebSocketUtils {
	
	private static final Map<String, Usuario> sessionMap = new ConcurrentHashMap<>();
	
	/**
	 * Construtor privado para a classe utilitária.
	 * 
	 */
	private WebSocketUtils() {
		super();
	}
	
	/**
	 * Responsável por registrar uma nova conexão.
	 * 
	 * @param sessionId
	 * @param 
	 */
	public static void connect(String sessionId, Usuario usuario) {

		sessionMap.put(sessionId, usuario);
		
	}
	
	/**
	 * Responsável por registrar uma nova conexão.
	 * 
	 * @param sessionId
	 * @param principal
	 */
	public static void connect(String sessionId, Principal principal) {

		connect(sessionId, SecurityUtils.getUsuarioByUserPrincipal(principal));
		
	}
	
	/**
	 * Responsávl por desconectar uma conexão.
	 * 
	 * @param sessionId
	 */
	public static void disconnect(String sessionId) {

		sessionMap.remove(sessionId);
		
	}
	
	/**
	 * Responsável por retornar um usuárioVO de acordo com a session id.
	 * 
	 * @param sessionId
	 * @return
	 */
	public static Usuario getUsuarioVOBySessionId(String sessionId){
		
		if(sessionMap.containsKey(sessionId)){
			
			return sessionMap.get(sessionId);
			
		}
		
		return null;
		
	}

}
