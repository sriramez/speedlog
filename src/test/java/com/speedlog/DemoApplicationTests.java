package com.speedlog;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.speeding.model.PatrolToRetModel;
import com.speedlog.entity.Patrol;
import com.speedlog.serviceImpl.PatrolService;

@SpringBootTest
class DemoApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PatrolService patrolService;

	@Test
	void contextLoads() {
	}
	
	@Test
    public void listAllPatrols()
            throws Exception {
		Patrol patrol = new Patrol();
		patrol.setCarnumber("car1");
		patrol.setCurrentAddress("address1");

        List<PatrolToRetModel> patrols = Arrays.asList( new PatrolToRetModel(patrol));

        given(patrolService
                .getAllPatrolVehicle())
                .willReturn(patrols);

        mockMvc.perform(get("/patrol")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(patrols.size())))
                .andExpect(jsonPath("$[0].carNumber", is(patrol.getCarnumber())));
    }

}
