package com.github.perryvaldez.seebooks.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.perryvaldez.seebooks.config.impl.DbUserDetailsService;
import com.github.perryvaldez.seebooks.datalayer.impl.jpa.JpaUserRepository;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()
	            .antMatchers("/", "/css/*").permitAll()
	            .anyRequest().authenticated()
	            .and()
	        .formLogin()
	            .loginPage("/login")
	            .permitAll()
	            .and()
	        .logout()
	            .logoutSuccessUrl("/loggedout")
	            .invalidateHttpSession(true)
	            .deleteCookies("JSESSIONID")
	            .permitAll()
	    ;
	}
   
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
	@Value("${spring.datasource.url}")
	private String dataSourceUrl;
	
	@Value("${spring.datasource.driver-class-name}")
	private String dataDriverClass;
	
	@Value("${spring.datasource.username}")
	private String dataUsername;
	
	@Value("${spring.datasource.password}")
	private String dataPassword;
	
	public DataSource dataSource()
	{
		var dbu = DataSourceBuilder
				.create()
				.username(dataUsername)
				.password(dataPassword)
				.url(dataSourceUrl)
				.driverClassName(dataDriverClass)
				.build()
				;
		
		return dbu;
	}
	   
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder, JpaUserRepository userRepository) {
    	return new DbUserDetailsService(this.dataSource(), userRepository);
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, @Qualifier("dbUserDetailsService") UserDetailsService uds) throws Exception {
        auth.userDetailsService(uds)          
        ;
    }
    
}
