package br.com.hotmart.desafiohotmart.socket.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import br.com.hotmart.desafiohotmart.socket.interceptors.AuthChannelInterceptorAdapter;

/**
 * Configurações de message broker
 * 
 * @author Tiago
 *
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class WebSocketAuthenticationMessageBrokerConfig extends AbstractWebSocketMessageBrokerConfigurer {
	
	@Autowired
    private AuthChannelInterceptorAdapter authChannelInterceptorAdapter;

	/**
	 * Esse método já foi definido em WebSocketConfig, por esse motivo não preencho nada aki.
	 * 
	 */
    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
       
    }

    @Override
    public void configureClientInboundChannel(final ChannelRegistration registration) {
        registration.interceptors(authChannelInterceptorAdapter);
    }

}
