package com.github.perryvaldez.seebooks.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.perryvaldez.seebooks.config.impl.DbUserDetailsService;
import com.github.perryvaldez.seebooks.services.SecurityService;
import com.github.perryvaldez.seebooks.services.SecurityUtil;
import com.github.perryvaldez.seebooks.services.UserService;
import com.github.perryvaldez.seebooks.services.impl.DefaultSecurityUtil;
import com.github.perryvaldez.seebooks.services.impl.DefaultSecurityService;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger(SecurityConfig.class);
	
	@Value("${spring.datasource.url}")
	private String dataSourceUrl;
	
    @Override
	protected void configure(HttpSecurity http) throws Exception {   	
    	ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry httpInit;
    	
    	if(dataSourceUrl.startsWith("jdbc:h2:mem:")) {
    		httpInit = http.csrf().disable()
    				       .authorizeRequests()
    				       .antMatchers("/h2-console/*").permitAll()
    				       ;
    	} else {
    		httpInit = http.csrf().ignoringAntMatchers("/api/**")
    				       .and()
    				       .authorizeRequests()
    				       ;
    	}

	    httpInit
	            .antMatchers("/", "/css/*", "/js/*", "/images/*", "/loggedout").permitAll()
	            .antMatchers("/admin/users/**").access("@securityService.matchPrivilege(authentication, { '*', 'can_manage', 'user', '*' })")
	            .antMatchers("/admin/businesses/**").access("@securityService.matchPrivilege(authentication, { '*', 'can_manage', 'business', '*' })")
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
      
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder, UserService userService) {
    	return new DbUserDetailsService(userService);
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, @Qualifier("dbUserDetailsService") UserDetailsService uds) throws Exception {
        auth.userDetailsService(uds)          
        ;
    }
    
    @Bean
    public SecurityService securityService() {
    	return new DefaultSecurityService(new DefaultSecurityUtil());
    }
    
    @Bean
    public SecurityUtil securityUtil() {
    	return new DefaultSecurityUtil();
    }
}
