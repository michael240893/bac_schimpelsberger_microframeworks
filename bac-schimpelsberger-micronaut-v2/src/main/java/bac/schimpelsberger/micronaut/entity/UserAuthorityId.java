package bac.schimpelsberger.micronaut.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserAuthorityId implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7313006944519281802L;

	@Column(name="username")
	private String username;
	
	@Column(name="authority_id")
	private String authorityId;

	
	
	public UserAuthorityId(String username, String authorityId) {
		super();
		this.username = username;
		this.authorityId = authorityId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorityId == null) ? 0 : authorityId.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAuthorityId other = (UserAuthorityId) obj;
		if (authorityId == null) {
			if (other.authorityId != null)
				return false;
		} else if (!authorityId.equals(other.authorityId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	
	
	
	
}
