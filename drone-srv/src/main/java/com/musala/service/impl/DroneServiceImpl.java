/**
 * 
 */
package com.musala.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.db.repository.DroneRepository;
import com.musala.dto.DroneDto;

import com.musala.exception.OverWeightException;
import com.musala.service.DroneService;

/**
 * @author aeltayary
 *
 */
@Service
public class DroneServiceImpl implements DroneService {

	@Autowired
	private DroneRepository droneRepo;

	@Override
	public DroneDto register(DroneDto droneDto) throws OverWeightException {
		return null;
	}
}
