/**
 * 
 */
package com.musala.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
import com.musala.enums.StateEnum;
import com.musala.exception.DroneAlreadyExistException;
import com.musala.exception.DroneDoesNotExistException;
import com.musala.exception.LowBatteryException;
import com.musala.exception.OverWeightException;
import com.musala.service.DroneService;

/**
 * @author aeltayary
 *
 */
@Service
@Transactional
public class DroneServiceImpl implements DroneService {

	private static final String OVER_WEIGHT_MSG_PATTERN = "Items total weight %d exceeds drome weight limit %d";

	private static final String DRONE_ALREADY_EXISTS_PATTERN = "Drone with serial number %s  already exists";

	private static final String DRONE_DOES_NOT_EXIST_MSG_PATTERN = "Drone with serial number %s  does not exist";

	private static final String DRONE_LOW_BATTERY_MSG_PATTERN = "Drone with serial number %s  has low batter";

	private static final Integer LOW_BATTERY_PERECENTAGE = 25;

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
	public DroneDto register(DroneDto droneDto) throws DroneAlreadyExistException {
		Optional<Drone> droneResult = droneRepo.findBySerialNumber(droneDto.getSerialNumber());
		if (droneResult.isPresent()) {
			String msg = String.format(DRONE_ALREADY_EXISTS_PATTERN, droneDto.getSerialNumber());
			throw new DroneAlreadyExistException(msg);
		}

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
	 * @throws LowBatteryException
	 */

	@Override
	public void loadDrone(String serinalNumber, List<MedicationDto> medicationList)
			throws OverWeightException, DroneDoesNotExistException, LowBatteryException {
		Optional<Drone> droneResult = droneRepo.findBySerialNumber(serinalNumber);
		if (droneResult.isPresent()) {
			Drone droneBean = droneResult.get();
			validateCargo(medicationList, droneBean);
			List<Cargo> cargoBeanList = new ArrayList<>();
			medicationList.stream().forEach(med -> {
				Cargo cargoBean = new Cargo();
				BeanUtils.copyProperties(med, cargoBean);
				cargoBean.setDrone(droneBean);
				cargoBeanList.add(cargoBean);
			});

			cargoRepo.saveAll(cargoBeanList);
		} else {
			String msg = String.format(DRONE_DOES_NOT_EXIST_MSG_PATTERN, serinalNumber);
			throw new DroneDoesNotExistException(msg);
		}

	}

	/**
	 * @param medicationList
	 * @param droneBean
	 * @throws OverWeightException
	 * @throws LowBatteryException
	 */
	private void validateCargo(List<MedicationDto> medicationList, Drone droneBean)
			throws OverWeightException, LowBatteryException {
		if(droneBean.getBattery().compareTo(LOW_BATTERY_PERECENTAGE)<0) {
			String msg = String.format(DRONE_LOW_BATTERY_MSG_PATTERN, droneBean.getSerialNumber());
			throw new LowBatteryException(msg);
		} 
		Integer currentWeight = droneBean.getCargo().stream().mapToInt(Cargo::getMedicationWeight).sum();
		Integer requestWeight = medicationList.stream().mapToInt(MedicationDto::getMedicationWeight).sum();
		Integer total = currentWeight + requestWeight;
		if (total.compareTo(droneBean.getWeightLimit()) > 0) {
			String msg = String.format(OVER_WEIGHT_MSG_PATTERN, requestWeight, droneBean.getWeightLimit());
			throw new OverWeightException(msg);
		}else if(total.compareTo(droneBean.getWeightLimit()) == 0) {
			droneBean.setState(stateRepo.findById(StateEnum.LOADED.getId()).get());
		}else {
			droneBean.setState(stateRepo.findById(StateEnum.LOADING.getId()).get());
		}
		droneRepo.save(droneBean);
	}

	/**
	 * 
	 * @param serinalNumber
	 * @return
	 * @throws DroneDoesNotExistException
	 */
	@Override
	public List<MedicationDto> getMedicationList(String serinalNumber) throws DroneDoesNotExistException {
		Optional<Drone> droneResult = droneRepo.findBySerialNumber(serinalNumber);
		if (droneResult.isPresent()) {
			List<MedicationDto> result = new ArrayList<>();
			droneResult.get().getCargo().stream().forEach(medBean -> {
				MedicationDto medDto = new MedicationDto();
				BeanUtils.copyProperties(medBean, medDto);
				result.add(medDto);
			});
			return result;

		} else {
			String msg = String.format(DRONE_DOES_NOT_EXIST_MSG_PATTERN, serinalNumber);
			throw new DroneDoesNotExistException(msg);
		}

	}


	/**
	 * @param serinalNumber
	 * @return droneDto
	 * @throws DroneDoesNotExistException
	 */
	@Override
	public void updateDrone(DroneDto droneDto)
			throws OverWeightException, DroneDoesNotExistException {
		Optional<Drone> droneResult = droneRepo.findBySerialNumber(droneDto.getSerialNumber());
		if (droneResult.isPresent()) {
			Drone droneBean = droneResult.get();
			Optional<Model> modelBeanResult = modelRepo.findById(droneDto.getModel().getId());
			Optional<State> stateBeanResult = stateRepo.findById(droneDto.getState().getId());
			droneBean.setModel(modelBeanResult.isEmpty() ? null : modelBeanResult.get());
			droneBean.setState(stateBeanResult.isEmpty() ? null : stateBeanResult.get());
			droneRepo.save(droneBean);

		} else {
			String msg = String.format(DRONE_DOES_NOT_EXIST_MSG_PATTERN, droneDto.getSerialNumber());
			throw new DroneDoesNotExistException(msg);
		}

	}

	/**
	 * 
	 * @return
	 */
	@Override
	public List<DroneDto> getAvaialbleDrones() {
		Optional<State> stateBeanResult = stateRepo.findById(StateEnum.LOADING.getId());
		List<DroneDto> result = new ArrayList<DroneDto>();
		List<Drone> droneBeanList = droneRepo.findByState(stateBeanResult.get());
		droneBeanList.forEach(droneBean -> {
			DroneDto droneDto = new DroneDto();
			BeanUtils.copyProperties(droneBean, droneDto);
			result.add(droneDto);

		});

		return result;
	}

	/**
	 * 
	 * @param serinalNumber
	 * @return
	 * @throws DroneDoesNotExistException
	 */

	@Override
	public String getBatteryLevel(String serinalNumber) throws DroneDoesNotExistException {
		Optional<Drone> droneResult = droneRepo.findBySerialNumber(serinalNumber);
		if (droneResult.isPresent()) {
			Drone droneBean = droneResult.get();
			return droneBean.getBattery().toString();
		} else {
			String msg = String.format(DRONE_DOES_NOT_EXIST_MSG_PATTERN, serinalNumber);
			throw new DroneDoesNotExistException(msg);
		}

	}


}
