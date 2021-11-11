package com.tuhuella.main.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tuhuella.main.entities.Pet;


@Repository
public interface PetRepository extends JpaRepository<Pet, String> {
	
	@Query("SELECT a FROM Pet a WHERE a.name LIKe :name")
	public List<Pet> searchPetName(@Param("name") String name);

	public List<Pet> findAll();
	
	@Query("SELECT a FROM Pet a WHERE a.id LIKE :id")
		public Pet consultId(@Param("id") String id);
	

	@Query("SELECT a from Pet a ORDER BY a.name")
	public ArrayList<Pet> searchAll();


	@Query("SELECT a from Pet a WHERE a.name LIKE :q OR a.species LIKE :q ORDER BY a.name DESC")
	public Page<Pet> searchAssetsByParam(Pageable pageable, @Param("q") String q);
	
}