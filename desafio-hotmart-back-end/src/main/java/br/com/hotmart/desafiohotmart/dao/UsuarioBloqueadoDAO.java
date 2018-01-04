package br.com.hotmart.desafiohotmart.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.hotmart.desafiohotmart.entity.Usuario;
import br.com.hotmart.desafiohotmart.entity.UsuarioBloqueado;

/**
 * Interface de acesso a dados de usuário bloqueado.
 * 
 * @author tiago
 *
 */
public interface UsuarioBloqueadoDAO extends PagingAndSortingRepository<UsuarioBloqueado, Long> {

	/**
	 * Responsável por retornar a quantidade de bloqueio de acordo com um usuário principal
	 * e um usuário bloqueado.
	 * 
	 * @param usuarioPrincipal
	 * @param usuarioBloqueado
	 * @return
	 */
	Long countByUsuarioPrincipalAndUsuarioBloqueado(Usuario usuarioPrincipal, Usuario usuarioBloqueado);

	/**
	 * Responsável por deletar um bloqueio de acordo com um usuário principal e um usuário bloqueado.
	 * 
	 * @param usuarioPrincipal
	 * @param usuarioBloqueado
	 */
	@Transactional
	@Modifying
	@Query("DELETE FROM UsuarioBloqueado WHERE usuarioPrincipal = :usuarioPrincipal AND usuarioBloqueado = :usuarioBloqueado")
	void deleteByUsuarioPrincipalAndUsuarioBloqueado(@Param("usuarioPrincipal") Usuario usuarioPrincipal, @Param("usuarioBloqueado") Usuario usuarioBloqueado);

}
