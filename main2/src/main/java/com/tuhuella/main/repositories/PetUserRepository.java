package com.tuhuella.main.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tuhuella.main.entities.PetUser;
import com.tuhuella.main.enums.Species;


@Repository
public interface PetUserRepository extends JpaRepository<PetUser, String> {
	
	@Query("SELECT a FROM  PetUser a WHERE a.name LIKe :name")
	public List<PetUser> searchPetName(@Param("name") String name);


	@Query("SELECT a FROM  PetUser a WHERE a.id LIKE :id")
		public PetUser consultId(@Param("id") String id);
	

	@Query("SELECT a from  PetUser a ORDER BY a.name")
	public List<PetUser> searchAll();

	
	@Query("SELECT a from  PetUser a WHERE a.species LIKE :species ORDER BY a.name")
	public ArrayList<PetUser> searchBySpecies(@Param("species") Species species);
	
	/*
	 * @Query("SELECT a from  PetUser a WHERE a.name LIKE :q OR a.species LIKE :q ORDER BY a.name DESC"
	 * ) public Page<PetUser> searchAssetsByParam(Pageable pageable, @Param("q")
	 * String q);
	 * 
	 * @Query("SELECT a from  PetUser a WHERE a.species LIKE :q ORDER BY a.species DESC"
	 * ) public Page<PetUser> searchSpecies(Pageable pageable, @Param("q") String
	 * q);
	 */


}