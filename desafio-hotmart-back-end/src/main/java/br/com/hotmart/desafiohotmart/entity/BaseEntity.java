package br.com.hotmart.desafiohotmart.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Classe base das entidades.
 * 
 * @author Tiago
 *
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 6525214743655583132L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sobrescrita do método equals.
	 * 
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BaseEntity)) {
			return false;
		}
		BaseEntity modelo = (BaseEntity) obj;
		if (modelo.getId() == null) {
			return false;
		}
		return modelo.getId().equals(this.getId());
	}

	/**
	 * Sobrescrita do método hashCode.
	 * 
	 */
	@Override
	public int hashCode() {

		if (getId() != null) {
			
			return getId().hashCode();
			
		} else {
			
			return 0;
			
		}
		
	}

}
