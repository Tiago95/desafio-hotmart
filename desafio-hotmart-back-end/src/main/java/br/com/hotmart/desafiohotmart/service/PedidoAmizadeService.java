package br.com.hotmart.desafiohotmart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import br.com.hotmart.desafiohotmart.dao.PedidoAmizadeDAO;
import br.com.hotmart.desafiohotmart.entity.PedidoAmizade;

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

}
