package com.tuhuella.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.tuhuella.main.entities.Zone;

public interface ZoneRepository extends JpaRepository<Zone, String> {
	
	@Query("SELECT a from Zone a WHERE a.city = :city ORDER BY a.city")
	public List<Zone> findByCity(@Param("city") String city);
	
	@Query("SELECT a from Zone a WHERE a.country = :country ORDER BY a.country")
	public List<Zone> findByCountry(@Param("country") String country);
	
	@Query("SELECT a from Zone a WHERE a.province = :province ORDER BY a.province")
	public List<Zone> findByProvince(@Param("province") String province);
	
	@Query("SELECT a from Zone a WHERE a.neighborhood = :neighborhood ORDER BY a.neighborhood")
	public List<Zone> findByneighborhood(@Param("neighborhood") String neighborhood);
}
