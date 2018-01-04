package br.com.hotmart.desafiohotmart.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import br.com.hotmart.desafiohotmart.dao.UsuarioBloqueadoDAO;
import br.com.hotmart.desafiohotmart.entity.Usuario;
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

	/**
	 * Responsável por bloquear um usuário.
	 * 
	 * @param idUsuario
	 * @param idUsuarioBloqueado
	 */
	public void bloquearUsuario(Long idUsuario, Long idUsuarioBloqueado) {

		if(idUsuario != null && idUsuarioBloqueado != null){
			
			Usuario usuarioPrincipal = new Usuario(idUsuario);
			Usuario usuarioBloqueado = new Usuario(idUsuarioBloqueado);
			
			if(usuarioBloqueadoDAO.countByUsuarioPrincipalAndUsuarioBloqueado(usuarioPrincipal, usuarioBloqueado) <= 0){
				
				save(new UsuarioBloqueado(usuarioPrincipal, usuarioBloqueado));
				
			}		
			
		}
		
	}
	
	/**
	 * Responsável por desbloquear um usuário.
	 * 
	 * @param idUsuario
	 * @param idUsuarioBloqueado
	 */
	@Transactional
	public void desbloquearUsuario(Long idUsuario, Long idUsuarioBloqueado) {

		if(idUsuario != null && idUsuarioBloqueado != null){
			
			usuarioBloqueadoDAO.deleteByUsuarioPrincipalAndUsuarioBloqueado(new Usuario(idUsuario), new Usuario(idUsuarioBloqueado));	
			
		}
		
	}

}
