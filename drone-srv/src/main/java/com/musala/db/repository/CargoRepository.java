/**
 * 
 */
package com.musala.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.db.entity.Cargo;

/**
 * @author aeltayary
 *
 */
public interface CargoRepository extends JpaRepository<Cargo, Long> {

}
