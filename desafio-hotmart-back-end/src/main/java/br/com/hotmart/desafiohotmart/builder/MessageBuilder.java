package br.com.hotmart.desafiohotmart.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.hotmart.desafiohotmart.enumerations.ReturnTypeEnum;
import br.com.hotmart.desafiohotmart.exception.FieldServiceException;
import br.com.hotmart.desafiohotmart.exception.ServiceException;
import br.com.hotmart.desafiohotmart.vo.FieldInfoVO;
import br.com.hotmart.desafiohotmart.vo.MessageInfoVO;
import br.com.hotmart.desafiohotmart.vo.ResponseVO;

/**
 * Classe responsável por controlar as mensagens de erro
 * e de deixar a forma de lidar com o ResponseVO mais fácil.
 * 
 * @author Tiago
 *
 * @param <E>
 */
public class MessageBuilder<E> {

	private Map<String, FieldInfoVO> errosEncontrados = new HashMap<>();

	private Set<String> mensagensErro = new LinkedHashSet<String>();

	private Set<String> mensagensSucesso = new LinkedHashSet<String>();

	private Set<String> mensagensAlerta = new LinkedHashSet<String>();

	private E voReturn;

	/**
	 * Construtor default.
	 *
	 */
	public MessageBuilder() {
		super();
	}

	/**
	 * Construtor que recebe o vo de retorno.
	 *
	 * @param voReturn
	 */
	public MessageBuilder(E voReturn) {

		this();

		this.voReturn = voReturn;
	}

	/**
	 * Responsável por colocar uma mensagem de erro em um determinado campo
	 * 
	 * @param nomeCampo
	 * @param mensagem
	 * @return
	 */
	public MessageBuilder<E> addErroField(String nomeCampo, String mensagem) {

		errosEncontrados.put(nomeCampo, new FieldInfoVO(mensagem, nomeCampo, ReturnTypeEnum.ERRO));

		return this;
	}
	
	/**
	 * Responsável por colocar uma mensagem de alerta em um determinado campo
	 * 
	 * @param nomeCampo
	 * @param mensagem
	 * @return
	 */
	public MessageBuilder<E> addWarningField(String nomeCampo, String mensagem) {

		errosEncontrados.put(nomeCampo, new FieldInfoVO(mensagem, nomeCampo, ReturnTypeEnum.SUCESSO));

		return this;
	}
	
	/**
	 * Responsável por colocar uma mensagem de sucesso em um determinado campo
	 * 
	 * @param nomeCampo
	 * @param mensagem
	 * @return
	 */
	public MessageBuilder<E> addSucessField(String nomeCampo, String mensagem) {

		errosEncontrados.put(nomeCampo, new FieldInfoVO(mensagem, nomeCampo, ReturnTypeEnum.ERRO));

		return this;
	}
	
	/**
	 * Responsável por colocar uma mensagem em um determinado campo
	 * 
	 * @param nomeCampo
	 * @param mensagem
	 * @return
	 */
	public MessageBuilder<E> addMessageField(String nomeCampo, String mensagem, ReturnTypeEnum returnType) {

		errosEncontrados.put(nomeCampo, new FieldInfoVO(mensagem, nomeCampo, returnType));

		return this;
	}	

	/**
	 * Adiciona mensagem de erro
	 *
	 * @param message
	 * @return
	 */
	public MessageBuilder<E> addErrorMessage(String message) {

		mensagensErro.add(message);
		
		return this;
	}


	/**
	 * Adiciona mensagem de alerta
	 *
	 * @param message
	 * @return
	 */
	public MessageBuilder<E> addWarningMessag(String message) {

		mensagensAlerta.add(message);
		
		return this;
	}

	/**
	 * Adiciona mensagem de sucesso
	 *
	 * @param message
	 * @return
	 */
	public MessageBuilder<E> addSucessMessage(String message) {

		mensagensSucesso.add(message);
		
		return this;
	}
	
	/**
	 * Responsável por adicionar um erro de acordo com uma ServiceException;
	 * 
	 * @param serviceException
	 * 
	 * @return
	 */
	public MessageBuilder<E> addErrorByServiceException(ServiceException serviceException) {
		
		if(serviceException != null){
			
			if(serviceException.getCause() instanceof FieldServiceException){
				
				return addErroField(((FieldServiceException) serviceException.getCause()).getField(), serviceException.getCause().getMessage());
				
			}else {
				
				return addErrorMessage(serviceException.getMessage());
				
			}
			
		}
		
		return this;
		
	}

	/**
	 * 
	 * Responsável por criar o objeto com todas as funcoes criadas
	 *
	 */
	public ResponseVO<E> build() {

		return new ResponseVO<E>(getReturnType(), new ArrayList<FieldInfoVO>(errosEncontrados.values()), getReturnMessages(), voReturn);
		
	}
	
	/**
	 * Responsável por obter o tipo de retorno.
	 * 
	 * @return
	 */
	private ReturnTypeEnum getReturnType(){
		
		return hasError() ? ReturnTypeEnum.ERRO : ReturnTypeEnum.SUCESSO;
		
	}

	/**
	 * Responsável por retornar as mensagens armazenadas no buider.
	 * 
	 * @return
	 */
	private List<MessageInfoVO> getReturnMessages() {
		
		List<MessageInfoVO> mensagensRetorno = new ArrayList<MessageInfoVO>();
		
		mensagensErro.forEach(message -> mensagensRetorno.add(new MessageInfoVO(message, ReturnTypeEnum.ERRO)));
		mensagensSucesso.forEach(message -> mensagensRetorno.add(new MessageInfoVO(message, ReturnTypeEnum.SUCESSO)));
		mensagensAlerta.forEach(message -> mensagensRetorno.add(new MessageInfoVO(message, ReturnTypeEnum.ATENCAO)));
		
		return mensagensRetorno;
	}

	/**
	 *
	 * @return se existe erro cadastrado em algum campo
	 */
	public boolean hasError() {

		return !errosEncontrados.isEmpty() || !mensagensErro.isEmpty();
	}

	/**
	 * @return the voReturn
	 */
	public E getVoReturn() {
		return voReturn;
	}

	/**
	 * @param voReturn the voReturn to set
	 */
	public MessageBuilder<E> setVoReturn(E voReturn) {
		
		this.voReturn = voReturn;
		
		return this;
	}
	
}
