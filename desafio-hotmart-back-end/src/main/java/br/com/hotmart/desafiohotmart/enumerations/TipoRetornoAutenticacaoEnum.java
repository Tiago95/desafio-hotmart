package br.com.hotmart.desafiohotmart.enumerations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.hotmart.desafiohotmart.utils.SecurityUtils;

/**
 * Enumeração com os tipos de retorno
 * de uma autenticação.
 * 
 * @author Tiago
 *
 */
public enum TipoRetornoAutenticacaoEnum {
	
	SUCESSO(null, "Usuário Autenticado Com Sucesso", "username"), 
	USUARIO_NAO_ENCONTRADO(UsernameNotFoundException.class, "Usuário não econtrado", "username"),
	SENHA_INVALIDA(BadCredentialsException.class, "Senha inválida", "password"), 
	CONTA_EXPIRADA(AccountExpiredException.class, "Conta expirada", "username"), 
	BLOQUEADO(LockedException.class, "Usuário bloqueado", "username"), 
	SENHA_EXPIRADA(CredentialsExpiredException.class, "Senha expirada", "password"),
	USUARIO_INATIVO(DisabledException.class, "Usuário Inativo", "username");
	
	private static final String NAO_FOI_ENCONTRADO_NENHUM_TIPO_RETORNO_AUTENTICACAO_PARA_A_CLASSE_DE_EXCECAO = "Não foi encontrado nenhum TipoRetornoAutenticacaoEnum para a classe de exceção: ";

	private Class<?> classeExcecao;
	
	private String mensagemRetorno;
	
	private String alvo;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtils.class);
	
	/**
	 * Construtor default.
	 * 
	 * @param classeExcecao
	 * @param mensagemRetorno
	 * @param alvo
	 */
	private TipoRetornoAutenticacaoEnum(Class<?> classeExcecao, String mensagemRetorno, String alvo) {

		this.classeExcecao = classeExcecao;
		this.mensagemRetorno = mensagemRetorno;
		this.alvo = alvo;
		
	}

	/**
	 * @return the classeExcecao
	 */
	public Class<?> getClasseExcecao() {
		return classeExcecao;
	}

	/**
	 * @return the mensagemRetorno
	 */
	public String getMensagemRetorno() {
		return mensagemRetorno;
	}
	
	/**
	 * @return the alvo
	 */
	public String getAlvo() {
		return alvo;
	}
	
	/**
	 * Metodo responsável por obter um tipo de retorno autenticação
	 * de acordo com uma exceção lançada na autenticação.
	 * 
	 * @param classeExcecao
	 * @return
	 */
	public final static TipoRetornoAutenticacaoEnum getTipoRetornoByException(Exception classeExcecao){
		
		if(classeExcecao != null){
			
			for(TipoRetornoAutenticacaoEnum tipoRetornoAutenticacaoEnum : TipoRetornoAutenticacaoEnum.values()){
				
				if(!tipoRetornoAutenticacaoEnum.name().equals("SUCESSO")
						&& tipoRetornoAutenticacaoEnum.getClasseExcecao().equals(classeExcecao.getClass())){
					
					return tipoRetornoAutenticacaoEnum;
					
				}
				
			}
			
			LOGGER.error(NAO_FOI_ENCONTRADO_NENHUM_TIPO_RETORNO_AUTENTICACAO_PARA_A_CLASSE_DE_EXCECAO + classeExcecao);
			
			throw new IllegalArgumentException(NAO_FOI_ENCONTRADO_NENHUM_TIPO_RETORNO_AUTENTICACAO_PARA_A_CLASSE_DE_EXCECAO + classeExcecao);
			
		}else{
			
			return TipoRetornoAutenticacaoEnum.SUCESSO;
			
		}
		
	}
}
