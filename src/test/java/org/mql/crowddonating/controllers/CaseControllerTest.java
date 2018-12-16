package org.mql.crowddonating.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mql.crowddonating.business.IPublicServices;
import org.mql.crowddonating.models.Case;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CaseControllerTest {

	
	private MockMvc mockMvc;
	
	/*
	@InjectMocks
	private CaseController caseController;
	
	@Mock
	private IPublicServices publicServices;
	
	Case case1;
	Case case2;
	
	@Before
	private void setup() {
		caseController = new CaseController();
		mockMvc = MockMvcBuilders.standaloneSetup(caseController).build();
		
		case1 = new Case();
		case1.setId(1L);
		case2 = new Case();
		case2.setId(2L);
	}

	
	@Test
	void shouldRenderCasesViewV2() throws Exception {
		mockMvc.perform(get("/cases")).andExpect(view().name("cases/cases"));
	}
	
	@Test
	void cases_ShouldIncludeCasesInModel() throws Exception{ 
		List<Case> cases = Arrays.asList(case1, case2);
		
		when(publicServices.getAllCases()).thenReturn(cases);
		mockMvc.perform(get("/cases"))
			.andExpect(status().isOk())
			.andExpect(view().name("cases/cases"))
			.andExpect(model().attribute("cases", cases));
		
		verify(publicServices).getAllCases();
	}
	*/
	
	private HomeController homeController;
	
	@Before
	private void setup() {
		homeController = new HomeController();
		mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
	}

	
	@Test
	void shouldRenderCasesViewV2() throws Exception {
		mockMvc.perform(get("/")).andExpect(view().name("home"));
	}

}
