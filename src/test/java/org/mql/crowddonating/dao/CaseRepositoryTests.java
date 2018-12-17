package org.mql.crowddonating.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mql.crowddonating.models.Case;
import org.mql.crowddonating.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
class CaseRepositoryTests {

	@Autowired
	private CaseRepository caseDao;
	
	private Case expectedCase;
	
	@BeforeEach
	private void setup() {
		expectedCase = new Case();
		expectedCase.setName("case test");
		expectedCase.setSlug(Utility.toSlug(expectedCase.getName()));
	}
	
	@Test
	void add_ShouldAddMyCase() {
		
		Case actualCase = caseDao.save(expectedCase);
		assertEquals(expectedCase.getName(), actualCase.getName());
		assertEquals(expectedCase.getSlug(), actualCase.getSlug());
		
	}
	
	
	@AfterEach
	private void destroy() {
		caseDao.delete(expectedCase);
	}
	
}
