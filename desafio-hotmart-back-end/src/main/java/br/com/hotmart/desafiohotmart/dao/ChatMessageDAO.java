package br.com.hotmart.desafiohotmart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.hotmart.desafiohotmart.entity.ChatMessage;
import br.com.hotmart.desafiohotmart.entity.Usuario;
import br.com.hotmart.desafiohotmart.vo.ChatMessageVO;
import br.com.hotmart.desafiohotmart.vo.UsuarioVO;

/**
 * Interface responsável por realizar
 * as operações na base de dados
 * relativas a entidade ChatMessage.
 * 
 * @author Tiago
 *
 */
public interface ChatMessageDAO extends PagingAndSortingRepository<ChatMessage, Long> {

	/**
	 * Responsável por obter os contatos que possuem mensagens com o usuário.
	 * 
	 * @param idUser
	 * @return
	 */
	@Query(nativeQuery = true, name = "SqlFindContatosChatInfoByIdUser")
	List<UsuarioVO> findContatosChatInfoByIdUser(@PathVariable("idUser") Long idUser);

	/**
	 * Responsável por retornar uma lista de mensagens trocadas entre dois usuários.
	 * 
	 * @param usuarioOrigem
	 * @param usuarioDestino
	 * @return
	 */
	@Query("SELECT new br.com.hotmart.desafiohotmart.vo.ChatMessageVO(cm.usuarioOrigem.id, cm.usuarioOrigem.nome, cm.message, cm.sendDate) FROM ChatMessage cm WHERE (cm.usuarioOrigem = :usuarioOrigem AND cm.usuarioDestino = :usuarioDestino) OR (cm.usuarioOrigem = :usuarioDestino AND cm.usuarioDestino = :usuarioOrigem)")
	List<ChatMessageVO> findMessagesByUserOrigemAndUserDestino(@PathVariable("usuarioOrigem") Usuario usuarioOrigem, @PathVariable("usuarioOrigem") Usuario usuarioDestino);

}
