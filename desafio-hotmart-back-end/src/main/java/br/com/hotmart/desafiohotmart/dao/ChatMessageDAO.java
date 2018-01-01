package br.com.hotmart.desafiohotmart.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.hotmart.desafiohotmart.entity.ChatMessage;

/**
 * Interface responsável por realizar
 * as operações na base de dados
 * relativas a entidade ChatMessage.
 * 
 * @author Tiago
 *
 */
public interface ChatMessageDAO extends PagingAndSortingRepository<ChatMessage, Long> {

}
