package br.com.hotmart.desafiohotmart.entity;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.hotmart.desafiohotmart.enumerations.StatusUsuarioEnum;
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return new HashSet<>();
		
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
