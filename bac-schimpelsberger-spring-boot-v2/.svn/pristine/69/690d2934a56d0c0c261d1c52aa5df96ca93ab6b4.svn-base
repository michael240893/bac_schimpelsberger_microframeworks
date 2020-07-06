package at.jku.se.bac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import at.jku.se.bac.entity.Authority;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

    @Query(value = " select a.* from bac_authorities a left join (select * from bac_user_authorities ua where ua.username=?1) u on a.id=u.authority_id where u.username is null order by a.id",
            nativeQuery = true)
     List<Authority> findUnassignedAuthorities(String username);


}
