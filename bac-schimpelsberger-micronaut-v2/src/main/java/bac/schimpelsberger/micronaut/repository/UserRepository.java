package bac.schimpelsberger.micronaut.repository;

import java.util.Optional;

import bac.schimpelsberger.micronaut.entity.User;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface UserRepository extends JpaRepository<User, String> {
	
	    Optional<User> findByEmail(String email);


	    
	    
}