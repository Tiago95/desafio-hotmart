package br.com.hotmart.desafiohotmart.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.hotmart.desafiohotmart.entity.PedidoAmizade;
import br.com.hotmart.desafiohotmart.entity.Usuario;
import br.com.hotmart.desafiohotmart.enumerations.StatusSolicitacaoAmizadeEnum;

/**
 * Interface de acesso a dados de pedido de amizade.
 * 
 * @author tiago
 *
 */
public interface PedidoAmizadeDAO extends PagingAndSortingRepository<PedidoAmizade, Long> {

	/**
	 * Responsável por obter a quantidade de pedido de amizade de acordo com um usuario principal
	 * e um usuario amigo.
	 * 
	 * @param usuarioPrincipal
	 * @param usuarioAmigo
	 * @return
	 */
	Long countByUsuarioPrincipalAndUsuarioAmigo(Usuario usuarioPrincipal, Usuario usuarioAmigo);

	/**
	 * Responsável por atualizar o pedido de amizade.
	 * 
	 * @param usuarioPrincipal
	 * @param usuarioAmigo
	 * @param statusSolicitacaoAmizade
	 */
	@Transactional
	@Modifying
	@Query("UPDATE FROM PedidoAmizade SET statusSolicitacao = :statusSolicitacaoAmizade WHERE usuarioPrincipal = :usuarioPrincipal AND usuarioAmigo = :usuarioAmigo")
	void atualizarPedidoAmizadeByUsuarioPrincipalAndUsuarioAmigo(@Param("usuarioPrincipal") Usuario usuarioPrincipal, @Param("usuarioAmigo") Usuario usuarioAmigo,
			@Param("statusSolicitacaoAmizade") StatusSolicitacaoAmizadeEnum statusSolicitacaoAmizade);

}
