package fr.cdamassy2021.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import fr.cdamassy2021.filter.JwtTokenAuthFilter;

//import fr.cdamassy2021.filter.JwtTokenAuthFilter;

/**
 * 
 * @author Soupramanien
 * Web security Configuration
 *
 */
@EnableWebSecurity
public class WebSecurityConfig {
	@Bean
	public BCryptPasswordEncoder bcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
//	@Bean
//	public UserDetailsService userDetailsService() {
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
//		return manager;
//	}
	
	/**
	 * 
	 * @author Soupramanien
	 * Configuration for URLs start with /api/
	 *
	 */
	@Order(1)
	@Configuration
	public static class RestSecurityConfig extends WebSecurityConfigurerAdapter {
		@Autowired
		JwtTokenAuthFilter jwtTokenAuthFilter;
		
		@Autowired
		BCryptPasswordEncoder bcryptPasswordEncoder;
		@Override
		protected void configure(HttpSecurity http) throws Exception {

			System.out.println("pwd:"+bcryptPasswordEncoder.encode("azerty"));
			http
			.mvcMatcher("/api/**");
			// On désactive la protection contre les CSRF.
			// Dans l'état actuel du logiciel, elle est inutile
			// Le probléme est que cette protection est stateful...
			http.csrf().disable();
			// on autorise cors
			http.cors();//cross origin reuest sharing
			// pas de session, on utilise JWT pour ça...
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			// on se débarrasse de la sécurité par défaut.
			http.httpBasic().disable();
			// on désactive aussi la page de login
			http.formLogin().disable();	//chain of reponsibility
			// on met en place le filtre qui va récupérer le token s'il existe et authentifier l'utilisateur.
			http.addFilterBefore(jwtTokenAuthFilter, UsernamePasswordAuthenticationFilter.class);
			// régles d'accès proprement dites
			// vu leur tête, on pourrait aussi les placer au niveau des méthodes, comme annotations.
			http.authorizeRequests()
				.mvcMatchers(HttpMethod.GET,  "/api/public/**").permitAll()
				.mvcMatchers(HttpMethod.POST, "/api/auth/login").permitAll()		
				.mvcMatchers(HttpMethod.POST, "/api/auth/register").permitAll()		
//				.mvcMatchers(HttpMethod.POST, "/api/product/**").access("hasRole('ROLE_EMPLOYE')")
				//.mvcMatchers("/api/product/**").access("hasRole('ROLE_EMPLOYE')")
				//.mvcMatchers(HttpMethod.GET, "/api/employe/**").access("hasRole('ROLE_EMPLOYE')")
				.mvcMatchers("/api/**").authenticated();
		}
		
	}
	/**
	 * 
	 * @author Soupramanien
	 * 
	 * Configuration for other URLs
	 *
	 */
	@Order(2)
	@Configuration
	public static class MvcSecurityConfig extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {

			System.out.println("In MVCSecurityConfig");
			http.authorizeRequests()
				.mvcMatchers("/**").permitAll();
		}
		
	}
	
}
