package com.tuhuella.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuhuella.main.entities.PetUser;
import com.tuhuella.main.services.UserService;

@Controller
@RequestMapping("/")
public class AppController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public String index(ModelMap model) {

		List<PetUser> petList = userService.findAllPets();
		if (!petList.isEmpty()) {
			PetUser pet1 = petList.get(petList.size() - 1);
			PetUser pet2 = petList.get(petList.size() - 2);
			PetUser pet3 = petList.get(petList.size() - 3);
			model.addAttribute("pet1", pet1);
			model.addAttribute("pet2", pet2);
			model.addAttribute("pet3", pet3);
		}
		return "index";
	}

	
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	
	/*@GetMapping("/user")
	public String user() {
		return "user";
	}*/
	
	@GetMapping("/404")
	public String admin() {
		return "404";
	}
}

