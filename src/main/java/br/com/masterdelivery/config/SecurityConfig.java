package br.com.masterdelivery.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import br.com.masterdelivery.security.JWTAuthenticationFilter;
import br.com.masterdelivery.security.JWTAuthorizationFilter;
import br.com.masterdelivery.security.JWTUtil;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	private static final String[] PUBLIC_MATCHERS = {
			"/swagger-ui.html/**", 
			"/configuration/**", 
			"/swagger-resources/**", 
			"/v2/api-docs","/webjars/**",
			"/h2-console/**"
	};

	private static final String[] PUBLIC_MATCHERS_GET = {
			
	};

	private static final String[] PUBLIC_MATCHERS_POST = {
			"/usuario/realizarcadastro/**",
			"/usuario/recuperarsenha/**"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers(PUBLIC_MATCHERS)
			.permitAll()
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST)
			.permitAll()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET)
			.permitAll()
			.anyRequest()
			.authenticated();
		
		
		//desabilita a sessão de usuário
		http.cors().and().csrf().disable();
		
		//certifica-se que o back-end não vai criar sessão de usuário
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	/*
	 * Por padrão o Cors não aceita requisições de multiplas fontes, então
	 * utilizamos esse metodo abaixo para aceitar.
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	/* Algoritmo de encode de senha */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
