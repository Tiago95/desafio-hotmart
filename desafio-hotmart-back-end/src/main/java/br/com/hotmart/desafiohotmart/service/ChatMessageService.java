package br.com.hotmart.desafiohotmart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import br.com.hotmart.desafiohotmart.dao.ChatMessageDAO;
import br.com.hotmart.desafiohotmart.entity.ChatMessage;

/**
 * Serviço responsável por manipular
 * as regras de negócio da entidade
 * chat message.
 * 
 * @author Tiago
 *
 */
@Service
public class ChatMessageService extends BaseServiceAbstract<ChatMessage, Long> {
	
	@Autowired
	private ChatMessageDAO chatMessageDAO;

	@Override
	public PagingAndSortingRepository<ChatMessage, Long> getDAO() {
		return chatMessageDAO;
	}

}
