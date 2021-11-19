package com.tuhuella.main.controllers;

import java.util.Date;
import java.util.Optional;

import com.tuhuella.main.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tuhuella.main.entities.HumanUser;
import com.tuhuella.main.entities.PetUser;
import com.tuhuella.main.entities.Photo;
import com.tuhuella.main.entities.Zone;
import com.tuhuella.main.enums.Province;
import com.tuhuella.main.enums.Sex;
import com.tuhuella.main.enums.Size;
import com.tuhuella.main.enums.Species;
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
	private PhotoService photoService;

	@GetMapping("/add-a-pet")
	public String form() {

		return "AddAPet-form";
	}

	@PostMapping("/add-a-pet/{id}")
	public String createPet(ModelMap modelo, @PathVariable String id,
			@RequestParam(required = false) MultipartFile file, @RequestParam String name,
			@RequestParam(required = false) Integer age, @RequestParam Species species,
			@RequestParam(required = false) String breed, @RequestParam(required = false) Integer Weight,
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

			petService.createPet(name, age, species, breed, Weight, sex, size, upToDateVaccine, castrated, deWormed,
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
		petService.showAllPet();
		return "PetsList";
	}

	@GetMapping("/showCats")
	public String searchCats(Pageable paginable, String query) {
		petService.searchSpecies(paginable, query);
		return "CatList";
	}
	
	@GetMapping("/showDogs")
	public String searchDogs(Pageable paginable, String query) {
		petService.searchSpecies(paginable, query);
		return "DogList";
	}
	
	@GetMapping("/showOthers")
	public String searchSpecies(Pageable paginable, String query) {
		petService.searchSpecies(paginable, query);
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
	
	
}
