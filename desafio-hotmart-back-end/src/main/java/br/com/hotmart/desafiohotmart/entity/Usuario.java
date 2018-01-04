package br.com.hotmart.desafiohotmart.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.hotmart.desafiohotmart.enumerations.StatusUsuarioEnum;
import br.com.hotmart.desafiohotmart.vo.ContatosVO;
import br.com.hotmart.desafiohotmart.vo.UsuarioVO;

/**
 * 
 * @author Tiago Guimarães da Silva
 * 
 * Entidade Usuário do Sistema Siscond
 *
 */
@Entity
@Table(name = "usuario")
@NamedNativeQuery(name = "SqlGetContatosVOByUsuario", query = "SELECT u.id AS id, u.nome AS nome, u.nick_name AS nick, CASE WHEN ub.id IS NOT NULL THEN 'true' ELSE 'false' END AS bloqueado, CASE WHEN pa.id IS NOT NULL THEN pa.status_solicitacao ELSE 0 END AS statusSolicitacaoAmizade, CASE WHEN pa.id IS NOT NULL AND pa.usuario_principal_id = :idUsuario THEN 'true' ELSE 'false' END AS amizadeSolicitada FROM usuario u LEFT JOIN usuario_bloqueado ub ON (ub.usuario_principal_id = :idUsuario AND ub.usuario_bloqueado_id = u.id) LEFT JOIN pedido_amizade pa ON ((pa.usuario_principal_id = :idUsuario AND pa.usuario_amigo_id = u.id) OR (pa.usuario_amigo_id = :idUsuario AND pa.usuario_principal_id = u.id)) WHERE u.id <> :idUsuario", resultSetMapping = "SqlGetContatosVOByUsuario")
@SqlResultSetMapping(name = "SqlGetContatosVOByUsuario", classes = @ConstructorResult(targetClass = ContatosVO.class, columns = {@ColumnResult(name = "id", type = Long.class), @ColumnResult(name = "nome", type = String.class), @ColumnResult(name = "nick", type = String.class), @ColumnResult(name = "bloqueado", type = Boolean.class), @ColumnResult(name = "statusSolicitacaoAmizade", type = Integer.class), @ColumnResult(name = "amizadeSolicitada", type = Boolean.class)}))
public class Usuario extends BaseEntity implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2252840882729290922L;
	
	@NotBlank
	@Size(max = 100)
	@Column(name = "nome", nullable = false, unique = true, length = 100)
	private String nome;
	
	@NotBlank
	@Size(max = 150)
	@Column(name = "login", nullable = false, unique = true, length = 150)
	private String login;
	
	@NotBlank
	@Column(name = "senha", nullable = false, unique = false, length = 255)
	private String senha;
	
	@NotBlank
	@Size(max = 50)
	@Column(name = "nick_name", nullable = false, unique = true, length = 50)
	private String nickName;
	
	@NotNull
	@Column(name = "status", nullable = false, unique = false, columnDefinition = "tinyint")
	@Enumerated(value = EnumType.ORDINAL)
	private StatusUsuarioEnum statusUsuario;
	
	@NotNull
	@Column(name = "conectado", nullable = false, unique = false)
	private boolean conectado;	
	
	@NotNull
	@Column(name = "permitir_conversas_anonimas", nullable = false, unique = false)
	private boolean permitirConversasAnonimas;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarioPrincipal")
	private Set<UsuarioBloqueado> usuariosBloqueados;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarioPrincipal")
	private Set<PedidoAmizade> pedidosAmizade;
	
	/**
	 * Construtor default.
	 * 
	 */
	public Usuario() {
		
		super();
		
		this.permitirConversasAnonimas = true;
	}
	
	/**
	 * Construtor que recebe um id.
	 * 
	 * @param id
	 */
	public Usuario(Long id){
		
		this();
		
		setId(id);		
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
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the statusUsuario
	 */
	public StatusUsuarioEnum getStatusUsuario() {
		return statusUsuario;
	}

	/**
	 * @param statusUsuario the statusUsuario to set
	 */
	public void setStatusUsuario(StatusUsuarioEnum statusUsuario) {
		this.statusUsuario = statusUsuario;
	}

	/**
	 * @return the conectado
	 */
	public boolean isConectado() {
		return conectado;
	}

	/**
	 * @param conectado the conectado to set
	 */
	public void setConectado(boolean conectado) {
		this.conectado = conectado;
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

	/**
	 * @return the usuariosBloqueados
	 */
	public Set<UsuarioBloqueado> getUsuariosBloqueados() {
		return usuariosBloqueados;
	}

	/**
	 * @param usuariosBloqueados the usuariosBloqueados to set
	 */
	public void setUsuariosBloqueados(Set<UsuarioBloqueado> usuariosBloqueados) {
		this.usuariosBloqueados = usuariosBloqueados;
	}

	/**
	 * @return the pedidosAmizade
	 */
	public Set<PedidoAmizade> getPedidosAmizade() {
		return pedidosAmizade;
	}

	/**
	 * @param pedidosAmizade the pedidosAmizade to set
	 */
	public void setPedidosAmizade(Set<PedidoAmizade> pedidosAmizade) {
		this.pedidosAmizade = pedidosAmizade;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Collections.singleton((GrantedAuthority) () -> "USER");
		
	}

	@Override
	public String getPassword() {
		
		return getSenha();
		
	}

	@Override
	public String getUsername() {
		
		return getLogin();
		
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return !StatusUsuarioEnum.CONTA_EXPIRADA.equals(getStatusUsuario());
		
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return !StatusUsuarioEnum.BLOQUEADO.equals(getStatusUsuario());
		
	}

	@Override
	public boolean isCredentialsNonExpired() {
	
		return !StatusUsuarioEnum.SENHA_EXPIRADA.equals(getStatusUsuario());
		
	}

	@Override
	public boolean isEnabled() {
		
		return !StatusUsuarioEnum.INATIVO.equals(getStatusUsuario());
		
	}

	/**
	 * Responsável por retornar um UsuarioVO
	 * 
	 * @return
	 */
	public UsuarioVO toUsuarioVO() {

		UsuarioVO usuarioVO = new UsuarioVO();
		
		usuarioVO.setId(this.getId());
		usuarioVO.setEmail(this.getLogin());
		usuarioVO.setNick(this.getNickName());
		usuarioVO.setNome(this.getNome());
		usuarioVO.setSenha(this.getSenha());
		usuarioVO.setConectado(this.isConectado());
		
		return usuarioVO;
	}

}
