package com.leandro.dscommerce.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


	@Autowired
	private Environment env;
	
	private static final String[] PUBLIC = { "/oauth/token", "/h2-console/**"};

	@Override
	public void configure(HttpSecurity http) throws Exception {

		// H2 frames
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		http.authorizeRequests().antMatchers(PUBLIC).permitAll()
		.antMatchers("/products").permitAll()
		.anyRequest().authenticated();
		
	}
	

}