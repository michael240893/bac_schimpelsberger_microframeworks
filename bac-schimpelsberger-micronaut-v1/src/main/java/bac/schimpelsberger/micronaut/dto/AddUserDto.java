package bac.schimpelsberger.micronaut.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import bac.schimpelsberger.micronaut.validation.PasswordMatches;
import bac.schimpelsberger.micronaut.validation.ValidEmail;
import io.micronaut.core.annotation.Introspected;


@Introspected
@PasswordMatches
public class AddUserDto {
    
	@NotNull
	@Size(min = 1, message = "Username needs to have more than 1 character")
	private String username;

	
	@NotNull
    @Size(min = 1, message = "First Name needs to have more than 1 character")
    private String firstName;

    @NotNull
    @Size(min = 1, message = "Last Name needs to have more than 1 character")
    private String lastName;

    @NotNull
    @Size(min = 8, message = "Password needs to have at least 8 characters")
    private String password;

    @NotNull
    @Size(min = 8, message = "Password needs to have at least 8 characters")
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @Size(min = 1, message = "Email needs to have at least one character")
    private String email;
    
    private Boolean enabled;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    private Integer role;

    public Integer getRole() {
        return role;
    }

    public void setRole(final Integer role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(final String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }


    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "UserDto [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", matchingPassword=" + matchingPassword + ", email=" + email + ", role=" + role + "]";
	}

	

}
