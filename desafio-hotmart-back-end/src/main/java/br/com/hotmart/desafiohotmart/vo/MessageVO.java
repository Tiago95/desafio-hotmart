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
	
	private Long idUsuarioDestino;

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

	/**
	 * @return the idUsuarioDestino
	 */
	public Long getIdUsuarioDestino() {
		return idUsuarioDestino;
	}

	/**
	 * @param idUsuarioDestino the idUsuarioDestino to set
	 */
	public void setIdUsuarioDestino(Long idUsuarioDestino) {
		this.idUsuarioDestino = idUsuarioDestino;
	}

}
