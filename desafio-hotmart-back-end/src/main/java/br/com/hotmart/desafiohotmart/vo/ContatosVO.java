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

	private Long id;
	
	private String nome;
	
	private String nick;
	
	private boolean bloqueado;
	
	private StatusSolicitacaoAmizadeEnum statusSolicitacaoAmizade;
	
	private boolean amizadeSolicitada;
	
	private boolean permitirConversasAnonimas;
	
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
	 * @param id
	 * @param nome
	 * @param nick
	 * @param bloqueado
	 * @param statusSolicitacaoAmizade
	 * @param amizadeSolicitada
	 * @param permitirConversasAnonimas
	 */
	public ContatosVO(Long id, String nome, String nick, Boolean bloqueado,
			Integer statusSolicitacaoAmizade, Boolean amizadeSolicitada, Boolean permitirConversasAnonimas) {
		
		this();
		
		this.id = id;
		this.nome = nome;
		this.nick = nick;
		
		if(bloqueado != null){
			
			this.bloqueado = bloqueado;
			
		}
		
		if(amizadeSolicitada != null){
			
			this.amizadeSolicitada = amizadeSolicitada;
			
		}
		
		if(permitirConversasAnonimas != null){
			
			this.permitirConversasAnonimas = permitirConversasAnonimas;
			
		}	
		
		if(statusSolicitacaoAmizade != null){
			
			this.statusSolicitacaoAmizade = StatusSolicitacaoAmizadeEnum.values()[statusSolicitacaoAmizade];
			
		}
		
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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

	/**
	 * @return the amizadeSolicitada
	 */
	public boolean isAmizadeSolicitada() {
		return amizadeSolicitada;
	}

	/**
	 * @param amizadeSolicitada the amizadeSolicitada to set
	 */
	public void setAmizadeSolicitada(boolean amizadeSolicitada) {
		this.amizadeSolicitada = amizadeSolicitada;
	}

	/**
	 * @return the permitirConversasAnonimas
	 */
	public boolean isPermitirConversasAnonimas() {
		return permitirConversasAnonimas;
	}

	/**
	 * @param permitirConversasAnonimas the permitirConversasAnonimas to set
	 */
	public void setPermitirConversasAnonimas(boolean permitirConversasAnonimas) {
		this.permitirConversasAnonimas = permitirConversasAnonimas;
	}

}
