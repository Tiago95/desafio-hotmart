package br.com.hotmart.desafiohotmart.service;

import java.io.Serializable;
import java.util.List;

import br.com.hotmart.desafiohotmart.entity.BaseEntity;

/**
 * Interface base de todos os serviços 
 * da aplicação.
 * 
 * @author Tiago Guimarães da Silva.
 *
 * @param <E>
 * @param <T>
 */
public interface BaseService<E extends BaseEntity, T extends Serializable> {
	
	/**
	 * Método responsável por obter
	 * uma entidade por um determinado
	 * id.
	 * 
	 * @param id
	 * @return Entidade (E)
	 */
	public E findById(T id);
	
	/**
	 * Método responsável por 
	 * remover uma entidade.
	 * 
	 * @param entidade
	 */
	public void delete(E entidade);
	
	/**
	 * Método responsável por remover
	 * uma lista de entidades.
	 * 
	 * @param listaEntidade
	 */
	public void delete(List<E> listaEntidade);
	
	/**
	 * Método responsável por salvar/atualizar
	 * uma entidade.	 *
	 * 
	 * @param entidade
	 * @return Entidade (E)
	 */
	public E save(E entidade);
}
