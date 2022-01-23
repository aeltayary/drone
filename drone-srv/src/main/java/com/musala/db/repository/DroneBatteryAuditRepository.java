/**
 * 
 */
package com.musala.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.db.entity.DroneAudit;

/**
 * @author aeltayary
 *
 */
public interface DroneBatteryAuditRepository extends JpaRepository<DroneAudit, Integer> {

}
