package bac.schimpelsberger.micronaut.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bac.schimpelsberger.micronaut.dto.AddUserDto;
import bac.schimpelsberger.micronaut.dto.EditUserDto;
import bac.schimpelsberger.micronaut.entity.Authority;
import bac.schimpelsberger.micronaut.entity.User;
import bac.schimpelsberger.micronaut.service.UserService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.exceptions.HttpStatusException;

@Controller("/users")  
public class UserController {
	
	Logger log =LoggerFactory.getLogger(this.getClass());
	  
	protected final UserService service;

		public UserController(UserService service) { 
	    	this.service = service;
	    }
		
	    @Get
	    public List<User> getUsers() {
			log.info("getUsers was called");
	    	List<User>users= service.findAll();
	    	return users;
	    }
	    
		@Post
		public User addUser(@Valid @Body AddUserDto dto) {
			log.info("addUser was called with AddUserDto: {}",dto);
			dto.setEnabled(true);
			User user=service.registerNewUserAccount(dto);
			return user;
		}
	    
	    @Get(value="/{username}") 
	    public User getUser(String username) {
			log.info("getUser was called with username: {}",username);
	        User user= service.findById(username);
	        if (user!=null) {
	        	return user;      
	        }
			else throw new HttpStatusException(HttpStatus.NOT_FOUND,"User not found");
	    }
	    
		@Delete("/{username}")
		public void deleteUser(String username) {
			log.info("deleteUser was called with username: {}",username);
			service.deleteUser(username);
			
		}
		
		@Put("/{username}")
		public User updateUser( String username, @Valid @Body EditUserDto dto) {
			dto.setUsername(username);
			log.info("updaeUser was called with EditUserDto: {}",dto);
			User user=service.updateUserAccount(dto);
			if (user==null)  throw new HttpStatusException(HttpStatus.NOT_FOUND,"User not found");
			return user;
		}
		
		@Get("/{username}/roles")
		public Set<Authority>  getUserRoles( @PathVariable(name = "username") String username) {
			log.info("getUserRoles was called with username: {}",username);
			User user=service.findById(username);
			log.info("getUserRoles found user: {}",user);
			if (user==null)  throw new HttpStatusException(HttpStatus.NOT_FOUND,"User nor found");
			return service.findAuthoritiesOfUser(username);
		}
		
		@Post("/{username}/roles")
		public void  addUserRoles(@PathVariable(name = "username") String username,@Body List<Authority>authorities) {
			log.info("addUserRoles was called with username: {} for : authorities",username,authorities==null?"NULL":authorities.size());
			service.addUserRoles(username, authorities);
		}
		
		@Delete("/{username}/roles")
		public void deleteUserRoles(@PathVariable(name = "username") String username, @Body List<Authority>authorities) {
			log.info("deleteUserRoles was called with username: {} for : authorities",username,authorities==null?"NULL":authorities.size());
			service.deleteUserRoles(username, authorities);
		}

		@Get("/{username}/unassignedRoles")
		public List<Authority>  getUnassignedUserRoles(@PathVariable(name = "username") String username) {
			log.info("getUserRoles was called with username: {}",username);
			User user=service.findById(username);
			if (user==null)  throw new HttpStatusException(HttpStatus.NOT_FOUND,"User Not found");
			List<Authority>unassignedAuthorities=service.findUnassignedAuthorities(username);
			return unassignedAuthorities;
		}
	    
	
}