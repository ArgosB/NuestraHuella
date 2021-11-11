package com.tuhuella.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tuhuella.main.entities.Photo;
import com.tuhuella.main.repositories.PhotoRepository;

@Service
public class PhotoService {
	
	@Autowired
	PhotoRepository photoRepo;
	
	public Photo savePhoto(MultipartFile file) throws Exception {
		try {
		if(file != null) {
			Photo photo = new Photo();
			
			photo.setName(file.getName());
			photo.setPicture(file.getBytes());
			
			return photoRepo.save(photo);
		} } catch(Exception e) {
			
		}
			
		return null;
		
	}	
	
}

