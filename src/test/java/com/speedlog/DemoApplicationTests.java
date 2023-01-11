package com.speedlog;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.speeding.model.PatrolToRetModel;
import com.speeding.model.VehicleModel;
import com.speedlog.entity.Patrol;
import com.speedlog.entity.Vehicle;
import com.speedlog.repository.VehicleRepository;
import com.speedlog.serviceImpl.PatrolService;
import com.speedlog.serviceImpl.VehicleService;

@SpringBootTest
class DemoApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PatrolService patrolService;
	
	@MockBean
	private VehicleService vehicleService;	
	
	@Mock
	VehicleRepository vehicleRepo;

	@Test
	void contextLoads() {
	}
	
	@Test
    public void listAllPatrols()
            throws Exception {
		Patrol patrol = new Patrol();
		patrol.setCarNumber("car1");
		patrol.setCurrentAddress("address1");

        List<PatrolToRetModel> patrols = Arrays.asList( new PatrolToRetModel(patrol));

        given(patrolService
                .getAllPatrolVehicle())
                .willReturn(patrols);

        mockMvc.perform(get("/patrol")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(patrols.size())))
                .andExpect(jsonPath("$[0].carNumber", is(patrol.getCarNumber())));
    }
	
	@Test
    public void listVehiclesByVehicleNumber()
            throws Exception {
		Vehicle vehicle = new Vehicle();
		vehicle.setCarNumber("car number1");
		vehicle.setCurrentAddress("address1");


        given(vehicleService
                .getCarInfo(vehicle.getCarNumber()))
                .willReturn(new VehicleModel(vehicle));

        when(vehicleRepo.findByCarNumber(vehicle.getCarNumber())).thenReturn(vehicle);

		VehicleModel expected = vehicleService.getCarInfo(vehicle.getCarNumber());

		assertThat(expected.getCarNumber()).isSameAs(vehicle.getCarNumber());
		verify(vehicleRepo).findByCarNumber(vehicle.getCarNumber());
    }

}
