package br.com.hotmart.desafiohotmart.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe utilitária para formatações em geral.
 * 
 * @author Tiago
 *
 */
public class FormatterUtils {
	
	private static final String FORMATTER_DEFAULT_DATE_TIME = "dd-MM-yyyy HH:mm:ss";

	/**
	 * Construtor default privado por ser uma classe utilitária.
	 * 
	 */
	private FormatterUtils() {
		super();
	}
	
	/**
	 * Responsável por converte uma data e hora no formato padrão brasileiro.
	 * 
	 * @param localDateTime
	 * @return
	 */
	public static String formatLocalDateTime(LocalDateTime localDateTime){
		
		if(localDateTime != null){
			
			return localDateTime.format(DateTimeFormatter.ofPattern(FORMATTER_DEFAULT_DATE_TIME));
			
		}
		
		return null;
		
	}

}
