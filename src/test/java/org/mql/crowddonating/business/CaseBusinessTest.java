package org.mql.crowddonating.business;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mql.crowddonating.business.implementations.PublicServicesBusiness;
import org.mql.crowddonating.dao.CaseRepository;
import org.mql.crowddonating.models.Case;

@RunWith(MockitoJUnitRunner.class)
public class CaseBusinessTest {

    @Mock
    private CaseRepository dao;

    @InjectMocks
    private IPublicServices service = new PublicServicesBusiness();

	@Test
	void getAllCases_ShouldReturn2() throws Exception{
		 List<Case> cases = Arrays.asList(
		            new Case(),
		            new Case()
		        );

		        when(dao.findAll()).thenReturn(cases);

		        assertEquals("findAll should return two favorites",2,service.getAllCases().size());
		        verify(dao).findAll();
	}

	@Test
	void findById_ShouldReturnOne() throws Exception{
		when(dao.findById(1L)).thenReturn(Optional.ofNullable(new Case()));
        assertThat(service.getCaseById(1L),instanceOf(Case.class));
        verify(dao).findById(1L);
	}

	@Test
	void getCaseBySlog_ShouldReturnOne() {
		when(dao.findBySlug("test-demo")).thenReturn(new Case());
        assertThat(service.getCaseBySlug("test-demo"),instanceOf(Case.class));
        verify(dao).findBySlug("test-demo");
	}

}
