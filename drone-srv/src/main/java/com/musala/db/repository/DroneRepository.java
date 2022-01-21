/**
 * 
 */
package com.musala.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.db.entity.Drone;

/**
 * @author aeltayary
 *
 */
public interface DroneRepository extends JpaRepository<Drone, Long> {

}
