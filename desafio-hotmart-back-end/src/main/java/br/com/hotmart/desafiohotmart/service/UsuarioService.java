package br.com.hotmart.desafiohotmart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.hotmart.desafiohotmart.dao.UsuarioDAO;
import br.com.hotmart.desafiohotmart.entity.Usuario;

/**
 * Serviço responsável por manipular
 * as regras de negócio da entidade
 * usuário.
 * 
 * @author Tiago
 *
 */
@Service
public class UsuarioService extends BaseServiceAbstract<Usuario, Long> implements UserDetailsService {
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	public PagingAndSortingRepository<Usuario, Long> getDAO() {
		
		return usuarioDAO;
		
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		Usuario usuario = findByLogin(login);
		
		if(usuario == null){
			
			throw new UsernameNotFoundException("Usuário não encontrado.");
			
		}else{
			
			return usuario;
			
		}
		
	}

	/**
	 * Método responsável por obter um usuário
	 * de acordo com o seu login.
	 * 
	 * @param login
	 * @return
	 */
	public Usuario findByLogin(String login) {
		
		return usuarioDAO.findByLogin(login);
		
	}

}
