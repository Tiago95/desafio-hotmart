package br.com.hotmart.desafiohotmart.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import br.com.hotmart.desafiohotmart.exceptions.SecurityException;
import br.com.hotmart.desafiohotmart.utils.SecurityUtils;

/**
 * Classe responsável por interceptar uma autenticação com sucesso do Spring security 
 * e assim devolver o padrão JSON esperado.
 * 
 * @author Tiago
 *
 */
@Component
public class AjaxAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AjaxAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
    	
    	try {
        	
			SecurityUtils.sendRetornoAutenticacao(response, HttpServletResponse.SC_OK, null);
			
		} catch (SecurityException e) {
			
			LOGGER.error("Problema ao devolver o objeto de autenticação do usuário.");
			
			response.setStatus(HttpServletResponse.SC_OK);
			
		}
    	
    }
    
}
