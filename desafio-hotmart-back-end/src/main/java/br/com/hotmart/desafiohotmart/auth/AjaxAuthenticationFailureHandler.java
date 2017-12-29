package br.com.hotmart.desafiohotmart.auth;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import br.com.hotmart.desafiohotmart.exceptions.SecurityException;
import br.com.hotmart.desafiohotmart.utils.SecurityUtils;

/**
 * Returns a 401 error code (Unauthorized) to the client, when Ajax authentication fails.
 */
@Component
public class AjaxAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AjaxAuthenticationFailureHandler.class);
	
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        try {
        	
			SecurityUtils.sendRetornoAutenticacao(response, HttpServletResponse.SC_UNAUTHORIZED, exception);
			
		} catch (SecurityException e) {
			
			LOGGER.error("Problema ao devolver o objeto de autenticação do usuário.");
			
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Falha na autenticação");
			
		}
        
    }
    
}
