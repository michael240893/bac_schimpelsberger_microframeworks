package at.jku.se.bac.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;



public class JwtTokenUtil {
		
	@Value("${at.jku.se.jwt.secret}")
	private String secret;
    

	
    public String getSubjectFromToken(String token) {
        return getClaimFromToken(token, c->c.getSubject());
    }
    
    public String getIssuerFromToken(String token) {
        return getClaimFromToken(token, c->c.getIssuer());
    }
    

    public Date getIssuedAtFromToken(String token) {
        return getClaimFromToken(token, c->c.getIssuedAt());
    }

    public Date getExpirationFromToken(String token) {
        return getClaimFromToken(token, c->c.getExpiration());
    }
    
    @SuppressWarnings("unchecked")
	public List<String> getRolesFromToken(String token){
    	return getClaimFromToken(token, c->c.get("roles",ArrayList.class));
    	
    }
    
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    		final Claims claims = getAllClaimsFromToken(token);
    		return claimsResolver.apply(claims);
    }
    
    private Claims getAllClaimsFromToken(String token) {	
        	Claims claims= Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        	return claims;
    }
    

}