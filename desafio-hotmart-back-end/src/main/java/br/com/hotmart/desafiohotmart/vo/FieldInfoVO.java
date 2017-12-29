package br.com.hotmart.desafiohotmart.vo;

import br.com.hotmart.desafiohotmart.enumerations.ReturnTypeEnum;

/**
 * Responsável por conter as informações de fields da aplicação.
 * 
 * @author Tiago
 *
 */
public class FieldInfoVO implements BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3662206656758615543L;
	
	private String message;
	
	private String nameField;
	
	private ReturnTypeEnum returnType;
	
	/**
	 * Construtor default.
	 * 
	 */
	public FieldInfoVO() {
		super();
	}	
	
	/**
	 * Construtor com todos os parametros.
	 * 
	 * @param message
	 * @param nameField
	 * @param returnType
	 */
	public FieldInfoVO(String message, String nameField, ReturnTypeEnum returnType) {
		
		this();
		
		this.message = message;
		this.nameField = nameField;
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
	 * @return the nameField
	 */
	public String getNameField() {
		return nameField;
	}

	/**
	 * @param nameField the nameField to set
	 */
	public void setNameField(String nameField) {
		this.nameField = nameField;
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
