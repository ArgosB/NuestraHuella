package com.tuhuella.main.services;



import java.util.Date;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tuhuella.main.entities.Photo;
import com.tuhuella.main.entities.User;

import com.tuhuella.main.entities.Zone;
import com.tuhuella.main.repositories.*;

@Service
public class UserService  {
	@Autowired
	private UserRepository userRepository;

	/*
	 * @Autowired private PhotoRepository PhotoRepository;
	 */

	/*
	 * @Autowired private PetRepository PetRepository;
	 */

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public User signUpUser(Photo photo, String name, String surname, String userName, String password, Date birthDate,
			String street, Integer StreetNumber, Zone zone, Integer phoneNumber, Integer alternativeNumber,
			String email) throws Exception {

		validate(name, surname, userName, email, password);
		User entity = new User();

		entity.setName(name);
		entity.setSurname(surname);
		entity.setUsername(userName);
		entity.setPassword(password);
		entity.setActive(true);
		entity.setPhoto(photo);
		entity.setBirthDate(birthDate);
		entity.setStreet(street);
		entity.setStreetNumber(StreetNumber);
		entity.setZone(zone);
		entity.setPhoneNumber(phoneNumber);
		entity.setAlternativeNumber(alternativeNumber);
		entity.setEmail(email);
		entity.setActive(true);

		return userRepository.save(entity);
	}

	public Optional<User> showUserByEmail(String email) throws Exception {
		try {
			return userRepository.findMyUserByEmail(email);

		} catch (Exception e) {
			return userRepository.findMyUserByEmail(email);

		}

	}

	public void edit(String id, Photo photo, String name, String surname, String userName, String password,
			Date birthDate, String street, Integer StreetNumber, Zone zone, Integer phoneNumber,
			Integer alternativeNumber, String email) throws Exception {
		validate(name, surname, userName, email, password);

		Optional<User> answer = userRepository.findMyUserByID(id);

		if (answer.isPresent()) {
			User user = answer.get();
			if (user.getId().equals(id)) {
				user.setName(name);
				user.setSurname(surname);
				user.setUsername(userName);
				user.setPassword(password);
				user.setActive(true);
				user.setPhoto(photo);
				user.setBirthDate(birthDate);
				user.setStreet(street);
				user.setStreetNumber(StreetNumber);
				user.setZone(zone);
				user.setPhoneNumber(phoneNumber);
				user.setAlternativeNumber(alternativeNumber);
				user.setEmail(email);
				user.setActive(true);
				userRepository.save(user);
			} else {
				throw new Exception("no tiene permiso suficiente para realizar la operacion");
			}
		} else {
			throw new Exception("no existe un usuario con el id solicitado");

		}
	}

	public void validate(String name, String surname, String username, String email, String password) throws Exception {

		if (name == null || name.isEmpty() || name.contains("  ")) {
			throw new Exception("Debe tener un name valido");
		}
		if (username == null || username.isEmpty() || username.contains("  ")) {
			throw new Exception("must have a valid username");
		}
		if (surname == null || surname.isEmpty() || surname.contains("  ")) {
			throw new Exception("Debe tener un surname valido");
		}
		if (email == null || email.isEmpty() || email.contains("  ")) {
			throw new Exception("must have a valid email");
		}

		if (password == null || password.isEmpty() || password.contains("  ") || password.length() < 8
				|| password.length() > 12) {
			throw new Exception("must have a  valid password");
		}

	}

	@Transactional(readOnly = true)
	public List<User> findUsers() {

		List<User> findUsers = userRepository.findAll();
		return findUsers;

	}

	@Transactional
	public User edit(String Id) {
		Optional<User> edit = userRepository.findById(Id);
		User user = edit.get();
		userRepository.save(user);
		return user;
	}

	@Transactional
	public User lockUser(String Id) {
		Optional<User> lockUser = userRepository.findById(Id);
		User user = lockUser.get();
		user.setActive(false);
		userRepository.save(user);
		return user;
	}

	@Transactional
	public void editUser(String id, String name) throws Exception {

		if (name == null || name.isEmpty()) {
			throw new Exception("El nombre no puede estar vacio");
		}

		Optional<User> answer = userRepository.findById(id);
		if (answer.isPresent()) {
			User user = answer.get();
			user.setName(name);
		} else {
			throw new Exception("No se pudo encontrar el id solicitado");
		}

	}

		

}
