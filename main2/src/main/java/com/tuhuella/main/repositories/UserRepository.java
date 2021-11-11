package com.tuhuella.main.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tuhuella.main.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	@Query("SELECT a from User a WHERE a.email = ?1 ")
	Optional <User> findMyUserByEmail(String email); 
	//List<User> findByNameContain(String name);
	//List<User> findAll();
	
	@Query("SELECT a from User a where a.id = ?1 ")
	Optional <User> findMyUserByID(String id); 
	
	
	@Query("SELECT a from User a WHERE a.active = true ORDER BY a.name")
	public List<User> showActive();
	
	@Query("SELECT a from User a WHERE a.surname LIKE surname ORDER BY a.surname")
	public List<User> searchBySurname(@Param("surname") String surname);
	
	@Query("SELECT a from User a WHERE a.username LIKE username")
	    public Optional<User> findByUsername(String username);
	}
	

