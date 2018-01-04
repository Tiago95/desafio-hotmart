package br.com.hotmart.desafiohotmart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.com.hotmart.desafiohotmart.service.UsuarioService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UsuarioService usuarioService;
	
	/**
	 * Método principal de configuração
	 * do spring security. Configurações
	 * de acesso a url, páginas de login,
	 * etc..
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().authorizeRequests()
		.antMatchers("/auth/user", "/auth/register", "/chat/**").permitAll()
		.anyRequest().permitAll()
		.and()
		.logout().permitAll()
		.and()
		.csrf().disable()
		.httpBasic()
		.and()
		.logout().permitAll()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
		.and()
		.csrf().disable();
		
	}
	
	/**
	 * Método responsável por regitrar o tipo 
	 * de autenticaçao customizado do spring 
	 * security.
	 * 
	 * @param auth
	 * @throws AuthenticationException
	 */
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) {
		
		auth.authenticationProvider(authenticationProvider());
		
	}
	
	/**
	 * Classe utilizada para criptografar a 
	 * senha do usuário do siscond.
	 * 
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * Método responsável por criar um 
	 * DaoAuthenticationProvider para registrar
	 * a customização da autenticação
	 * do spring security.
	 * 
	 * @return
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		
		authenticationProvider.setUserDetailsService(usuarioService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		authenticationProvider.setHideUserNotFoundExceptions(false);
		
		return authenticationProvider;
	}
	
	/**
	 * Configuração do CORS para aceitar requisições de localhost nas portas
	 * 4200 e 4201.
	 * 
	 * @return
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurerAdapter() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**").allowedOrigins("http://localhost:4200", "http://localhost:4201", "*");	          
	        }
	    };
	}
}
