package com.app.BookMe.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

import static com.app.BookMe.security.SecurityConstants.SIGN_IN_URL;
import static com.app.BookMe.security.SecurityConstants.SIGN_UP_URL;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	private UserDetailsService userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;

	public WebSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.POST, SIGN_IN_URL,SIGN_UP_URL).permitAll()
				.antMatchers(HttpMethod.GET, SIGN_IN_URL,SIGN_UP_URL).permitAll()
				.anyRequest().authenticated().and().addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager()))
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select t.email, t.password, 1 from " +
                        "(select email, password from funcionario " +
                        "union all " +
                        "select email, password from requisitante " +
						"union all " +
						"select email, password from administrador " +
                        "union all " +
                        "select email, password from responsavel) t where t.email=?")
                .authoritiesByUsernameQuery(
                        "select t.email, CONCAT(t.tipo,',',t.id) from (select email, 'Funcionario' as tipo, id from funcionario " +
                                "union all " +
                                "select email, 'Requisitante' as tipo, id from requisitante " +
								"union all " +
								"select email, 'Administrador' as tipo, id from administrador " +
                                "union all " +
                                "select email, 'Responsavel' as tipo, id from responsavel) t where t.email=?")
                .passwordEncoder(bCryptPasswordEncoder);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		System.out.println("setting cors configuration");
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.applyPermitDefaultValues();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

}