/**
 * 
 */
package com.musala.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.musala.db.entity.Drone;
import com.musala.db.entity.DroneAudit;
import com.musala.db.repository.DroneBatteryAuditRepository;
import com.musala.db.repository.DroneRepository;
import com.musala.service.DroneAuditService;

/**
 * @author aeltayary
 *
 */
@Service
@Transactional
public class DroneBatteryAuditServiceImpl implements DroneAuditService {



	private static final Integer CHECK_BATTERY_ACTIVITY = Integer.valueOf(1);

	private static final Integer DUMMY_BATTERY_REDUCTION_RATE = Integer.valueOf(5);

	@Autowired
	private DroneBatteryAuditRepository droneAuditRepo;

	@Autowired
	private DroneRepository droneRepo;

	/**
	 * Dummy service to update batter level while flying battery level will be
	 * deducted by 5% each minute
	 */
	@Scheduled(fixedRate = 60000)
	@Override
	public void updateBatterLevel() {
		List<Drone> droneList = droneRepo.findAll();
		List<DroneAudit> droneAuditList = new ArrayList<>();
		droneList.stream().filter(droneBean -> droneBean.getBattery().compareTo(0) > 0).forEach(droneBean -> {
			DroneAudit auditBean = new DroneAudit();
			auditBean.setDrone(droneBean);
			auditBean.setActivityId(CHECK_BATTERY_ACTIVITY);
			auditBean.setOldVlaue(droneBean.getBattery().toString());
			Integer currentValue = droneBean.getBattery() - DUMMY_BATTERY_REDUCTION_RATE > 0
					? droneBean.getBattery() - DUMMY_BATTERY_REDUCTION_RATE
					: 0;
			auditBean.setNewValue(currentValue.toString());
			droneBean.setBattery(currentValue);
			droneAuditList.add(auditBean);

		});

		droneAuditRepo.saveAll(droneAuditList);
	}

}
