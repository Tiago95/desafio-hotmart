package br.com.hotmart.desafiohotmart.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.hotmart.desafiohotmart.enumerations.ReturnTypeEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * Responsável por montar a estrutura padrão de retorno do REST (JSON).
 * 
 * @author Tiago
 *
 * @param <T>
 */
public class ResponseVO<T> implements BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5687213113675187630L;

	@ApiModelProperty(notes = "Tipo de retorno da requisição")
	private ReturnTypeEnum returnType;
	
	@ApiModelProperty(notes = "Mensagens de validação relacionadas a campo")
	private List<FieldInfoVO> fieldsInfo;
	
	@ApiModelProperty(notes = "Mensagens de validação não relacionadas a campo")
	private List<MessageInfoVO> messagesInfo;
	
	@ApiModelProperty(notes = "VO de retorno de uma requisição")
	private T voReturn;
	
	/**
	 * Construtor default.
	 * 
	 */
	public ResponseVO() {
		super();
	}
	
	/**
	 * Construtor que recebe um tipo de retorno e um ou vários
	 * fields info. 
	 * 
	 * @param returnType
	 * @param fieldsInfo
	 */
	public ResponseVO(ReturnTypeEnum returnType, FieldInfoVO... fieldsInfo) {
		
		this();
		
		this.returnType = returnType;
		
		if(fieldsInfo != null && fieldsInfo.length > 0){
			
			this.fieldsInfo = new ArrayList<>();
			
			for (FieldInfoVO fi : fieldsInfo) {
				
				this.fieldsInfo.add(fi);
				
			}
			
		}
		
	}

	/**
	 * Construtor que recebe um tipo de retorno e uma mensagem.
	 * 
	 * @param returnType
	 * @param mensagem
	 */
	public ResponseVO(ReturnTypeEnum returnType, String mensagem) {
		
		this.messagesInfo = new ArrayList<>(Arrays.asList(new MessageInfoVO(mensagem, returnType)));	
		this.returnType = returnType;
	}

	/**
	 * Construtor que recebe um tipo de retorno, uma lista de fields e uma lista de mensagens.
	 * 
	 * @param tipo
	 * @param fieldsInfoVO
	 * @param messagesInfoVO
	 */
	public ResponseVO(ReturnTypeEnum returnType, List<FieldInfoVO> fieldsInfoVO, List<MessageInfoVO> messagesInfoVO) {
		
		this();
		
		this.returnType = returnType;
		this.fieldsInfo = fieldsInfoVO;
		this.messagesInfo = messagesInfoVO;
		
	}

	/**
	 * Construtor que recebe todos os paramêtros.
	 * 
	 * @param returnType
	 * @param fieldsInfoVO
	 * @param messagesInfoVO
	 * @param entityReturn
	 */
	public ResponseVO(ReturnTypeEnum returnType, List<FieldInfoVO> fieldsInfoVO, List<MessageInfoVO> messagesInfoVO, T voReturn) {
		
		this();
		
		this.returnType = returnType;
		this.fieldsInfo = fieldsInfoVO;
		this.messagesInfo = messagesInfoVO;
		this.voReturn = voReturn;
	}

	/**
	 * Construtor que recebe um tipo de retorno e um vo de retorno.
	 * 
	 * @param returnType
	 * @param voReturn
	 */
	public ResponseVO(ReturnTypeEnum returnType, T voReturn) {
		
		this.returnType = returnType;
		this.voReturn = voReturn;

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

	/**
	 * @return the fieldsInfo
	 */
	public List<FieldInfoVO> getFieldsInfo() {
		return fieldsInfo;
	}

	/**
	 * @param fieldsInfo the fieldsInfo to set
	 */
	public void setFieldsInfo(List<FieldInfoVO> fieldsInfo) {
		this.fieldsInfo = fieldsInfo;
	}

	/**
	 * @return the messagesInfo
	 */
	public List<MessageInfoVO> getMessagesInfo() {
		return messagesInfo;
	}

	/**
	 * @param messagesInfo the messagesInfo to set
	 */
	public void setMessagesInfo(List<MessageInfoVO> messagesInfo) {
		this.messagesInfo = messagesInfo;
	}

	/**
	 * @return the voReturn
	 */
	public T getVoReturn() {
		return voReturn;
	}

	/**
	 * @param voReturn the voReturn to set
	 */
	public void setVoReturn(T voReturn) {
		this.voReturn = voReturn;
	}
	
}
