package at.jku.se.bac.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtException;


public class JwtAuthenticationFilter extends OncePerRequestFilter{

	
	Logger log =LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String bearer=request.getHeader("Authorization");
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

				List<SimpleGrantedAuthority>authRoles=roles.stream().map(x->new SimpleGrantedAuthority(x)).collect(Collectors.toList());
				Authentication auth=new UsernamePasswordAuthenticationToken(username,null,authRoles);
				SecurityContextHolder.getContext().setAuthentication(auth);

			}catch(JwtException e) {
				log.warn("doFilter: caught JwtException of instance: {}",e.getClass());
			}			
		}
		filterChain.doFilter(request, response);
		
	}

}
