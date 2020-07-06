package bac.schimpelsberger.micronaut.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.JwtException;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.HttpAttributes;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.FilterChain;
import io.micronaut.http.filter.HttpFilter;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationUserDetailsAdapter;
import io.micronaut.security.authentication.UserDetails;

@Filter("/**")
public class JwtAuthenticationFilter implements HttpFilter{

	
	Logger log =LoggerFactory.getLogger(this.getClass());

	private final JwtTokenUtil jwtTokenUtil;
	
	
	
	public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
		this.jwtTokenUtil = jwtTokenUtil;
	}



	@Override
	public Publisher<? extends HttpResponse<?>> doFilter(HttpRequest<?> request, FilterChain chain) {
		String bearer=request.getHeaders().get("Authorization");
		log.debug("doFilter: found bearer {}",bearer);
		if (!StringUtils.isEmpty(bearer)&&bearer.length()>7) {
			String token=bearer.substring(7);
			log.debug("doFilter: extracted token {}",token);
	
			try {
				String username=jwtTokenUtil.getSubjectFromToken(token);
				List<String>roles=jwtTokenUtil.getRolesFromToken(token);
				
				if (roles==null) {
					roles=new ArrayList<String>();
				}

				UserDetails userDetails=new UserDetails(username,roles);		
			    Authentication authentication = new AuthenticationUserDetailsAdapter(userDetails, "roles");
			    request.setAttribute(HttpAttributes.PRINCIPAL, authentication);
							
			}catch(JwtException e) {
				log.warn("doFilter: caught JwtException of instance: {}",e.getClass());
			}			
		}
					
		return chain.proceed(request);
	}

}
