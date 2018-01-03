package br.com.hotmart.desafiohotmart.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import br.com.hotmart.desafiohotmart.vo.ChatMessageVO;

/**
 * Entidade de armazenamento das mensagens de usuário.
 * 
 * @author Tiago
 *
 */
@Entity
@Table(name = "chat_message")
public class ChatMessage extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -773650550742026709L;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_origem_id")
	private Usuario usuarioOrigem;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_destino_id")
	private Usuario usuarioDestino;
	
	@NotBlank
	@Size(max = 255)
	@Column(name = "message", nullable = false, unique = false, length = 255)
	private String message;
	
	@NotNull
	@Column(name = "send_date", nullable = true, unique = false, columnDefinition = "datetime")
	private LocalDateTime sendDate;
	
	@NotNull
	@Column(name = "lida", nullable = false, unique = false)
	private boolean lida;
	
	@NotNull
	@Column(name = "recebida", nullable = false, unique = false)
	private boolean recebida;
	
	/**
	 * Construtor default.
	 * 
	 */
	public ChatMessage() {
		super();
	}

	/**
	 * Construtor que recebe um usuário de origem, um usuário de destino e uma mensagem.
	 * 
	 * @param usuarioOrigem
	 * @param usuarioDestino
	 * @param message
	 */
	public ChatMessage(Usuario usuarioOrigem, Usuario usuarioDestino, String message) {

		this();
		
		this.usuarioOrigem = usuarioOrigem;
		this.usuarioDestino = usuarioDestino;
		this.message = message;
		this.sendDate = LocalDateTime.now();
		
	}

	/**
	 * @return the usuarioOrigem
	 */
	public Usuario getUsuarioOrigem() {
		return usuarioOrigem;
	}

	/**
	 * @param usuarioOrigem the usuarioOrigem to set
	 */
	public void setUsuarioOrigem(Usuario usuarioOrigem) {
		this.usuarioOrigem = usuarioOrigem;
	}

	/**
	 * @return the usuarioDestino
	 */
	public Usuario getUsuarioDestino() {
		return usuarioDestino;
	}

	/**
	 * @param usuarioDestino the usuarioDestino to set
	 */
	public void setUsuarioDestino(Usuario usuarioDestino) {
		this.usuarioDestino = usuarioDestino;
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
	 * @return the sendDate
	 */
	public LocalDateTime getSendDate() {
		return sendDate;
	}

	/**
	 * @param sendDate the sendDate to set
	 */
	public void setSendDate(LocalDateTime sendDate) {
		this.sendDate = sendDate;
	}

	/**
	 * @return the lida
	 */
	public boolean isLida() {
		return lida;
	}

	/**
	 * @param lida the lida to set
	 */
	public void setLida(boolean lida) {
		this.lida = lida;
	}

	/**
	 * @return the recebida
	 */
	public boolean isRecebida() {
		return recebida;
	}

	/**
	 * @param recebida the recebida to set
	 */
	public void setRecebida(boolean recebida) {
		this.recebida = recebida;
	}

	/**
	 * Responsável por retornar um ChatMessageVO.
	 * 
	 * @return
	 */
	public ChatMessageVO toChatMessageVO() {

		ChatMessageVO chatMessageVO = new ChatMessageVO();
		
		chatMessageVO.setIdUsuarioOrigem(getUsuarioOrigem().getId());
		chatMessageVO.setNameUsuarioOrigem(getUsuarioOrigem().getNome());
		chatMessageVO.setMessage(getMessage());
		chatMessageVO.setDate(getSendDate());
		
		return chatMessageVO;
	}
	
}
