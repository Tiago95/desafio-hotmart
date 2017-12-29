package br.com.hotmart.desafiohotmart.entity;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.hotmart.desafiohotmart.enumerations.StatusUsuarioEnum;

/**
 * 
 * @author Tiago Guimarães da Silva
 * 
 * Entidade Usuário do Sistema Siscond
 *
 */
@Entity
@Table(name = "Usuario")
public class Usuario extends BaseEntity implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2252840882729290922L;
	
	@Column(name = "login", nullable = false, unique = true, length = 150)
	private String login;
	
	@Column(name = "senha", nullable = false, unique = false, length = 255)
	private String senha;
	
	@Column(name = "nick_name", nullable = false, unique = true, length = 50)
	private String nickName;
	
	@Column(name = "status", nullable = false, unique = false, columnDefinition = "tinyint")
	@Enumerated(value = EnumType.ORDINAL)
	private StatusUsuarioEnum statusUsuario;	

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

}
