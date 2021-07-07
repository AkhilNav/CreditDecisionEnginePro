package com.engine;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.engine.controller.CreditDecisionController;
import com.engine.model.LoanSanctionInputVo;
import com.engine.service.LoanSanctionService;

@RunWith(MockitoJUnitRunner.class)
public class CreditDecisionControllerUnitTest {
	
	@InjectMocks
	CreditDecisionController decisionController;
	
	@Mock
	LoanSanctionService loanSanctionService;
	
	@Test
	public void testCalculateSanctionAmount() throws Exception {
		LoanSanctionInputVo loanSanctionInputVoObj = buildLoanSanctionInputVo("840027967954");
		
		Mockito.when(loanSanctionService.applyLoan(loanSanctionInputVoObj)).thenReturn(2500.0);
		assertTrue("Sanctioned Loan is:2500.0".equals(decisionController.calculateLoanSanctionAmount(loanSanctionInputVoObj).getBody()));
	    try{
	    	Mockito.when(loanSanctionService.applyLoan(loanSanctionInputVoObj)).thenThrow(new RuntimeException("You Cannot apply a loan with in 30 days"));
	    	decisionController.calculateLoanSanctionAmount(loanSanctionInputVoObj);
	    }catch(Exception ex){
	    	assertTrue("You Cannot apply a loan with in 30 days".equals(ex.getMessage()));
	    }
		
	}
	
	private LoanSanctionInputVo buildLoanSanctionInputVo(String ssn){
		LoanSanctionInputVo loanSanctionInputVo= new LoanSanctionInputVo();
		loanSanctionInputVo.setAnnualIncome(10000);
		loanSanctionInputVo.setLoanAmount(60000);
		loanSanctionInputVo.setSsnNumber(ssn);
		return loanSanctionInputVo;
	}
}
