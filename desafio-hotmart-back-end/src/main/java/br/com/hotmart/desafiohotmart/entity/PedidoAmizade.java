package br.com.hotmart.desafiohotmart.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.hotmart.desafiohotmart.enumerations.StatusSolicitacaoAmizadeEnum;

/**
 * Entidade de solicitação de amizade.
 * 
 * @author tiago
 *
 */
@Entity
@Table(name = "pedido_amizade")
public class PedidoAmizade extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 655941378736180055L;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_principal_id")
	private Usuario usuarioPrincipal;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_amigo_id")
	private Usuario usuarioAmigo;
	
	@NotNull
	@Column(name = "data_solicitacao", nullable = true, unique = false, columnDefinition = "datetime")
	private LocalDateTime dataSolicitacao;
	
	@NotNull
	@Column(name = "status_solicitacao", nullable = false, unique = false, columnDefinition = "tinyint")
	@Enumerated(value = EnumType.ORDINAL)
	private StatusSolicitacaoAmizadeEnum statusSolicitacao;

	/**
	 * @return the usuarioPrincipal
	 */
	public Usuario getUsuarioPrincipal() {
		return usuarioPrincipal;
	}

	/**
	 * @param usuarioPrincipal the usuarioPrincipal to set
	 */
	public void setUsuarioPrincipal(Usuario usuarioPrincipal) {
		this.usuarioPrincipal = usuarioPrincipal;
	}

	/**
	 * @return the usuarioAmigo
	 */
	public Usuario getUsuarioAmigo() {
		return usuarioAmigo;
	}

	/**
	 * @param usuarioAmigo the usuarioAmigo to set
	 */
	public void setUsuarioAmigo(Usuario usuarioAmigo) {
		this.usuarioAmigo = usuarioAmigo;
	}

	/**
	 * @return the dataSolicitacao
	 */
	public LocalDateTime getDataSolicitacao() {
		return dataSolicitacao;
	}

	/**
	 * @param dataSolicitacao the dataSolicitacao to set
	 */
	public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	/**
	 * @return the statusSolicitacao
	 */
	public StatusSolicitacaoAmizadeEnum getStatusSolicitacao() {
		return statusSolicitacao;
	}

	/**
	 * @param statusSolicitacao the statusSolicitacao to set
	 */
	public void setStatusSolicitacao(StatusSolicitacaoAmizadeEnum statusSolicitacao) {
		this.statusSolicitacao = statusSolicitacao;
	}

}
