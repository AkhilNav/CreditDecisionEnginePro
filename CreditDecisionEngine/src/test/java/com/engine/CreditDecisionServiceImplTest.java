package com.engine;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.engine.model.CreditScore;
import com.engine.repository.CreditScoreRepository;
import com.engine.service.CreditDecisionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CreditDecisionServiceImplTest {
	
	@InjectMocks
	CreditDecisionServiceImpl creditDecisionServiceImpl;
	
	@Mock
	CreditScoreRepository creditScoreRepository;
	
	@Test
	public void testGetCreditScore() throws Exception {
		CreditScore creditScoreObj = buildCreditScore("840027967954");
		Optional<CreditScore> creditSOptionalObject = Optional.of(creditScoreObj);
		Mockito.when(creditScoreRepository.findById("840027967954")).thenReturn(creditSOptionalObject);
		assertTrue(500==creditDecisionServiceImpl.getCreditScore("840027967954"));
     }
	
	private CreditScore buildCreditScore(String ssn){
		CreditScore creditScore= new CreditScore();
		creditScore.setSsn(ssn);
		creditScore.setCreditScore(500l);
		return creditScore;
	}
}
