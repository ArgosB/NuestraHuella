package com.tuhuella.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.tuhuella.main.entities.Photo;
import com.tuhuella.main.repositories.PhotoRepository;
import com.tuhuella.main.entities.PetUser;


import java.util.Date;
import java.util.Optional;



@Service
public class PhotoService {
	
	@Autowired
	private PhotoRepository photoRepo;
	@Autowired
	private UserService userService;
	@Autowired
	private PetService petService;
	
	
	
	public Photo savePhoto(MultipartFile file) throws Exception {
		try {
		if(file != null) {
			Photo photo = new Photo();

			photo.setName(file.getName());
			photo.setPicture(file.getBytes());
			photo.setMime(file.getContentType());
			photo.setActive(true);
			photo.setCreatePhoto(new Date());
			return photoRepo.save(photo);
		} } catch(Exception e) {
			e.getMessage();
			return null;
		}
		return null;
	}	
	
	
	
	
	
}

