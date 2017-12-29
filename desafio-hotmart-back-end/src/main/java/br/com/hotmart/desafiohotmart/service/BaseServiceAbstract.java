package br.com.hotmart.desafiohotmart.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.hotmart.desafiohotmart.entity.BaseEntity;

/**
 * Classe base para todos os serviços da 
 * aplicação.
 * 
 * @author Tiago Guimarães da Silva.
 *
 * @param <E>
 * @param <T>
 */
public abstract class BaseServiceAbstract<E extends BaseEntity, T extends Serializable>
implements BaseService<E, T> {
	
	public abstract PagingAndSortingRepository<E, T> getDAO();
	
	@Override
	public E findById(T id) {
		return getDAO().findOne(id);
	}
	
	@Override
	public void delete(E entidade) {
		getDAO().delete(entidade);		
	}
	
	@Override
	public void delete(List<E> listaEntidade) {
		getDAO().delete(listaEntidade);		
	}
	
	@Override
	public E save(E entidade) {
		return getDAO().save(entidade);
	}
}
