package bac.schimpelsberger.micronaut.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bac_user_authorities")
public class UserAuthority {
	
	@EmbeddedId
	private UserAuthorityId id;

	public UserAuthorityId getId() {
		return id;
	}

	public void setId(UserAuthorityId id) {
		this.id = id;
	}

	public UserAuthority(UserAuthorityId id) {
		super();
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		UserAuthority other = (UserAuthority) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
