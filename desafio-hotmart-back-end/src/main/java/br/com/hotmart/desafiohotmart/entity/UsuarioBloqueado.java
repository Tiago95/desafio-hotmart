package br.com.hotmart.desafiohotmart.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade de usuários bloqueados.
 * 
 * @author Tiago
 *
 */
@Entity
@Table(name = "usuario_bloqueado")
public class UsuarioBloqueado extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7164855964788457136L;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_principal_id")
	private Usuario usuarioPrincipal;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_bloqueado_id")
	private Usuario usuarioBloqueado;
	
	@NotNull
	@Column(name = "data_bloqueio", nullable = true, unique = false, columnDefinition = "datetime")
	private LocalDateTime dataBloqueio;

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
	 * @return the usuarioBloqueado
	 */
	public Usuario getUsuarioBloqueado() {
		return usuarioBloqueado;
	}

	/**
	 * @param usuarioBloqueado the usuarioBloqueado to set
	 */
	public void setUsuarioBloqueado(Usuario usuarioBloqueado) {
		this.usuarioBloqueado = usuarioBloqueado;
	}

	/**
	 * @return the dataBloqueio
	 */
	public LocalDateTime getDataBloqueio() {
		return dataBloqueio;
	}

	/**
	 * @param dataBloqueio the dataBloqueio to set
	 */
	public void setDataBloqueio(LocalDateTime dataBloqueio) {
		this.dataBloqueio = dataBloqueio;
	}
}
