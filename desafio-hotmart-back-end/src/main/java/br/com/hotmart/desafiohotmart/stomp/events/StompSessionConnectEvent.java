package br.com.hotmart.desafiohotmart.stomp.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import br.com.hotmart.desafiohotmart.entity.Usuario;
import br.com.hotmart.desafiohotmart.service.UsuarioService;
import br.com.hotmart.desafiohotmart.utils.SecurityUtils;
import br.com.hotmart.desafiohotmart.utils.WebSocketUtils;

/**
 * Classe responsável pelo evento de conexão
 * 
 * @author Tiago
 *
 */
@Component
public class StompSessionConnectEvent implements ApplicationListener<SessionConnectEvent> {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public void onApplicationEvent(SessionConnectEvent event) {

		StompHeaderAccessor shaccessor = StompHeaderAccessor.wrap(event.getMessage());

		try {
			
			Usuario usuario = SecurityUtils.getUsuarioByUserPrincipal(shaccessor.getUser());

			WebSocketUtils.connect(shaccessor.getSessionId(), usuario);
			
			usuarioService.updateUserConnected(usuario.getId(), true);
			
		} catch (Exception e) {
			
			LOGGER.error("CAN'T CREATE SESSION ID [ " + shaccessor.getSessionId() + " ]", e.getMessage());
			
		}
		
	}
	
}