package br.com.hotmart.desafiohotmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal do desafio hotmart, responsável por inicializar a aplicação
 * e subir um tomcat embarcado para a mesma.
 * 
 * @author Tiago
 *
 */
@SpringBootApplication
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
