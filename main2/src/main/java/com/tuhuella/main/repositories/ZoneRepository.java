package com.tuhuella.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuhuella.main.entities.Zone;

public interface ZoneRepository extends JpaRepository<Zone, String> {

}
