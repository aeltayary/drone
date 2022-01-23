/**
 * 
 */
package com.musala.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.db.entity.Drone;
import com.musala.db.entity.State;

/**
 * @author aeltayary
 *
 */
public interface DroneRepository extends JpaRepository<Drone, Long> {
	
	Optional<Drone> findBySerialNumber(String serialNumber);

	List<Drone> findByState(State state);

}
