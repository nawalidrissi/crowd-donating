package org.mql.crowddonating.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mql.crowddonating.business.IAssociationBusiness;
import org.mql.crowddonating.business.IPublicServices;
import org.mql.crowddonating.business.IUserServices;
import org.mql.crowddonating.models.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class HomeControllerTest {

	private MockMvc mockMvc;
	
	@InjectMocks
	private CaseController controller;
	
	@Mock
    private IUserServices userBusiness;

    @Mock
    private IAssociationBusiness associationBusiness;

    @Mock
    private IPublicServices publicServices;
	
	@BeforeEach
	void setUp() throws Exception {
		controller = new CaseController();
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	void home_ShouldRenderHomeView() throws Exception{
		/*
		List<Case> cases = Arrays.asList(new Case(), new Case());
	        when(publicServices.getAllCases()).thenReturn(cases);

	        mockMvc.perform(get("/cases"))
	            .andExpect(status().isOk())
	            .andExpect(view().name("cases/cases"))
	            .andExpect(model().attribute("cases",cases));
	        verify(publicServices).getAllCases();
	       */
	}

}
