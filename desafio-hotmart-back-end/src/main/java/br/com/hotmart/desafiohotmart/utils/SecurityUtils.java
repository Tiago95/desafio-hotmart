package br.com.hotmart.desafiohotmart.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.hotmart.desafiohotmart.enumerations.ReturnTypeEnum;
import br.com.hotmart.desafiohotmart.enumerations.TipoRetornoAutenticacaoEnum;
import br.com.hotmart.desafiohotmart.vo.FieldInfoVO;
import br.com.hotmart.desafiohotmart.vo.ResponseVO;
import br.com.hotmart.desafiohotmart.exceptions.SecurityException;

/**
 * Classe utilitária com os métodos de segurança.
 * 
 * @author Tiago
 *
 */
public final class SecurityUtils {
	
    private static final String NAO_FOI_POSSIVEL_GRAVAR_ARQUIVO_RETORNO_AUTENTICACAO = "Não foi possível gravar o arquivo de retorno da autenticação do usuário";

	private static final ObjectMapper mapper = new ObjectMapper();
    
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtils.class);

    /**
     * Construtor privado por se tratar
     * de uma classe utilitária
     * 
     */
    private SecurityUtils() {
    	
    	super();
    	
    }
	
    /**
     * Método responsável por retornar um objeto 
     * Retorno autenticação após a autenticação
     * de um usuário.
     * 
     * @param response
     * @param status
     * @param exception
     * @throws IOException
     * @throws SecurityException
     */
    public static void sendRetornoAutenticacao(HttpServletResponse response, int status, Exception exception) throws IOException, SecurityException {
    	
        configurarResponseRetornoAutenticacao(response, status, exception);        
        
    }

    /**
     * Metodo responsável por configurar o object
     * response da autenticação.
     * 
     * @param response
     * @param status
     * @param exception
     * @throws JsonProcessingException
     * @throws IOException
     * @throws SecurityException
     */
	private static void configurarResponseRetornoAutenticacao(HttpServletResponse response, int status, Exception exception) throws JsonProcessingException, IOException, SecurityException {
		
		montarHeaderResponse(response, status);

        montarArquivoRetornoAutenticacao(response, status, exception);
        
	}

	/**
	 * Método responsável por montar os Headers
	 * do response da autenticação.
	 * 
	 * @param response
	 * @param status
	 */
	private static void montarHeaderResponse(HttpServletResponse response, int status) {
		
		response.setContentType("application/json;charset=UTF-8");
        response.setStatus(status);
        
	}

	/**
	 * Método responsável por escrever o arquivo
	 * de retorno no response da autenticação 
	 * do usuário.
	 * 
	 * @param response
	 * @param status
	 * @param exception
	 * @throws IOException
	 * @throws JsonProcessingException
	 * @throws SecurityException
	 */
	private static void montarArquivoRetornoAutenticacao(HttpServletResponse response, int status, Exception exception)
			throws IOException, JsonProcessingException, SecurityException {
		
		PrintWriter writer = response.getWriter();
		
		try{
			
			TipoRetornoAutenticacaoEnum tipoRetornoAutenticacaoEnum = TipoRetornoAutenticacaoEnum.getTipoRetornoByException(exception);

			writer.write(mapper.writeValueAsString(new ResponseVO<>(ReturnTypeEnum.ERRO, 
					new FieldInfoVO(tipoRetornoAutenticacaoEnum.getMensagemRetorno(), tipoRetornoAutenticacaoEnum.getAlvo(), ReturnTypeEnum.ERRO))));
	     		
		}catch (Exception e) {

			LOGGER.error(NAO_FOI_POSSIVEL_GRAVAR_ARQUIVO_RETORNO_AUTENTICACAO, e);

			throw new SecurityException(NAO_FOI_POSSIVEL_GRAVAR_ARQUIVO_RETORNO_AUTENTICACAO, e);
			
		}finally {
			
			writer.flush();
			writer.close();
			
		}     
        
	}

}
