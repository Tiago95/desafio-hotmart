package br.com.hotmart.desafiohotmart.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.hotmart.desafiohotmart.entity.UsuarioBloqueado;

/**
 * Interface de acesso a dados de usuário bloqueado.
 * 
 * @author tiago
 *
 */
public interface UsuarioBloqueadoDAO extends PagingAndSortingRepository<UsuarioBloqueado, Long> {

}
