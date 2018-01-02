package br.com.hotmart.desafiohotmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

/**
 * Classe principal do desafio hotmart, responsável por inicializar a aplicação
 * e subir um tomcat embarcado para a mesma.
 * 
 * @author Tiago
 *
 */
@SpringBootApplication
@EntityScan(basePackageClasses = {DesafioHotmartApplication.class, Jsr310JpaConverters.class})
public class DesafioHotmartApplication {

	/**
	 * Responsável por inicializar a aplicação.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		SpringApplication.run(DesafioHotmartApplication.class, args);
		
	}
	
}
