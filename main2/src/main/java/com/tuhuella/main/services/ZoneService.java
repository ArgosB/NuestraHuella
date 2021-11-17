package com.tuhuella.main.services;

import java.util.Date;
import java.util.Optional;


import com.tuhuella.main.entities.Zone;
import com.tuhuella.main.repositories.ZoneRepository;
import com.tuhuella.main.webException.WebException;
import com.tuhuella.main.enums.Province;

public class ZoneService {

	private ZoneRepository zoneRepo;

	public void saveZone(String city, String country, String neighborhood, Province province) throws Exception {

		try {
			Zone zone = new Zone();
			zone.setCity(city);
			zone.setCountry(country);
			zone.setNeighborhood(neighborhood);
			zone.setProvince(province);
			zoneRepo.save(zone);
			
		} catch (Exception e) {
			throw new Exception("La zona no ha podido ser guardada");
		}
		
		
	}

	public void editZone(String id, String city, String country, String neighborhood, Province province) throws Exception{

		Optional<Zone> answer = zoneRepo.findById(id);

		if (answer.isPresent()) {
			Zone zone = answer.get();

			zone.setCity(city);
			zone.setCountry(country);
			zone.setProvince(province);
			zone.setNeighborhood(neighborhood);

		} else {
			throw new Exception("no existe una zona con el id solicitado");
		}
	}
	
	public void removeZone(String id) throws Exception{
		
		Optional<Zone> answer = zoneRepo.findById(id);

		if (answer.isPresent()) {
			Zone zone = answer.get();
			zoneRepo.delete(zone);
	
		} else {
			throw new Exception("no existe una zona con el id solicitado");
		}
		
	}
	
	
}
