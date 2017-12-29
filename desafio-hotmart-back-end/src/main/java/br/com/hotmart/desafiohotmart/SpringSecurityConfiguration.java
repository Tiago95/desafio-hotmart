package br.com.hotmart.desafiohotmart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import br.com.hotmart.desafiohotmart.auth.AjaxAuthenticationFailureHandler;
import br.com.hotmart.desafiohotmart.auth.AjaxAuthenticationSuccessHandler;
import br.com.hotmart.desafiohotmart.service.UsuarioService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

	@Autowired
    private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;
	
	/**
	 * Método principal de configuração
	 * do spring security. Configurações
	 * de acesso a url, páginas de login,
	 * etc..
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/login.html").permitAll().and()
		.formLogin()
		.loginPage("/login.html")
		.loginProcessingUrl("/login")
		.usernameParameter("username")
        .passwordParameter("password")
        .successHandler(ajaxAuthenticationSuccessHandler)
        .failureHandler(ajaxAuthenticationFailureHandler).and()
		.logout()
		.deleteCookies("JSESSIONID")
		.invalidateHttpSession(true).and().csrf()
		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		
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
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws AuthenticationException {
		
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
}
