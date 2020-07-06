package bac.schimpelsberger.micronaut.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import bac.schimpelsberger.micronaut.dto.AddUserDto;
import bac.schimpelsberger.micronaut.dto.EditUserDto;
import bac.schimpelsberger.micronaut.entity.Authority;
import bac.schimpelsberger.micronaut.entity.User;
import bac.schimpelsberger.micronaut.entity.UserAuthority;
import bac.schimpelsberger.micronaut.entity.UserAuthorityId;
import bac.schimpelsberger.micronaut.repository.AuthorityRepository;
import bac.schimpelsberger.micronaut.repository.UserAuthorityRepository;
import bac.schimpelsberger.micronaut.repository.UserRepository;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;

@Singleton
public class UserService {
	
	protected final UserRepository userRepository;
	protected final AuthorityRepository authorityRepository;
	protected final UserAuthorityRepository userAuthorityRepository;

	public UserService(UserRepository userRepository, AuthorityRepository authorityRepository, UserAuthorityRepository userAuthorityRepository) { 
    	this.userRepository = userRepository;
    	this.authorityRepository=authorityRepository;
    	this.userAuthorityRepository=userAuthorityRepository;
    }

	   @Transactional
	    public User registerNewUserAccount(final AddUserDto addUserDto) {
	        if (usernameExists(addUserDto.getUsername())) {
	        	throw new HttpStatusException(HttpStatus.BAD_REQUEST,"username already exists");
	        }
	        if (emailExists(addUserDto.getEmail())) {   
	        	throw new HttpStatusException(HttpStatus.BAD_REQUEST,"email already exists");
	        }
	        final User user = new User();
	        user.setUsername(addUserDto.getUsername());
	        user.setFirstName(addUserDto.getFirstName());
	        user.setLastName(addUserDto.getLastName());
	        user.setPassword(addUserDto.getPassword());
	        user.setEmail(addUserDto.getEmail());
	        user.setEnabled(addUserDto.getEnabled());
	        return userRepository.save(user);
	    }
	   
	    @Transactional
	    public User updateUserAccount(final EditUserDto editUserDto) {
	        
	    	User user=userRepository.findById(editUserDto.getUsername()).orElse(null);
	    	if (user==null)throw new HttpStatusException(HttpStatus.NOT_FOUND,"User not found");
	    	
	    	if (!user.getEmail().equals(editUserDto.getEmail())) {
	    		if (userRepository.findByEmail(editUserDto.getEmail()).isPresent()) {
	            	throw new HttpStatusException(HttpStatus.BAD_REQUEST,"email already exists");
	    		}
	    	}
	    	
	        user.setUsername(editUserDto.getUsername());
	        user.setFirstName(editUserDto.getFirstName());
	        user.setLastName(editUserDto.getLastName());
	        user.setEmail(editUserDto.getEmail());
	        user.setEnabled(editUserDto.getEnabled());
	        return userRepository.update(user);
	    }
	    
	    public List<User>findAll(){
	    	return userRepository.findAll();
	    }
	    
	    @Transactional
	    public void deleteUser(String username) {
	    	userRepository.findById(username).orElseThrow(()->new HttpStatusException(HttpStatus.NOT_FOUND,"username not found"));
	    	userRepository.deleteById(username);
	    }
	    
	    public User findById(String username) {
	        return userRepository.findById(username).orElse(null);
	    }
	    
	    
	    public User findUserByEmail(String email){
	        return userRepository.findByEmail(email).orElse(null);
	     }
	    
	    
	    public User saveUser(User user) {
	    	return userRepository.save(user);
	    }
	    
	    
	    private boolean emailExists(final String email) {
	        return userRepository.findByEmail(email).orElse(null) != null;
	    }
	    
	    private boolean usernameExists(final String username) {
	        return userRepository.findById(username).orElse(null) != null;
	    }
	    
		
		public List<Authority>findUnassignedAuthorities(String username){
			return authorityRepository.findUnassignedAuthorities(username);
		}


	    private boolean authorityExists(Authority authority) {
	    	return authorityRepository.findById(authority.getId())!=null;
	    }

		public Set<Authority> findAuthoritiesOfUser(String username) {
			return authorityRepository.findAuthoritiesOfUser(username);
		}

		@Transactional
		public void addUserRoles(String username, List<Authority> authorities) {
			User user=userRepository.findById(username).orElseThrow(()->new HttpStatusException(HttpStatus.NOT_FOUND,"User not found in DB"));
	    	Set<Authority> userAuthorities=authorityRepository.findAuthoritiesOfUser(username);
    		if (userAuthorities==null) {userAuthorities=new HashSet<>();}
    		Set<Authority>effectiveUserAuthorities=userAuthorities;
			if (!CollectionUtils.isEmpty(authorities)) {
	    		authorities.stream()
	    			.filter(x->authorityExists(x))
	    			.filter(x->!effectiveUserAuthorities.contains(x))
	    			.forEach(x->userAuthorityRepository.save(new UserAuthority(new UserAuthorityId(username, x.getId()))));
	    	}
		}

		@Transactional
		public void deleteUserRoles(String username, List<Authority> authorities) {
			User user=userRepository.findById(username).orElseThrow(()->new HttpStatusException(HttpStatus.NOT_FOUND,"User not found in DB"));
	    	Set<Authority> userAuthorities=authorityRepository.findAuthoritiesOfUser(username);
    		if (userAuthorities==null) {userAuthorities=new HashSet<>();}
    		Set<Authority>effectiveUserAuthorities=userAuthorities;
			if (!CollectionUtils.isEmpty(authorities)) {
	    		authorities.stream()
	    			.filter(x->authorityExists(x))
	    			.filter(x->effectiveUserAuthorities.contains(x))
	    			.forEach(x->userAuthorityRepository.deleteById(new UserAuthorityId(username, x.getId())));
	    	}
			
		}
	

}
