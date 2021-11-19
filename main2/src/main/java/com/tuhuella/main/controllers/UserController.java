package com.tuhuella.main.controllers;

import java.util.Date;
//import java.util.List;

import com.tuhuella.main.webException.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tuhuella.main.entities.Photo;
import com.tuhuella.main.entities.HumanUser;
import com.tuhuella.main.entities.Zone;
import com.tuhuella.main.enums.Province;
import com.tuhuella.main.repositories.PhotoRepository;
import com.tuhuella.main.repositories.ZoneRepository;
import com.tuhuella.main.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PhotoRepository photoRepo;
	@Autowired
	private ZoneRepository zoneRepo;

	@GetMapping("/sign-up")
	public String form() {

		return "singup-form";
	}

	@PostMapping("/sign-up")
	public String saveUser(ModelMap modelo, @RequestParam("file") MultipartFile file, @RequestParam String name,
						   @RequestParam String surname, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthDate,
						   @RequestParam String email, @RequestParam String password, @RequestParam String confirmedPassword,
						   @RequestParam String userName, @RequestParam Long phoneNumber,
						   @RequestParam(required = false) Long alternativeNumber, @RequestParam String country,
						   @RequestParam(required = false) Province province, @RequestParam String city,
						   @RequestParam(required = false) String neighborhood) {

		try {


			if (!password.equals(confirmedPassword)) {
				throw new
						Exception("Las contraseñas no coinciden");
			}


			Zone zone = new Zone();
			zone.setCity(city);
			zone.setCountry(country);
			zone.setNeighborhood(neighborhood);
			zone.setProvince(province);
			zone = zoneRepo.save(zone);

			Photo photo = new Photo();
			photo.setName(file.getName());
			photo.setMime(file.getContentType());
			photo.setPicture(file.getBytes());
			photo.setActive(true);
			photo.setCreatePhoto(new Date());
			photoRepo.save(photo);

			userService.signUpUser(photo, name, surname, userName, password, birthDate, zone, phoneNumber,
					alternativeNumber, email);

			modelo.put("exito", name);
			return "singup-form";
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			return "singup-form";
		}
	}

	
	/*@GetMapping("/showUserList")
	public String show(ModelMap modelo) {
		List<HumanUser> users = userService.findUsers();
		modelo.addAttribute("Users", users);
		return "UserList";
	}*/


	@GetMapping("/edit/{id}")
	public String edit(@PathVariable String id, ModelMap modelo) {
		HumanUser user = userService.edit(id);
		modelo.addAttribute("User", user);
		return "EditProfile";
	}




	@PostMapping("/edit/{id}")
	public String editUser(@PathVariable String id, ModelMap modelo, @RequestParam(value = "file") MultipartFile file, @RequestParam(required = false) String name,
						   @RequestParam(required = false) String surname, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthDate,
						   @RequestParam(required = false) String email, @RequestParam(required = false) String password, @RequestParam(required = false) String confirmedPassword,
						   @RequestParam(required = false) String userName, @RequestParam(required = false) Long phoneNumber,
						   @RequestParam(required = false) Long alternativeNumber, @RequestParam(required = false) String country,
						   @RequestParam(required = false) Province province, @RequestParam(required = false) String city,
						   @RequestParam(required = false) String neighborhood ) throws Exception {
		Photo photo = new Photo();
		photo.setName(file.getName());
		photo.setMime(file.getContentType());
		photo.setPicture(file.getBytes());
		photo.setActive(true);
		photo.setCreatePhoto(new Date());
		photoRepo.save(photo);

		Zone zone = new Zone();
		zone.setCity(city);
		zone.setCountry(country);
		zone.setNeighborhood(neighborhood);
		zone.setProvince(province);
		zone = zoneRepo.save(zone);

		HumanUser user = userService.edit(id, photo, name, surname, userName, password, birthDate, zone, phoneNumber,
				alternativeNumber, email);

		modelo.addAttribute("User", user);
		return "EditProfile";
	}

	@GetMapping("/delete/{id}")
	public String inactive(@PathVariable String id) {
		try {
			userService.lockUser(id);
		} catch (WebException e) {
			e.printStackTrace();
		}
		return "index";
	}

	@GetMapping("/login")
	public String login(@RequestParam(required = false) String username, @RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) throws Exception {
		try {
			if (error != null) {
				model.put("error", "Usuario o clave incorrectos");
			}
			if (username != null) {
				model.put("username", username);
			}
			if (logout != null) {
				model.put("logout", "Ha salido correctamente.");
			}

			return "login";

		} catch (Exception e) {
			model.put("error", e.getMessage());
			return "login";
		}
	}
}
/*GetMapping("/logout")
public string logout (Model model){
		model.addAllAttributes("Success", "ha finalizado la accion");
	}
	return "index"
}*/
