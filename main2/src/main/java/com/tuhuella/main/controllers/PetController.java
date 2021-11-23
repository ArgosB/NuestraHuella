package com.tuhuella.main.controllers;

import com.tuhuella.main.entities.HumanUser;
import com.tuhuella.main.entities.PetUser;
import com.tuhuella.main.entities.Photo;
import com.tuhuella.main.entities.Zone;
import com.tuhuella.main.enums.Sex;
import com.tuhuella.main.enums.Size;
import com.tuhuella.main.enums.Species;
import com.tuhuella.main.repositories.HumanUserRepository;
import com.tuhuella.main.repositories.PetUserRepository;
import com.tuhuella.main.services.PetService;
import com.tuhuella.main.services.PhotoService;
import com.tuhuella.main.webException.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
@RequestMapping("/pet")

public class
PetController {
	@Autowired
	private PetService petService;
	@Autowired
	private HumanUserRepository userRepo;
	@Autowired
	private PhotoService photoService;
	@Autowired
	private PetUserRepository petRepo;
	@GetMapping("/add-a-pet")
	public String form() {

		return "AddAPet-form";
	}

	@PostMapping("/add-a-pet/{id}")
	public String createPet(ModelMap modelo, @PathVariable String id,
			@RequestParam(required = false) MultipartFile file, @RequestParam String name,
			@RequestParam(required = false) Integer ageInMonths, @RequestParam Species species,
			@RequestParam(required = false) String breed, @RequestParam(required = false) Integer weight,
			@RequestParam(required = false) Sex sex, @RequestParam Size size,
			@RequestParam(required = false) Boolean upToDateVaccine, @RequestParam(required = false) Boolean castrated,
			@RequestParam(required = false) Boolean deWormed, @RequestParam(required = false) String disease)
			throws Exception {

		try {
			HumanUser user = userRepo.getById(id);
			if (user == null) {
				modelo.put("error en el id", "error");
			}

			Zone zone = user.getZone();

			Photo photo = photoService.savePhoto(file);

			petService.createPet(id, name, ageInMonths, species, breed, weight, sex, size, upToDateVaccine, castrated, deWormed,
					disease, zone, photo);

			modelo.put("exito", name.toString());
			return "/AddAPet-form";
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			return "AddAPet-form";
		}
	}

	@GetMapping("/showPets")
	public String showPets(ModelMap modelo) {
		modelo.addAttribute("speciesList", petService.showAllPet());
		return "PetsList";
	}

	@GetMapping("/showCats/{specie}")
	public String searchCats(ModelMap modelo, @PathVariable Species specie) {	
		modelo.addAttribute("speciesList", petService.findBySpecies(specie));
		return "CatList";
	}
	
	@GetMapping("/showDogs/{specie}")
	public String searchDogs(ModelMap modelo, @PathVariable Species specie) {
		modelo.addAttribute("speciesList", petService.findBySpecies(specie));
		return "DogList";
	}
	
	@GetMapping("/showOthers/{specie}")
	public String searchSpecies(ModelMap modelo, @PathVariable Species specie) {
		modelo.addAttribute("speciesList", petService.findBySpecies(specie));
		return "OthersPets";
	}

	@GetMapping("/photo")
	public ResponseEntity<byte[]> petPhoto(@RequestParam String id) throws Exception {

		try {
			Optional<PetUser> op = petService.findAPetById(id);
			if (!op.isPresent()) {
				throw new Exception("Esta mascota no existe");
			}
			PetUser pet = op.get();
			
			byte[] photo = pet.getPhoto().getPicture();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);

			return new ResponseEntity<>(photo, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.getMessage();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	@PostMapping("/one/{id}")
	public String onePet(@PathVariable String id, ModelMap model) throws WebException {

	return "One-Pet";

	}

	@GetMapping("/one/{id}")
	public String findById(@PathVariable String id, ModelMap model) throws WebException {

		PetUser pet = petRepo.getById(id);
		HumanUser user = pet.getUser();
		if(pet != null){

			model.addAttribute("pet", pet);
			model.addAttribute("user", user);
			final String s = "One-Pet";
			return s;
		}
		else throw new WebException("Error en el ID");

	}


}
