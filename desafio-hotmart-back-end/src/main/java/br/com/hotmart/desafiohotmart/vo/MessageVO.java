package br.com.hotmart.desafiohotmart.vo;

/**
 * Responsáel por receber as mensagens enviadas no chat.
 * 
 * @author Tiago
 *
 */
public class MessageVO implements BaseVO, BaseResponseVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4579003371467714061L;
	
	private String message;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
