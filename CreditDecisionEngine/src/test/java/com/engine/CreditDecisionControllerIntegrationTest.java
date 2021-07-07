package com.engine;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.engine.controller.CreditDecisionController;
import com.engine.model.LoanSanctionInputVo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditDecisionControllerIntegrationTest {
	
	@Autowired
	private CreditDecisionController decisionController;
	
	@Test
	public void testCalculateSanctionAmount() throws Exception {
		LoanSanctionInputVo loanSanctionInputVoObj = buildLoanSanctionInputVo("840027967954");
		assertTrue("Sanctioned Loan is:2500.0".equals(decisionController.calculateLoanSanctionAmount(loanSanctionInputVoObj).getBody()));
	    try{
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
