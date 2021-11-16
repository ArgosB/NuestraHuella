package com.tuhuella.main.controllers;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tuhuella.main.entities.HumanUser;
import com.tuhuella.main.entities.Photo;
import com.tuhuella.main.entities.Zone;
import com.tuhuella.main.enums.Province;
import com.tuhuella.main.enums.Sex;
import com.tuhuella.main.enums.Size;
import com.tuhuella.main.repositories.HumanUserRepository;
import com.tuhuella.main.repositories.PhotoRepository;
import com.tuhuella.main.repositories.ZoneRepository;
import com.tuhuella.main.services.PetService;
import com.tuhuella.main.services.UserService;

@Controller
@RequestMapping("/pet")

public class PetController {
	@Autowired
	private PetService petService;
	@Autowired
	private HumanUserRepository userRepo;
	@Autowired
	private ZoneRepository zoneRepo;
	@Autowired
	private PhotoRepository photoRepo;

	@GetMapping("/add-a-pet/{id}")
	public String form() {

		return "AddAPet-form";
	}

	@PostMapping("/add-a-pet/{id}")
	public String createPet(ModelMap modelo,@PathVariable String id, @RequestParam("file") MultipartFile file,@RequestParam String name, @RequestParam Integer age, @RequestParam String species,@RequestParam(required=false) String breed, 
			@RequestParam(required=false) Integer Weight, @RequestParam Sex sex, @RequestParam Size size, @RequestParam Boolean upToDateVaccine, @RequestParam Boolean castrated, @RequestParam Boolean deWormed, @RequestParam String disease, 
			@RequestParam String city,@RequestParam String country,@RequestParam String neighborhood,@RequestParam Province province) throws Exception {

		try {
			
			HumanUser user = userRepo.getById(id);
			
			Zone zone = user.getZone();
			

			Photo photo = new Photo();
			photo.setName(file.getName());
			photo.setMime(file.getContentType());
			photo.setPicture(file.getBytes());
			photo.setActive(true);
			photo.setCreatePhoto(new Date());
			photoRepo.save(photo);
			petService.createPet(name, age, species, breed, Weight, sex, size, upToDateVaccine, castrated, deWormed, disease, zone, photo);
			
			

			modelo.put("exito", name.toString());
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


	
}
