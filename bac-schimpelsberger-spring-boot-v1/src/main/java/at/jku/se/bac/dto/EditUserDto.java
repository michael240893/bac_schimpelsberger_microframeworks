package at.jku.se.bac.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import at.jku.se.bac.validation.ValidEmail;




public class EditUserDto {
    	
//	@NotNull
//	@Size(min = 1, message = "Username needs to have more than 1 character")
	private String username;

	
	@NotNull
    @Size(min = 1, message = "First Name needs to have more than 1 character")
    private String firstName;

    @NotNull
    @Size(min = 1, message = "Last Name needs to have more than 1 character")
    private String lastName;


    @ValidEmail
    @NotNull
    @Size(min = 1, message = "Email needs to have at least one character")
    private String email;

    @NotNull
    private Boolean enabled;
    	


	public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
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
		return "EditUserDto [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", enabled=" + enabled + "]";
	}






	


	

}
