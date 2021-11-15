package com.tuhuella.main.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.tuhuella.main.entities.Zone;
import com.tuhuella.main.enums.Sex;
import com.tuhuella.main.enums.Size;
import com.tuhuella.main.services.PetService;

@Controller
@RequestMapping("/pet")

public class PetController {
	@Autowired
	private PetService petService;

	@GetMapping("/register")
	public String form() {

		return "AddAPet-form";
	}

	@PostMapping("/register")
	public String createPet(ModelMap modelo, String name, Integer age, String species, String breed, 
	Integer Weight, Sex sex, Size size, Boolean upToDateVaccine, Boolean castrated, Boolean deWormed, String disease, Zone zone) {

		try {
			petService.createPet(name, age, species, breed, Weight, sex, size, upToDateVaccine, castrated, deWormed, disease, zone);

			modelo.put("exito", "registro exitoso");
			return "AddAPet-form";
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			return "AddAPet-form";
		}
	}

	
	@GetMapping("/showPets")
	public String showPets(ModelMap modelo) {
		petService.showAllPet();
		return "pet-list";
	}
	
	@GetMapping("/showSpecies")
	public String searchSpecies(Pageable paginable, String query) {
		petService.searchSpecies(paginable, query);
		return "petspecies-list";
	}


	@GetMapping("/showSpecies")
	public String searchSpecies(Pageable paginable, String query) {
		petService.searchSpecies(paginable, query);
		return "petspecies-list";
	}
}
