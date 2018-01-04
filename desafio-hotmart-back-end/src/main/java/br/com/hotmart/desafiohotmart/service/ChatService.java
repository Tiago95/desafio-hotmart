package br.com.hotmart.desafiohotmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import br.com.hotmart.desafiohotmart.dao.ChatMessageDAO;
import br.com.hotmart.desafiohotmart.entity.ChatMessage;
import br.com.hotmart.desafiohotmart.entity.Usuario;
import br.com.hotmart.desafiohotmart.vo.ChatInfoVO;
import br.com.hotmart.desafiohotmart.vo.ChatMessageVO;
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
		
		ChatInfoVO chatInfoVO = new ChatInfoVO();
		
		List<UsuarioVO> usuariosComMensagens = chatMessageDAO.findContatosChatInfoByIdUser(idUser);
		
		chatInfoVO.setUsuariosComMensagens(usuariosComMensagens);
		
		if(usuariosComMensagens != null && !usuariosComMensagens.isEmpty()){
			
			List<ChatMessageVO> listaChatMessageVO = chatMessageDAO.findMessagesByUserOrigemAndUserDestino(new Usuario(idUser), idUserActive != null ? new Usuario(idUserActive) : usuariosComMensagens.iterator().next().toUsuario());
			
			chatInfoVO.setMensagensAtivas(listaChatMessageVO);
		}

		return chatInfoVO;
		
	}

}
