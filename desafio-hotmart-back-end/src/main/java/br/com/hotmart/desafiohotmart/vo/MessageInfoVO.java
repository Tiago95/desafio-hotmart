package br.com.hotmart.desafiohotmart.vo;

import br.com.hotmart.desafiohotmart.enumerations.ReturnTypeEnum;

/**
 * Classe responsável por conter as mensgens de retorno
 * de uma requisição.
 * 
 * @author Tiago
 *
 */
public class MessageInfoVO implements BaseVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3707788901966746251L;

	private String message;
	
	private ReturnTypeEnum returnType;
	
	/**
	 * Construtor default.
	 * 
	 */
	public MessageInfoVO() {
		super();
	}

	/**
	 * Construtor que recebe uma mensagem e um tipo de retorno.
	 * 
	 * @param message
	 * @param returnType
	 */
	public MessageInfoVO(String message, ReturnTypeEnum returnType) {

		this.message = message;
		this.returnType = returnType;
		
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
	 * @return the returnType
	 */
	public ReturnTypeEnum getReturnType() {
		return returnType;
	}

	/**
	 * @param returnType the returnType to set
	 */
	public void setReturnType(ReturnTypeEnum returnType) {
		this.returnType = returnType;
	}

}
