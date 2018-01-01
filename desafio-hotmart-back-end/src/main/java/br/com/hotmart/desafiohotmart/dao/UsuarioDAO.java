package br.com.hotmart.desafiohotmart.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.hotmart.desafiohotmart.entity.Usuario;

/**
 * Interface responsável por realizar
 * as operações na base de dados
 * relativas a entidade usuário.
 * 
 * @author Tiago
 *
 */
public interface UsuarioDAO extends PagingAndSortingRepository<Usuario, Long> {

	/**
	 * Método responsável por obter um usuário
	 * de acordo com o seu login.
	 * 
	 * @param login
	 * @return
	 */
	Usuario findByLogin(String login);

	/**
	 * Responsável por obter a quantidade de nick name.
	 * 
	 * @param nickName
	 * @return
	 */
	Long countByNickName(String nickName);

	/**
	 * Responsável por obter a quantidade de e-mail.
	 * 
	 * @param login
	 * @return
	 */
	Long countByLogin(String login);

	/**
	 * Responsável por atualizar o status de conectado do usuário.
	 * 
	 * @param idUsuario
	 * @param conectado
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Usuario u SET conectado = :conectado WHERE u.id = :idUsuario")
	void updateUserConnected(@Param("idUsuario") Long idUsuario, @Param("conectado") boolean conectado);
}
