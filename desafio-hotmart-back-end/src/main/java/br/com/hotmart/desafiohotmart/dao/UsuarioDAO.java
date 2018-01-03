package br.com.hotmart.desafiohotmart.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.hotmart.desafiohotmart.entity.Usuario;
import br.com.hotmart.desafiohotmart.vo.ContatosVO;

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
	
	/**
	 * Responsável por retornar uma lista de contatos de acordo com um usuário.
	 * 
	 * @param usuario
	 * @return
	 */
	@Query("SELECT new br.com.hotmart.desafiohotmart.vo.ContatosVO(u.nome, u.nickName, CASE WHEN ub IS NOT NULL THEN true ELSE false END, CASE WHEN pa IS NOT NULL THEN pa.statusSolicitacao ELSE 0 END) FROM Usuario u LEFT JOIN UsuarioBloqueado ub ON (ub.usuarioPrincipal = :usuario AND ub.usuarioBloqueado = u) LEFT JOIN PedidoAmizade pa ON (pa.usuarioPrincipal = :usuario AND pa.usuarioAmigo = u) WHERE u <> :usuario")
	List<ContatosVO> getContatosVOByUsuario(@Param("usuario") Usuario usuario);
}
