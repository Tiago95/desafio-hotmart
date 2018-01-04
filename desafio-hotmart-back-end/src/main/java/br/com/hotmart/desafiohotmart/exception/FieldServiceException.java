package br.com.hotmart.desafiohotmart.exception;

/**
 * Classe de exceção de serviço quando o erro estiver
 * ligado ao field.
 * 
 * @author Tiago
 *
 */
public class FieldServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2038336178395988196L;
	
	private final String field;
	
	/**
	 * Construtor com todos os parametros.
	 * 
	 * @param field
	 * @param message
	 */
	public FieldServiceException(String field, String message) {
		
		super(message);
		
		this.field = field;
		
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}
	
}
