package bac.schimpelsberger.micronaut.repository;


import java.util.List;
import java.util.Set;

import bac.schimpelsberger.micronaut.entity.Authority;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface AuthorityRepository extends JpaRepository<Authority, String> {

	  @Query(value = " select a.* from bac_authorities a left join (select * from bac_user_authorities ua where ua.username=:username) u on a.id=u.authority_id where u.username is null order by a.id",
	            nativeQuery = true)
	     List<Authority> findUnassignedAuthorities(String username);

	  @Query(value = "select a.* from bac_authorities a inner join bac_user_authorities ua on ua.authority_id=a.id where ua.username=:username",
	            nativeQuery = true)
	Set<Authority> findAuthoritiesOfUser(String username);
	  

}