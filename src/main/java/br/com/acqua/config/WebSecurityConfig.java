package br.com.acqua.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.acqua.config.security.CustonUserDetailsService;
import br.com.acqua.config.security.MyAccessDeniedHandler;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustonUserDetailsService userDetailsService;
	
	@Autowired
	private MyAccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/css/**","/js/**","/imagens/**").permitAll()
			.antMatchers("/usuarios").hasAnyRole("USER", "ADMIN")
			.antMatchers("/index").hasAnyRole("USER", "ADMIN")
			.antMatchers("/produtos").hasAnyRole("USER", "ADMIN")
			.antMatchers("/produto/perfil/**").hasAnyRole("USER", "ADMIN")
			.antMatchers("/movimentacoes/**").hasAnyRole("USER", "ADMIN")
			.anyRequest()
			.authenticated()
		.and()
		.formLogin()
			.loginPage("/login")
			.permitAll()
		.and()
			.logout()
			.logoutSuccessUrl("/login?logout")
			.permitAll()
			.and()
				.rememberMe()
				.userDetailsService(userDetailsService)
			.and()
				.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

	}
}