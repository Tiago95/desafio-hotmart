package br.com.hotmart.desafiohotmart.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.hotmart.desafiohotmart.entity.PedidoAmizade;

/**
 * Interface de acesso a dados de pedido de amizade.
 * 
 * @author tiago
 *
 */
public interface PedidoAmizadeDAO extends PagingAndSortingRepository<PedidoAmizade, Long> {

}
