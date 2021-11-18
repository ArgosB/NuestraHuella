package com.tuhuella.main.services;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.tuhuella.main.entities.*;
import com.tuhuella.main.entities.Zone;
import com.tuhuella.main.enums.Sex;
import com.tuhuella.main.enums.Size;
import com.tuhuella.main.enums.Species;
import com.tuhuella.main.repositories.PetUserRepository;





@Service
public class PetService {

@Autowired
private PetUserRepository petRepository;

@Transactional
public void createPet ( String name, Integer age, Species species, String breed, Integer Weight, Sex sex, Size size, Boolean upToDateVaccine, Boolean castrated, Boolean deWormed, String disease, Zone zone, Photo photo) throws Exception{
	
	if (name.equals(null)||name.isEmpty()) {
		throw new Exception ("El nombre no puede estar vacio");
	}else {
		PetUser pet = new PetUser();
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
		pet.setZone(zone);
		pet.setPhoto(photo);
		
		petRepository.save(pet);
	}
	

	
}
@Transactional
public void showAllPet() {
	petRepository.searchAll();
}


@Transactional
public Page<PetUser> searchPet(Pageable paginable, String query) {
	return petRepository.searchAssetsByParam(paginable, query);
}

@Transactional
public void delete(String id) throws Exception {
	PetUser entidad = petRepository.getById(id);
	petRepository.delete(entidad);
}

public void validatePet(PetUser m) throws Exception {
	if (m.getSpecies() == null)

	{
		throw new Exception("La mascota tiene que tener una especie");
	}

	if (m.getPhoto() == null) {
		throw new Exception("La mascota tiene que tener una Foto");
	}

}
@Transactional
public Page<PetUser> searchSpecies(Pageable paginable, String query) {
	return petRepository.searchAssetsByParam(paginable, query);
}

}
