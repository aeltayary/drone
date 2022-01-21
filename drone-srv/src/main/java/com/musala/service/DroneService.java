/**
 * 
 */
package com.musala.service;

import org.springframework.stereotype.Service;

import com.musala.dto.DroneDto;
import com.musala.exception.OverWeightException;

/**
 * @author aeltayary
 *
 */

public interface DroneService  {

	public DroneDto register(DroneDto droneDto) throws OverWeightException;
	
	
}
