package br.com.hotmart.desafiohotmart.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

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
	List<UsuarioVO> findContatosChatInfoByIdUser(@Param("idUser") Long idUser);

	/**
	 * Responsável por retornar uma lista de mensagens trocadas entre dois usuários.
	 * 
	 * @param usuarioOrigem
	 * @param usuarioDestino
	 * @return
	 */
	@Query("SELECT new br.com.hotmart.desafiohotmart.vo.ChatMessageVO(cm.id, cm.usuarioOrigem.id, cm.usuarioOrigem.nome, cm.message, cm.sendDate) FROM ChatMessage cm WHERE (cm.usuarioOrigem = :usuarioOrigem AND cm.usuarioDestino = :usuarioDestino) OR (cm.usuarioOrigem = :usuarioDestino AND cm.usuarioDestino = :usuarioOrigem) ORDER BY cm.sendDate ASC")
	List<ChatMessageVO> findMessagesByUserOrigemAndUserDestino(@Param("usuarioOrigem") Usuario usuarioOrigem, @Param("usuarioDestino") Usuario usuarioDestino);

	/**
	 * Responsável por obter a quantidades de mensagens de acordo com um determinado usuário destino e a flag lida.
	 * 
	 * @param usuarioDestino
	 * @param lida
	 * @return
	 */
	Long countByUsuarioDestinoAndLida(Usuario usuarioDestino, boolean lida);

	/**
	 * Responsável por retornar todas as mensagens não lidas por um usuário de destino.
	 * 
	 * @param usuarioDestino
	 * @return
	 */
	@Query("SELECT new br.com.hotmart.desafiohotmart.vo.ChatMessageVO(cm.id, cm.usuarioOrigem.id, cm.usuarioOrigem.nome, cm.message, cm.sendDate) FROM ChatMessage cm WHERE cm.usuarioDestino = :usuarioDestino AND cm.lida = false ORDER BY cm.sendDate DESC")
	List<ChatMessageVO> findUltimasMensagensRecebidasNaoLidasByUsuarioDestino(@Param("usuarioDestino") Usuario usuarioDestino);

	/**
	 * Responsável por atualizar o estado de recebida da mensagem
	 * 
	 * @param idsChatMensagem
	 * @return
	 */
	@Transactional
	@Modifying
	@Query("UPDATE ChatMessage SET recebida = true WHERE id IN (:idsChatMensagem)")
	void atualizarMensagemRecebida(@Param("idsChatMensagem") List<Long> idsChatMensagem);
	
	/**
	 * Responsável por atualizar o estado de lida da mensagem
	 * 
	 * @param idsChatMensagem
	 * @return
	 */
	@Transactional
	@Modifying
	@Query("UPDATE ChatMessage SET lida = true WHERE id IN (:idsChatMensagem)")
	void atualizarMensagemLida(@Param("idsChatMensagem") List<Long> idsChatMensagem);

}
