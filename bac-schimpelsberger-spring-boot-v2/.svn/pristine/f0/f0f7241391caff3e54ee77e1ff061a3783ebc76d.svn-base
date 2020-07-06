package at.jku.se.bac.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import at.jku.se.bac.dto.AddUserDto;
import at.jku.se.bac.dto.EditUserDto;
import at.jku.se.bac.entity.Authority;
import at.jku.se.bac.entity.User;
import at.jku.se.bac.repository.AuthorityRepository;
import at.jku.se.bac.repository.UserRepository;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuthorityRepository authorityRepository;
        

    @Transactional(propagation=Propagation.REQUIRED)
    public User registerNewUserAccount(final AddUserDto addUserDto) {
        if (usernameExists(addUserDto.getUsername())) {
        	throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"username already exists");
        }
        if (emailExists(addUserDto.getEmail())) {   
        	throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"email already exists");
        }
        final User user = new User();
        user.setUsername(addUserDto.getUsername());
        user.setFirstName(addUserDto.getFirstName());
        user.setLastName(addUserDto.getLastName());
        //user.setPassword(passwordEncoder.encode(addUserDto.getPassword()));
        user.setPassword(addUserDto.getPassword());
        user.setEmail(addUserDto.getEmail());
        user.setEnabled(addUserDto.getEnabled());
        return userRepository.save(user);
    }
   
    @Transactional(propagation=Propagation.REQUIRED)
    public User updateUserAccount(final EditUserDto editUserDto) {
        
    	User user=userRepository.findById(editUserDto.getUsername()).orElse(null);
    	if (user==null)throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    	if (!user.getEmail().equals(editUserDto.getEmail())) {
    		if (userRepository.findByEmail(editUserDto.getEmail()).isPresent()) {
            	throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"email already exists");
    		}
    	}
    	
        user.setUsername(editUserDto.getUsername());
        user.setFirstName(editUserDto.getFirstName());
        user.setLastName(editUserDto.getLastName());
        user.setEmail(editUserDto.getEmail());
        user.setEnabled(editUserDto.getEnabled());
        return user;
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    public void deleteUser(String username) {
    	userRepository.findById(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Could not delete user, username not found"));
    	userRepository.deleteById(username);
    }
    

    public User findById(String username){
       return userRepository.findById(username).orElse(null);
    }
    
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
     }
    
    public List<User>findAll(){
    	return userRepository.findAll();
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

    @Transactional(propagation=Propagation.REQUIRED)
	public void addUserRoles(String username, List<Authority> authorities) {
    	User user=userRepository.findById(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    	if (!CollectionUtils.isEmpty(authorities)) {
    		if (user.getAuthorities()==null) {user.setAuthorities(new HashSet<>());}
    		authorities.stream()
    			.filter(x->authorityExists(x))
    			.filter(x->!user.getAuthorities().contains(x))
    			.forEach(x->user.getAuthorities().add(x));;
    	}
    }

    @Transactional(propagation=Propagation.REQUIRED)
	public void deleteUserRoles(String username, List<Authority> authorities) {
    	User user=userRepository.findById(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    	if (!CollectionUtils.isEmpty(authorities)) {
    		if (user.getAuthorities()==null) {return;}
    		authorities.stream()
    			.filter(x->authorityExists(x))
    			.filter(x->user.getAuthorities().contains(x))
    			.forEach(x->user.getAuthorities().remove(x));;
    	}
	}
    
    private boolean authorityExists(Authority authority) {
    	return authorityRepository.findById(authority.getId())!=null;
    }
	
}
