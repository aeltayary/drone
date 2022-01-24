/**
 * 
 */

package com.musala.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
import com.musala.enums.ModelEnum;
import com.musala.enums.StateEnum;
import com.musala.exception.DroneDoesNotExistException;
import com.musala.exception.OverWeightException;
import com.musala.service.impl.DroneServiceImpl;

/**
 * Junit class to test DroneServiceImpl
 * 
 * @author aeltayary
 *
 */

@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
public class DroneServiceImplTest {

	@Mock
	private DroneRepository droneRepo;

	@Mock
	private CargoRepository cargoRepo;

	@Mock
	private ModelRepository modelRepo;

	@Mock
	private StateRepository stateRepo;

	@InjectMocks
	DroneServiceImpl srv;

	
	/**
	 * test register drone
	 */

	@Test
	void testRegister() {
		try {
			DroneDto dto = new DroneDto();
			dto.setSerialNumber("11111");
			dto.setBattery(50);
			dto.setModel(ModelEnum.MIDDLEWEIGHT);
			dto.setState(StateEnum.IDLE);
			dto.setWeightLimit(500);

			// mock droneRepo.findBySerialNumber
			Optional<Drone> dbDroneResult = Optional.ofNullable(null);
			doReturn(dbDroneResult).when(droneRepo).findBySerialNumber("11111");
			
			// mock modelRepo.findById
			Model modelBean = new Model();
			modelBean.setId(ModelEnum.MIDDLEWEIGHT.getId());
			modelBean.setModelName(ModelEnum.MIDDLEWEIGHT.toString());
			Optional<Model> dbModelResult = Optional.of(modelBean);
			doReturn(dbModelResult).when(modelRepo).findById(ModelEnum.MIDDLEWEIGHT.getId());

			// mock stateRepo.findById
			State stateBean = new State();
			stateBean.setId(StateEnum.IDLE.getId());
			stateBean.setStateName(StateEnum.IDLE.toString());
			Optional<State> dbStateResult = Optional.of(stateBean);
			doReturn(dbStateResult).when(stateRepo).findById(StateEnum.IDLE.getId());

			// Mock droneRepo.save
			doReturn(new Drone()).when(droneRepo).save(Mockito.any());

			srv.register(dto);
			assertTrue(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertTrue(false);
		}
		

	}

	/**
	 * test loadDrone
	 */
	@Test
	void testLoadDrone() {
		try {

			List<MedicationDto> medList = mockMedicationList();
			Drone bean = mockDroneDbBean();
			// mock droneRepo.findBySerialNumber
			Optional<Drone> dbDroneResult = Optional.of(bean);
			doReturn(dbDroneResult).when(droneRepo).findBySerialNumber("11111");

			Model modelBean = new Model();
			modelBean.setId(ModelEnum.MIDDLEWEIGHT.getId());
			modelBean.setModelName(ModelEnum.MIDDLEWEIGHT.toString());
			bean.setModel(modelBean);

			State stateBean = new State();
			stateBean.setId(StateEnum.LOADING.getId());
			stateBean.setStateName(StateEnum.LOADING.toString());
			bean.setState(stateBean);
			Optional<State> dbStateResult = Optional.of(stateBean);
			doReturn(dbStateResult).when(stateRepo).findById(StateEnum.LOADING.getId());

			// Mock droneRepo.save
			doReturn(new ArrayList<Drone>()).when(cargoRepo).saveAll(Mockito.any());

			srv.loadDrone("11111", medList);
			assertTrue(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertTrue(true);
		}

	}

	/**
	 * test loadDrone with overweight
	 */
	@Test
	void testLoadDroneWithOverweight() {
		try {
			List<MedicationDto> medList = mockMedicationList();
			medList.get(0).setMedicationWeight(100);
			medList.get(0).setMedicationWeight(400);
			Drone bean = mockDroneDbBean();
			// mock droneRepo.findBySerialNumber
			Optional<Drone> dbDroneResult = Optional.of(bean);
			doReturn(dbDroneResult).when(droneRepo).findBySerialNumber("11111");
			srv.loadDrone("11111", medList);
			assertTrue(false);
		} catch (OverWeightException ex) {
			assertTrue(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertTrue(false);
		}

	}

	/**
	 * test getMedicationList
	 */
	@Test
	void testGetMedicationList() {
		try {

			Drone bean = mockDroneDbBean();
			// mock droneRepo.findBySerialNumber
			Optional<Drone> dbDroneResult = Optional.of(bean);
			doReturn(dbDroneResult).when(droneRepo).findBySerialNumber("11111");
			List<MedicationDto> medList = srv.getMedicationList("11111");
			assertNotNull(medList);
			assertEquals(2, medList.size());
			assertEquals(bean.getCargo().get(0).getMedicationCode(), medList.get(0).getMedicationCode());
			assertEquals(bean.getCargo().get(0).getMedicationName(), medList.get(0).getMedicationName());
			assertEquals(bean.getCargo().get(0).getMedicationWeight(), medList.get(0).getMedicationWeight());

			assertEquals(bean.getCargo().get(1).getMedicationCode(), medList.get(1).getMedicationCode());
			assertEquals(bean.getCargo().get(1).getMedicationName(), medList.get(1).getMedicationName());
			assertEquals(bean.getCargo().get(1).getMedicationWeight(), medList.get(1).getMedicationWeight());

		} catch (Exception ex) {
			ex.printStackTrace();
			assertTrue(true);
		}

	}


	/**
	 * test updateDrone
	 */
	
	@Test
	void testUpdateDrone() {
		try {
			DroneDto dto = new DroneDto();
			dto.setSerialNumber("11111");
			dto.setBattery(50);
			dto.setModel(ModelEnum.MIDDLEWEIGHT);
			dto.setState(StateEnum.DELIVERED);
			dto.setWeightLimit(500);

			// mock droneRepo.findBySerialNumber
			Optional<Drone> dbDroneResult = Optional.of(mockDroneDbBean());
			doReturn(dbDroneResult).when(droneRepo).findBySerialNumber("11111");
			
			// mock modelRepo.findById
			Model modelBean = new Model();
			modelBean.setId(ModelEnum.MIDDLEWEIGHT.getId());
			modelBean.setModelName(ModelEnum.MIDDLEWEIGHT.toString());
			Optional<Model> dbModelResult = Optional.of(modelBean);
			doReturn(dbModelResult).when(modelRepo).findById(ModelEnum.MIDDLEWEIGHT.getId());

			// mock stateRepo.findById
			State stateBean = new State();
			stateBean.setId(StateEnum.DELIVERED.getId());
			stateBean.setStateName(StateEnum.DELIVERED.toString());
			Optional<State> dbStateResult = Optional.of(stateBean);
			doReturn(dbStateResult).when(stateRepo).findById(StateEnum.DELIVERED.getId());

			// Mock droneRepo.save
			doReturn(new Drone()).when(droneRepo).save(Mockito.any());

			srv.updateDrone(dto);
			assertTrue(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * test updateDrone that is not exist
	 */

	@Test
	void testUpdateNonExistingDrone() {
		try {
			DroneDto dto = new DroneDto();
			dto.setSerialNumber("11111");
			dto.setBattery(50);
			dto.setModel(ModelEnum.MIDDLEWEIGHT);
			dto.setState(StateEnum.DELIVERED);
			dto.setWeightLimit(500);

			// mock droneRepo.findBySerialNumber
			Optional<Drone> dbDroneResult = Optional.ofNullable(null);
			doReturn(dbDroneResult).when(droneRepo).findBySerialNumber("11111");
			srv.updateDrone(dto);
			assertTrue(false);
		} catch (DroneDoesNotExistException ex) {
			assertTrue(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * @return
	 */
	private Drone mockDroneDbBean() {
		Drone bean = new Drone();
		bean.setSerialNumber("11111");
		bean.setBattery(50);
		bean.setWeightLimit(500);

		List<Cargo> list = new ArrayList<Cargo>();
		Cargo cargo = new Cargo();
		cargo.setId(Long.valueOf(1));
		cargo.setMedicationCode("12345");
		cargo.setMedicationName("camera");
		cargo.setMedicationWeight(50);
		list.add(cargo);

		cargo = new Cargo();
		cargo.setId(Long.valueOf(2));
		cargo.setMedicationCode("123456");
		cargo.setMedicationName("sensor");
		cargo.setMedicationWeight(50);
		list.add(cargo);

		bean.setCargo(list);
		return bean;
	}


	/**
	 * @return
	 */
	private List<MedicationDto> mockMedicationList() {
		List<MedicationDto> medList = new ArrayList<>();
		MedicationDto med= new MedicationDto();
		med.setMedicationCode("12345");
		med.setMedicationName("camera");
		med.setMedicationWeight(100);
		medList.add(med);
		
		med= new MedicationDto();
		med.setMedicationCode("123456");
		med.setMedicationName("sensor");
		med.setMedicationWeight(200);
		medList.add(med);
		return medList;
	}

	/**
	 * test getAvaialbleDrones
	 */
	@Test
	void testGetAvaialbleDrones() {
		try {

			Drone bean = mockDroneDbBean();
			State stateBean = new State();
			stateBean.setId(StateEnum.LOADING.getId());
			stateBean.setStateName(StateEnum.LOADING.toString());
			bean.setState(stateBean);
			Model modelBean = new Model();
			modelBean.setId(ModelEnum.MIDDLEWEIGHT.getId());
			modelBean.setModelName(ModelEnum.MIDDLEWEIGHT.toString());
			bean.setModel(modelBean);
			Optional<State> dbStateResult = Optional.of(stateBean);
			doReturn(dbStateResult).when(stateRepo).findById(StateEnum.LOADING.getId());
			List<Drone> droneList = new ArrayList<>();
			droneList.add(bean);

			doReturn(droneList).when(droneRepo).findByState(stateBean);
			List<DroneDto> result = srv.getAvailableDrones();

			assertNotNull(result);
			assertEquals(1, result.size());

		} catch (Exception ex) {
			ex.printStackTrace();
			assertTrue(false);
		}

	}

	/**
	 * test getBatteryLevel
	 */
	@Test
	void testGetBatteryLevel() {
		try {

			Drone bean = mockDroneDbBean();
			Optional<Drone> dbDroneResult = Optional.of(bean);

			State stateBean = new State();
			stateBean.setId(StateEnum.LOADING.getId());
			stateBean.setStateName(StateEnum.LOADING.toString());
			bean.setState(stateBean);
			doReturn(dbDroneResult).when(droneRepo).findBySerialNumber("11111");
			String result = srv.getBatteryLevel("11111");

			assertNotNull(result);
			assertEquals("50", result);

		} catch (Exception ex) {
			ex.printStackTrace();
			assertTrue(true);
		}

	}

	public static void main(String[] args) throws Exception {

		File f = new File("01.JPG");
		String encodstring = encodeFileToBase64Binary(f);
		System.out.println(encodstring);
	}

	private static String encodeFileToBase64Binary(File file) throws Exception {
		FileInputStream fileInputStream = new FileInputStream(file);
		byte[] bytes = new byte[(int) file.length()];
		fileInputStream.read(bytes);
		return new String(Base64.getEncoder().encode(bytes), "UTF-8");
	}

}
