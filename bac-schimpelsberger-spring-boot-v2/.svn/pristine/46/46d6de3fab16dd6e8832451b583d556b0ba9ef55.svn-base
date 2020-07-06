package at.jku.se.bac.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	JwtAuthenticationFilter jwtTokenFilter() {
		return new JwtAuthenticationFilter();
	}
	
//	@Bean
//	JwtAuthenticationFilterBasic jwtTokenFilterBasic() throws Exception {
//		return new JwtAuthenticationFilterBasic(authenticationManager());
//	}
//	
	
	@Bean
	JwtTokenUtil jwtTokenUtil() {
		return new JwtTokenUtil();
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
		http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
		.and()
		.authorizeRequests().anyRequest().hasRole("ADMIN")
		.and()
		.cors().and().csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
       // .addFilter(jwtTokenFilterBasic())
        .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
        .headers().cacheControl();
        
        
        
				
    }
}
