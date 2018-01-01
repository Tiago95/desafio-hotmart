package br.com.hotmart.desafiohotmart.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

/**
 * Classe responsável por conter as configurações de data source.
 * 
 * @author Tiago
 *
 */
@Configuration
public class DataSourceConfiguration {

	private static final int IDLE_TIMEOUT = 300000;
	private static final int CONNETION_TIME_OUT = 30000;
	private static final int MAX_LIFE_TIME = 1200000;
	private static final int MAX_POOL_SIZE = 200;
    
	/**
	 * Declaração do DataSource
	 * da aplicação junto ao poll
	 * de conexões do Hikari.
	 * 
	 * @return DataSource
	 */
	@Bean
    public DataSource dataSource() {
		
		HikariDataSource hikariDataSource = new HikariDataSource();
		
		hikariDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/desafio-hotmart?autoReconnect=true&useSSL=false");
		hikariDataSource.setUsername("desafio-hotmart");
		hikariDataSource.setPassword("desafio-hotmart");
		hikariDataSource.setPoolName("DesafioHotmartPoolConnection");
		hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE);
		hikariDataSource.setMaxLifetime(MAX_LIFE_TIME);
		hikariDataSource.setConnectionTimeout(CONNETION_TIME_OUT);
		hikariDataSource.setIdleTimeout(IDLE_TIMEOUT);
		
        return hikariDataSource;
        
    }	
	
}
