/**
 * 
 */
package com.musala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.musala.dto.DroneDto;
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
	public DroneDto register(@RequestBody DroneDto drone) throws OverWeightException {
		return srv.register(drone);
	}

}
