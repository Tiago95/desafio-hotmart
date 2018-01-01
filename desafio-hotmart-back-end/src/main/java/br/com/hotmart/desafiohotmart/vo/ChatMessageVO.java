package br.com.hotmart.desafiohotmart.vo;

import java.time.LocalDateTime;

/**
 * Classe responsável por conter os atributos de mensagens.
 * 
 * @author Tiago
 *
 */
public class ChatMessageVO implements BaseVO, BaseResponseVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6652861385695786342L;

	private Long idUsuarioOrigem;
	
	private String nameUsuarioOrigem;
	
	private String message;
	
	private LocalDateTime date;

	/**
	 * @return the idUsuarioOrigem
	 */
	public Long getIdUsuarioOrigem() {
		return idUsuarioOrigem;
	}

	/**
	 * @param idUsuarioOrigem the idUsuarioOrigem to set
	 */
	public void setIdUsuarioOrigem(Long idUsuarioOrigem) {
		this.idUsuarioOrigem = idUsuarioOrigem;
	}

	/**
	 * @return the nameUsuarioOrigem
	 */
	public String getNameUsuarioOrigem() {
		return nameUsuarioOrigem;
	}

	/**
	 * @param nameUsuarioOrigem the nameUsuarioOrigem to set
	 */
	public void setNameUsuarioOrigem(String nameUsuarioOrigem) {
		this.nameUsuarioOrigem = nameUsuarioOrigem;
	}

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
	 * @return the date
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
}
