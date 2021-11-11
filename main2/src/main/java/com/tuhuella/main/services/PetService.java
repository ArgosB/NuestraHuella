package com.tuhuella.main.services;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.transaction.Transactional;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tuhuella.main.entities.*;
import com.tuhuella.main.entities.Zone;
import com.tuhuella.main.enums.Sex;
import com.tuhuella.main.enums.Size;
import com.tuhuella.main.repositories.PetRepository;
import com.tuhuella.main.webException.WebException;




@Service
public class PetService {

@Autowired
private PetRepository petRepository;

@Transactional
public void createPet ( String name, Integer age, String species, String breed, Integer Weight, Sex sex, Size size, Boolean upToDateVaccine, Boolean castrated, Boolean deWormed, String disease, Zone zone) throws Exception{
	
	if (name.equals(null)||name.isEmpty()) {
		throw new Exception ("El nombre no puede estar vacio");
	}else {
		Pet pet = new Pet();
		pet.setName(name);
		pet.setAgeInMonths(age);
		pet.setSpecies(species);
		pet.setBreed(breed);
		pet.setWeight(Weight);
		pet.setSex(sex);
		pet.setSize(size);
		pet.setUpToDateVaccine(upToDateVaccine);
		pet.setDewormed(deWormed);
		pet.setCastrated(castrated);
		pet.setDisease(breed);
		pet.setZone(null);
	}
	

	
}
@Transactional
public void showAllPet() {
	petRepository.searchAll();
}


@Transactional
public Page<Pet> searchPet(Pageable paginable, String query) {
	return petRepository.searchAssetsByParam(paginable, query);
}

@Transactional
public void delete(String id) throws Exception {
	Pet entidad = petRepository.getById(id);
	petRepository.delete(entidad);
}

public void validatePet(Pet m) throws Exception {
	if (m.getSpecies() == null || m.getSpecies().isEmpty() || m.getSpecies().equals(" "))

	{
		throw new Exception("La mascota tiene que tener una especie");
	}

	if (m.getPhoto() == null) {
		throw new Exception("La mascota tiene que tener una Foto");
	}

}

}
