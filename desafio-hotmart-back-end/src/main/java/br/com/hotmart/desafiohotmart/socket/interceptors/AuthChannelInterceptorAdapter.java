package br.com.hotmart.desafiohotmart.socket.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import br.com.hotmart.desafiohotmart.socket.security.WebSocketAuthenticatorService;

/**
 * Interceptador de conexões de web socket
 * 
 * @author Tiago
 *
 */
@Component
public class AuthChannelInterceptorAdapter extends ChannelInterceptorAdapter{
	
	private static final String USERNAME_HEADER = "login";
	
    private static final String PASSWORD_HEADER = "password";
    
    @Autowired
    private WebSocketAuthenticatorService webSocketAuthenticatorService;
    
    @Override
    public Message<?> preSend(final Message<?> message, final MessageChannel channel) throws AuthenticationException {
    	
        final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT == accessor.getCommand()) {

            final UsernamePasswordAuthenticationToken user = webSocketAuthenticatorService.getAuthenticatedOrFail(accessor.getFirstNativeHeader(USERNAME_HEADER), 
            		accessor.getFirstNativeHeader(PASSWORD_HEADER));

            accessor.setUser(user);
        }
        
        return message;
        
    }
    
}
