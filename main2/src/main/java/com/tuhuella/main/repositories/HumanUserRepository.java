package com.tuhuella.main.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import com.tuhuella.main.entities.HumanUser;

@Repository
public interface HumanUserRepository extends JpaRepository<HumanUser, String> {
	
	@Query("SELECT a from HumanUser a WHERE a.email = :email ")
	UserDetails findMyUserByEmail(String email);

	@Query("SELECT a from HumanUser a WHERE a.username = :username ")
	HumanUser findMyUserByusername(@Param("username")String username);

	@Query("SELECT a from HumanUser a WHERE a.email = :email ")
	HumanUser findByemail(@Param("email") String email);

	
	@Query("SELECT a from HumanUser a where a.id = ?1 ")
	Optional <HumanUser> findMyUserByID(String id); 
	
	
	@Query("SELECT a from HumanUser a WHERE a.active = true ORDER BY a.name")
	public List<HumanUser> showActive();
	
	@Query("SELECT a from HumanUser a WHERE a.surname LIKE surname ORDER BY a.surname")
	public List<HumanUser> searchBySurname(@Param("surname") String surname);
	
	@Query("SELECT a from HumanUser a WHERE a.username LIKE username and a.active = true")
	    public Optional<HumanUser> findByUsername(String username);
	}
	

