package org.mql.crowddonating.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mql.crowddonating.business.implementations.PublicServicesBusiness;
import org.mql.crowddonating.models.Case;
import org.mql.crowddonating.models.Type;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
class CaseControllerTest {
	
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private final CaseController caseController = new CaseController();
//
//    @Mock
//    private PublicServicesBusiness publicServices =  new PublicServicesBusiness();
//
//    @BeforeEach
//    void setup() {
//    }
//
//    @Test
//    void cases_ShouldRenderCasesView() throws Exception {
//        mockMvc = MockMvcBuilders.standaloneSetup(caseController).build();
//
//        Case case1 = new Case();
//        case1.setId(1);
//        Case case2 = new Case();
//        case2.setId(2);
//        List<Case> cases = Arrays.asList(case1, case2);
//
//        List<Type> types = Arrays.asList(new Type());
//
//        when(publicServices.getAllCases()).thenReturn(cases);
//
//        mockMvc.perform(get("/cases"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("cases/cases"))
//                .andExpect(model().attribute("cases", cases));
//        verify(publicServices).getAllCases();
//    }
}