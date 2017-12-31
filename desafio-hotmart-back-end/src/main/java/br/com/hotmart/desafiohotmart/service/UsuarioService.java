package br.com.hotmart.desafiohotmart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.hotmart.desafiohotmart.dao.UsuarioDAO;
import br.com.hotmart.desafiohotmart.entity.Usuario;
import br.com.hotmart.desafiohotmart.enumerations.StatusUsuarioEnum;
import br.com.hotmart.desafiohotmart.exception.FieldServiceException;
import br.com.hotmart.desafiohotmart.exception.ServiceException;

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
	
	private static final String EMAIL_JA_EXISTENTE = "E-mail já existente";

	private static final String EMAIL = "email";

	private static final String NICK_NAME_JA_EXISTENTE = "Nick name já existente";

	private static final String NICK = "nick";

	private static final String USUARIO_NAO_PODE_SER_NULO = "O usuário não pode ser nulo";

	private static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado.";

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public PagingAndSortingRepository<Usuario, Long> getDAO() {
		
		return usuarioDAO;
		
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

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		Usuario usuario = findByLogin(login);
		
		if(usuario == null){
			
			throw new UsernameNotFoundException(USUARIO_NAO_ENCONTRADO);
			
		}else{
			
			return usuario;
			
		}
		
	}
	
	/**
	 * Responsável por gerar um novo usuário.
	 * 
	 * @param usuario
	 * @return
	 * @throws ServiceException 
	 */
	public Usuario registerNewUserAccount(Usuario usuario) throws ServiceException{
		
		if(usuario != null){
			
			try{
				
				validateUsuario(usuario);
				
				usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
				usuario.setStatusUsuario(StatusUsuarioEnum.ATIVO);
				
				return usuarioDAO.save(usuario);
				
			}catch(Exception e){
				
				throw new ServiceException(e);
				
			}		
			
		}
		
		throw new ServiceException(USUARIO_NAO_PODE_SER_NULO);
		
	}

	/**
	 * Responsável por validar um usuário.
	 * 
	 * @param usuario
	 * @throws ServiceException
	 */
	private void validateUsuario(Usuario usuario) throws ServiceException {
		
		if(usuario != null){
			
			validateExistingEmail(usuario);
			
			validateExistingNickName(usuario);
			
		}else{
			
			throw new ServiceException(USUARIO_NAO_PODE_SER_NULO);
			
		}		
		
	}

	/**
	 * Responsável por validar se já existe um nick name cadastrado no banco.
	 * 
	 * @param usuario
	 * @throws FieldServiceException
	 */
	private void validateExistingNickName(Usuario usuario) throws FieldServiceException {
		
		if(usuarioDAO.countByNickName(usuario.getNickName()) > 0){
			
			throw new FieldServiceException(NICK, NICK_NAME_JA_EXISTENTE);
			
		}
		
	}

	/**
	 * Responsável por validar se já existe um e-mail cadastrado no banco.
	 * 
	 * @param usuario
	 * @throws FieldServiceException
	 */
	private void validateExistingEmail(Usuario usuario) throws FieldServiceException {
		
		if(usuarioDAO.countByLogin(usuario.getLogin()) > 0){
			
			throw new FieldServiceException(EMAIL, EMAIL_JA_EXISTENTE);
			
		}
		
	}
	
}
