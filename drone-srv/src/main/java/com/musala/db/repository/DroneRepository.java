/**
 * 
 */
package com.musala.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.db.entity.Drone;

/**
 * @author aeltayary
 *
 */
public interface DroneRepository extends JpaRepository<Drone, Long> {
	
	Optional<Drone> findBySerialNumber(String serialNumber);

}
