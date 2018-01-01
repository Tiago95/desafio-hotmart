package br.com.hotmart.desafiohotmart.utils;

import java.security.Principal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.com.hotmart.desafiohotmart.vo.UsuarioVO;

/**
 * Classe utilitária responsável por conter métodos utilitário para web socket
 * 
 * @author Tiago
 *
 */
public class WebSocketUtils {
	
	private static final Map<String, UsuarioVO> sessionMap = new ConcurrentHashMap<>();
	
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
	 * @param usuarioVO
	 */
	public static void connect(String sessionId, UsuarioVO usuarioVO) {

		sessionMap.put(sessionId, usuarioVO);
		
	}
	
	/**
	 * Responsável por registrar uma nova conexão.
	 * 
	 * @param sessionId
	 * @param principal
	 */
	public static void connect(String sessionId, Principal principal) {

		connect(sessionId, SecurityUtils.getUsuarioVOByUserPrincipal(principal));
		
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
	public static UsuarioVO getUsuarioVOBySessionId(String sessionId){
		
		if(sessionMap.containsKey(sessionId)){
			
			return sessionMap.get(sessionId);
			
		}
		
		return null;
		
	}

}
