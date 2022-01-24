package com.musala;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.musala.service.DroneService;

@SpringBootTest
//@AutoConfigureMockMvc
class DroneSrvApplicationTests {

	@MockBean
	private DroneService srv;


	@Test
	void contextLoads() {

		System.out.println(srv.toString());
	}

}
