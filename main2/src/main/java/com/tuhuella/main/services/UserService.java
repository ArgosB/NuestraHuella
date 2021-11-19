package com.tuhuella.main.services;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import com.tuhuella.main.enums.Province;
import com.tuhuella.main.webException.WebException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tuhuella.main.entities.Photo;
import com.tuhuella.main.entities.HumanUser;
import com.tuhuella.main.entities.PetUser;
import com.tuhuella.main.entities.Zone;
import com.tuhuella.main.repositories.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private HumanUserRepository userRepository;

	@Autowired 
	private PhotoRepository PhotoRepository;
	
	@Autowired
	private PetUserRepository petRepository;


	/*
	 * @Autowired private PetRepository PetRepository;
	 */

	@Transactional/*(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })*/
	public HumanUser signUpUser(Photo photo, String name, String surname, String userName, String password, Date birthDate,
								Zone zone, Long phoneNumber, Long alternativeNumber,
								String email) throws Exception {

		validate(name, surname, userName, email, password);
		HumanUser entity = new HumanUser();

		entity.setName(name);
		entity.setSurname(surname);
		entity.setUsername(userName);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			entity.setPassword(encoder.encode(password));


		entity.setPhoto(photo);
		entity.setBirthDate(birthDate);
		entity.setZone(zone);
		entity.setPhoneNumber(phoneNumber);
		entity.setAlternativeNumber(alternativeNumber);
		entity.setEmail(email);
		entity.setActive(true);
		entity.setEnabled(true);
		entity.setCreateUser(new Date());


		return userRepository.save(entity);
	}



	//Estoy probando un nuevo metodo siguiendo paso a paso el video de la mina
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		HumanUser user;
		user = userRepository.findMyUserByusername(username);
		if (user != null && user.getActive()){

			List<GrantedAuthority> grantities = new ArrayList<>();
			GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_USER_DEFAULT");
			grantities.add(p1);
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true);
			session.setAttribute("UserSession", user );
			return new User(user.getUsername(),user.getPassword(),grantities);
		}else throw new UsernameNotFoundException("username not found");
	}


	@Transactional
	public HumanUser edit(String id, Photo photo, String name, String surname, String userName, String password,
					 Date birthDate, Zone zone, Long phoneNumber,
					 Long alternativeNumber, String email) throws Exception {

		Optional<HumanUser> answer = userRepository.findMyUserByID(id);

		if (answer.isPresent()) {
			HumanUser user = answer.get();
			if (user.getId().equals(id)) {
				user.setName(name);

				user.setSurname(surname);
				if (userName != null && !userName.isEmpty()) {
					user.setUsername(userName);
				}
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				user.setPassword(encoder.encode(password));
				user.setActive(true);
				user.setPhoto(photo);
				user.setBirthDate(birthDate);
				user.setZone(zone);
				if (phoneNumber != null && phoneNumber != 0 ) {
					user.setPhoneNumber(phoneNumber);
				}

				user.setAlternativeNumber(alternativeNumber);
				user.setEmail(email);
				user.setActive(true);
				user.setEnabled(true);
				user.setModifiedUser(new Date());
			return	userRepository.save(user);
				
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
		
		if (password == null || password.isEmpty()) {
			throw new Exception("must have a  valid password");
		}

	}

	@Transactional(readOnly = true)
	public List<HumanUser> findUsers() {

		List<HumanUser> findUsers = userRepository.findAll();
		return findUsers;

	}

	@Transactional
	public HumanUser edit(String Id) {
		Optional<HumanUser> edit = userRepository.findById(Id);
		HumanUser user = edit.get();
		userRepository.save(user);
		return user;
	}

	@Transactional
	public HumanUser lockUser(String Id) throws WebException {
		if (!Id.isEmpty()){
		Optional<HumanUser> lockUser = userRepository.findById(Id);
		HumanUser user = lockUser.get();
		user.setActive(false);
		user.setEnabled(false);
		userRepository.save(user);
		return user;}
		else {
			throw new WebException("No se pudo encontrar el id solicitado");
		}
	}



	@Transactional
	public void editUser(String id, String name) throws Exception {

		if (name == null || name.isEmpty()) {
			throw new Exception("El nombre no puede estar vacio");
		}

		Optional<HumanUser> answer = userRepository.findById(id);
		if (answer.isPresent()) {
			HumanUser user = answer.get();
			user.setName(name);
		} else {
			throw new Exception("No se pudo encontrar el id solicitado");
		}

	}

	
}
