package com.example.demo;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import com.example.demo.model.Publicacion;
import com.example.demo.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class config {
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.csrf(csrf -> csrf.ignoringRequestMatchers("/","/logeo","/registrarUsuario","/main","/publiUser/{id}","/cambiarPubli/{id}", "/hello","/video"))
		.authorizeHttpRequests(requests -> requests
					.requestMatchers("/","/logeo","/registrarUsuario","/chats","/hello","/video").permitAll()
				.requestMatchers("/main","/publiUser","/publiUser/{id}","/cambiarPubli/{id}").authenticated()
				.anyRequest().permitAll()
				)
			.formLogin(withDefaults())
			.httpBasic(withDefaults())
			.logout((logout) -> logout.permitAll());
		return http.build();
	}
	
/*	@Bean
	public UserDetailsService userDetailsService() {
		
		var user = User.builder()
						.username("admin")
						.password(passwordEncoder().encode("admin"))
						.roles("USER")
						.build();
		return new InMemoryUserDetailsManager(user);
	}
	*/
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    	daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    	daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    	return daoAuthenticationProvider;
    }
	  @Bean
	  public PasswordEncoder passwordEncoder(){
	  	return new BCryptPasswordEncoder();
	  }
}
