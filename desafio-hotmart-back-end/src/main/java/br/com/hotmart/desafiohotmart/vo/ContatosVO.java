package br.com.hotmart.desafiohotmart.vo;

import br.com.hotmart.desafiohotmart.enumerations.StatusSolicitacaoAmizadeEnum;

/**
 * 
 * Classe que irá encapsular as informações de contato.
 * 
 * @author tiago
 *
 */
public class ContatosVO implements BaseResponseVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4126424322444784145L;

	private String nome;
	
	private String nick;
	
	private boolean bloqueado;
	
	private StatusSolicitacaoAmizadeEnum statusSolicitacaoAmizade;
	
	/**
	 * Construtor default.
	 * 
	 */
	public ContatosVO() {
		super();
	}
	
	/**
	 * Construtor com todos os parametros.
	 * 
	 * @param nome
	 * @param nick
	 * @param bloqueado
	 * @param statusSolicitacaoAmizade
	 */
	public ContatosVO(String nome, String nick, boolean bloqueado,
			StatusSolicitacaoAmizadeEnum statusSolicitacaoAmizade) {
		
		this();
		
		this.nome = nome;
		this.nick = nick;
		this.bloqueado = bloqueado;
		this.statusSolicitacaoAmizade = statusSolicitacaoAmizade;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the nick
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * @param nick the nick to set
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * @return the bloqueado
	 */
	public boolean isBloqueado() {
		return bloqueado;
	}

	/**
	 * @param bloqueado the bloqueado to set
	 */
	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	/**
	 * @return the statusSolicitacaoAmizade
	 */
	public StatusSolicitacaoAmizadeEnum getStatusSolicitacaoAmizade() {
		return statusSolicitacaoAmizade;
	}

	/**
	 * @param statusSolicitacaoAmizade the statusSolicitacaoAmizade to set
	 */
	public void setStatusSolicitacaoAmizade(StatusSolicitacaoAmizadeEnum statusSolicitacaoAmizade) {
		this.statusSolicitacaoAmizade = statusSolicitacaoAmizade;
	}

}
