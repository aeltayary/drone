/**
 * 
 */
package com.musala.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.db.entity.Cargo;
import com.musala.db.entity.Drone;
import com.musala.db.entity.Model;
import com.musala.db.entity.State;
import com.musala.db.repository.CargoRepository;
import com.musala.db.repository.DroneRepository;
import com.musala.db.repository.ModelRepository;
import com.musala.db.repository.StateRepository;
import com.musala.dto.DroneDto;
import com.musala.dto.MedicationDto;
import com.musala.exception.DroneDoesNotExistException;
import com.musala.exception.OverWeightException;
import com.musala.service.DroneService;

/**
 * @author aeltayary
 *
 */
@Service
public class DroneServiceImpl implements DroneService {

	private static final String OVER_WEIGHT_MSG_PATTERN = "Items total weight %d exceeds drome weight limit {1}";

	private static final String DRONE_DOES_NOT_EXIST_MSG_PATTERN = "Drone with serial number %s  does not exist";

	@Autowired
	private DroneRepository droneRepo;

	@Autowired
	private CargoRepository cargoRepo;

	@Autowired
	private ModelRepository modelRepo;

	@Autowired
	private StateRepository stateRepo;


	/**
	 * 
	 * @param droneDto
	 * @return
	 */
	@Override
	public DroneDto register(DroneDto droneDto) {
		Drone droneBean = new Drone();
		BeanUtils.copyProperties(droneDto, droneBean);
		Optional<Model> modelBeanResult = modelRepo.findById(droneDto.getModel().getId());
		Optional<State> stateBeanResult = stateRepo.findById(droneDto.getState().getId());
		droneBean.setModel(modelBeanResult.isEmpty() ? null : modelBeanResult.get());
		droneBean.setState(stateBeanResult.isEmpty() ? null : stateBeanResult.get());
		droneRepo.save(droneBean);
		return droneDto;
	}


	/**
	 * 
	 * @param serinalNumber
	 * @param medicationList
	 * @throws OverWeightException
	 * @throws DroneDoesNotExistException
	 */

	@Override
	public void loadDrone(String serinalNumber, List<MedicationDto> medicationList)
			throws OverWeightException, DroneDoesNotExistException {
		Optional<Drone> droneResult = droneRepo.findBySerialNumber(serinalNumber);
		if (droneResult.isPresent()) {
			Drone droneBean = droneResult.get();
			Integer totalWeight = medicationList.stream().mapToInt(MedicationDto::getMedicationWeight).sum();
			if (totalWeight.compareTo(droneBean.getWeightLimit()) > 0) {
				String msg = String.format(OVER_WEIGHT_MSG_PATTERN, totalWeight, droneBean.getWeightLimit());
				throw new OverWeightException(msg);
			} else {
				List<Cargo> cargoBeanList = new ArrayList<>();
				medicationList.stream().forEach(med -> {
					Cargo cargoBean = new Cargo();
					BeanUtils.copyProperties(med, cargoBean);
					cargoBean.setDrone(droneBean);
					cargoBeanList.add(cargoBean);
				});

				cargoRepo.saveAll(cargoBeanList);

			}
		} else {
			String msg = String.format(DRONE_DOES_NOT_EXIST_MSG_PATTERN, serinalNumber);
			throw new DroneDoesNotExistException(msg);
		}

	}

	/**
	 * 
	 * @param serinalNumber
	 * @return
	 * @throws DroneDoesNotExistException
	 */
	@Override
	public List<MedicationDto> getMedicationList(String serinalNumber) throws DroneDoesNotExistException {
		return null;
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public List<DroneDto> getAvaialbleDrones() {
		return null;
	}

	/**
	 * 
	 * @param serinalNumber
	 * @return
	 * @throws DroneDoesNotExistException
	 */

	@Override
	public String getBatteryLevel(String serinalNumber) throws DroneDoesNotExistException {
		return null;
	}

}
