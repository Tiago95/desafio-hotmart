package br.com.hotmart.desafiohotmart.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import br.com.hotmart.desafiohotmart.dao.PedidoAmizadeDAO;
import br.com.hotmart.desafiohotmart.entity.PedidoAmizade;
import br.com.hotmart.desafiohotmart.entity.Usuario;
import br.com.hotmart.desafiohotmart.enumerations.StatusSolicitacaoAmizadeEnum;

/**
 * Classe de serviço de pedido de amizade.
 * 
 * @author tiago
 *
 */
@Service
public class PedidoAmizadeService extends BaseServiceAbstract<PedidoAmizade, Long> {

	@Autowired
	private PedidoAmizadeDAO pedidoAmizadeDAO;
	
	@Override
	public PagingAndSortingRepository<PedidoAmizade, Long> getDAO() {
		return pedidoAmizadeDAO;
	}

	/**
	 * Responsável por solicitar um pedido de amizade.
	 * 
	 * @param idUsuario
	 * @param idUsuarioAmigo
	 */
	public void solicitarPedidoAmizade(Long idUsuario, Long idUsuarioAmigo) {

		if(idUsuario != null && idUsuarioAmigo != null){
			
			Usuario usuarioPrincipal = new Usuario(idUsuario);
			Usuario usuarioAmigo = new Usuario(idUsuarioAmigo);
			
			if(pedidoAmizadeDAO.countByUsuarioPrincipalAndUsuarioAmigo(usuarioPrincipal, usuarioAmigo) <= 0){
				
				save(new PedidoAmizade(usuarioPrincipal, usuarioAmigo));
				
			}
			
		}
		
	}
	
	/**
	 * Responsável por atualizar o pedido de amizade.
	 * 
	 * @param usuarioPrincipal
	 * @param usuarioAmigo
	 * @param statusSolicitacaoAmizade
	 */
	@Transactional
	public void atualizarPedidoAmizade(Long idUsuario, Long idUsuarioAmigo, StatusSolicitacaoAmizadeEnum statusSolicitacaoAmizade) {

		if(idUsuario != null && idUsuarioAmigo != null){
			
			pedidoAmizadeDAO.atualizarPedidoAmizadeByUsuarioPrincipalAndUsuarioAmigo(new Usuario(idUsuario), new Usuario(idUsuarioAmigo), statusSolicitacaoAmizade);
			
		}
		
	}

}
