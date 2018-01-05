package br.com.hotmart.desafiohotmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Classe principal do desafio hotmart, responsável por inicializar a aplicação
 * e subir um tomcat embarcado para a mesma.
 * 
 * @author Tiago
 *
 */
@SpringBootApplication
@EntityScan(basePackageClasses = {DesafioHotmartApplication.class, Jsr310JpaConverters.class})
@EnableSwagger2
public class DesafioHotmartApplication {

	/**
	 * Responsável por inicializar a aplicação.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		SpringApplication.run(DesafioHotmartApplication.class, args);
		
	}
	
    /**
     * Responsável por criar um bean do swagger
     * 
     * @return
     */
    @Bean
    public Docket newsApi() {
    	
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("br.com.hotmart.desafiohotmart"))              
                .build();  
        
    }
     
    /**
     * Responsável por criar um ApiInfo
     * 
     * @return
     */
    private ApiInfo apiInfo() {
    	
        return new ApiInfoBuilder().title("Desafio Hotmart - Chat")
                .description("Esse desafio tem como objetivo montar um chat com websocket entre usuários.")
                .contact("Tiago Guimarães da Silva").version("1.0").build();
        
    }
	
}
