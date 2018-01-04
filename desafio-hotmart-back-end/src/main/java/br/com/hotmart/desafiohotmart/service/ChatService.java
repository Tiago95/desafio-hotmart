package br.com.hotmart.desafiohotmart.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import br.com.hotmart.desafiohotmart.dao.ChatMessageDAO;
import br.com.hotmart.desafiohotmart.entity.ChatMessage;
import br.com.hotmart.desafiohotmart.entity.Usuario;
import br.com.hotmart.desafiohotmart.vo.ChatInfoVO;
import br.com.hotmart.desafiohotmart.vo.ChatMessageVO;
import br.com.hotmart.desafiohotmart.vo.LastChatInfoVO;
import br.com.hotmart.desafiohotmart.vo.UsuarioVO;

/**
 * Serviço responsável por manipular
 * as regras de negócio da entidade
 * chat message.
 * 
 * @author Tiago
 *
 */
@Service
public class ChatService extends BaseServiceAbstract<ChatMessage, Long> {
	
	@Autowired
	private ChatMessageDAO chatMessageDAO;
	
	@Autowired
	private UsuarioBloqueadoService usuarioBloqueadoService;

	@Override
	public PagingAndSortingRepository<ChatMessage, Long> getDAO() {
		return chatMessageDAO;
	}

	/**
	 * Responsável por retornar a lista de contatos/mensagens de um determiando usuário e destinatário.
	 * 
	 * @param idUser
	 * @param idUserActive
	 * @return
	 */
	public ChatInfoVO getChatInfoByIdUser(Long idUser, Long idUserActive) {		
		
		List<UsuarioVO> usuariosComMensagens = chatMessageDAO.findContatosChatInfoByIdUser(idUser);		
		
		if(usuariosComMensagens != null && !usuariosComMensagens.isEmpty()){
			
			ChatInfoVO chatInfoVO = new ChatInfoVO();
			
			chatInfoVO.setUsuariosComMensagens(usuariosComMensagens);
			
			List<ChatMessageVO> listaChatMessageVO = chatMessageDAO.findMessagesByUserOrigemAndUserDestino(new Usuario(idUser), idUserActive != null ? new Usuario(idUserActive) : usuariosComMensagens.iterator().next().toUsuario());
			
			chatInfoVO.setMensagensAtivas(listaChatMessageVO);
			
			return chatInfoVO;
		}

		return null;
		
	}
	
	/**
	 * Responsável por retornar uma lista de mensagens trocadas entre usuários.
	 * 
	 * @param idUsuarioOrigem
	 * @param idUsuarioDestino
	 * @return
	 */
	public List<ChatMessageVO> findMessagesByUserOrigemAndUserDestino(Long idUsuarioOrigem, Long idUsuarioDestino) {
		
		if(idUsuarioOrigem != null && idUsuarioDestino != null){
			
			return chatMessageDAO.findMessagesByUserOrigemAndUserDestino(new Usuario(idUsuarioOrigem), new Usuario(idUsuarioDestino));
			
		}
		
		return new ArrayList<>();
		
	}

	/**
	 * Responsável por obter as últimas informações do chat.
	 * 
	 * @param idUsuarioOrigem
	 * @return
	 */
	public LastChatInfoVO getLastChatInfo(Long idUsuarioOrigem) {
		
		if(idUsuarioOrigem != null){
			
			Usuario usuarioDestino = new Usuario(idUsuarioOrigem);
			
			LastChatInfoVO lastChatInfoVO = new LastChatInfoVO();
			
			lastChatInfoVO.setQuantidadeMensagensNaoLidas(chatMessageDAO.countByUsuarioDestinoAndLida(usuarioDestino, false));
			
			lastChatInfoVO.setUltimasMensagensRecebidas(chatMessageDAO.findUltimasMensagensRecebidasNaoLidasByUsuarioDestino(usuarioDestino));
			
			return lastChatInfoVO;
			
		}
		
		return null;		
		
	}
	
	@Override
	public ChatMessage save(ChatMessage chatMessage) {
		
		if(chatMessage != null && chatMessage.getUsuarioOrigem() != null
				&& chatMessage.getUsuarioDestino() != null
				&& usuarioBloqueadoService.countByUsuarioPrincipalAndUsuarioBloqueado(chatMessage.getUsuarioDestino(), chatMessage.getUsuarioOrigem()) <= 0){
			
			return super.save(chatMessage);
			
		}
		
		return null;
		
	}

	/**
	 * Responsável por atualizar o estado de recebida da mensagem
	 * 
	 * @param idsChatMensagem
	 * @return
	 */
	@Transactional
	public void atualizarMensagemRecebida(Long idChatMensagem) {

		if(idChatMensagem != null){
			
			atualizarMensagemRecebida(Arrays.asList(idChatMensagem));
			
		}
		
	}
	
	/**
	 * Responsável por atualizar o estado de recebida da mensagem
	 * 
	 * @param idsChatMensagem
	 * @return
	 */
	@Transactional
	public void atualizarMensagemRecebida(List<Long> idsChatMensagem) {

		if(idsChatMensagem != null && !idsChatMensagem.isEmpty()){
			
			chatMessageDAO.atualizarMensagemRecebida(idsChatMensagem);
			
		}
		
	}
	
	/**
	 * Responsável por atualizar o estado de lida da mensagem
	 * 
	 * @param idsChatMensagem
	 * @return
	 */
	@Transactional
	public void atualizarMensagemLida(Long idChatMensagem) {

		if(idChatMensagem != null){
			
			atualizarMensagemRecebida(Arrays.asList(idChatMensagem));
			
		}
		
	}
	
	/**
	 * Responsável por atualizar o estado de lida da mensagem
	 * 
	 * @param idsChatMensagem
	 * @return
	 */
	@Transactional
	public void atualizarMensagemLida(List<Long> idsChatMensagem) {

		if(idsChatMensagem != null && !idsChatMensagem.isEmpty()){
			
			chatMessageDAO.atualizarMensagemLida(idsChatMensagem);
			
		}
		
	}

}
