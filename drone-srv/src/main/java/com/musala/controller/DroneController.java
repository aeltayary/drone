/**
 * 
 */
package com.musala.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.musala.dto.DroneDto;
import com.musala.dto.MedicationDto;
import com.musala.exception.DroneDoesNotExistException;
import com.musala.exception.OverWeightException;
import com.musala.service.DroneService;

/**
 * @author aeltayary
 *
 */
@RestController
public class DroneController {

	@Autowired
	private DroneService srv;

	@PostMapping("/drones")
	@ResponseStatus(code = HttpStatus.CREATED)
	public DroneDto register(@RequestBody @Valid DroneDto drone) {
		return srv.register(drone);
	}

	
	@PostMapping("/drones/{serialNumber}/medications")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void loadDrone(@PathVariable String serialNumber,@RequestBody List<MedicationDto> medicationList) throws OverWeightException,DroneDoesNotExistException {
		srv.loadDrone(serialNumber, medicationList);
	}
	
	
	
	@GetMapping("/drones/{serialNumber}/medications")
	public List<MedicationDto> getMedication(@PathVariable String serialNumbere) throws DroneDoesNotExistException  {
		return srv.getMedicationList(serialNumbere);
	}
	
	
	
	@GetMapping("/drones")
	public List<DroneDto> getAvaialbleDrones()  {
		return srv.getAvaialbleDrones();
	}
	
	
	@GetMapping("/drones/drones/{serialNumber}/battery")
	public String getBatteryLevel(@PathVariable String serialNumbere)  throws DroneDoesNotExistException {
		return srv.getBatteryLevel(serialNumbere);
	}
	
}
