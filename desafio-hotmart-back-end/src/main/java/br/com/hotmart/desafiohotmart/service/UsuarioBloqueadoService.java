package br.com.hotmart.desafiohotmart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import br.com.hotmart.desafiohotmart.dao.UsuarioBloqueadoDAO;
import br.com.hotmart.desafiohotmart.entity.UsuarioBloqueado;

/**
 * Classe de serviço de usuário bloqueado.
 * 
 * @author tiago
 *
 */
@Service
public class UsuarioBloqueadoService extends BaseServiceAbstract<UsuarioBloqueado, Long> {

	@Autowired
	private UsuarioBloqueadoDAO usuarioBloqueadoDAO;
	
	@Override
	public PagingAndSortingRepository<UsuarioBloqueado, Long> getDAO() {
		return usuarioBloqueadoDAO;
	}

}
