/**
 * 
 */
package com.musala.service;

import java.util.List;

import com.musala.dto.DroneDto;
import com.musala.dto.MedicationDto;
import com.musala.exception.DroneDoesNotExistException;
import com.musala.exception.OverWeightException;

/**
 * @author aeltayary
 *
 */

public interface DroneService  {

	public DroneDto register(DroneDto droneDto);
	
	public void loadDrone(String  serinalNumber,List<MedicationDto> medicationList) throws OverWeightException,DroneDoesNotExistException;
	
	public List<MedicationDto>  getMedicationList(String  serinalNumber) throws DroneDoesNotExistException;
	
	public List<DroneDto>  getAvaialbleDrones();
	
	public String  getBatteryLevel(String  serinalNumber) throws DroneDoesNotExistException;
	
}
