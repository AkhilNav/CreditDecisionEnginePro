package com.engine;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.engine.model.Loan;
import com.engine.model.LoanSanctionInputVo;
import com.engine.repository.LoanRepository;
import com.engine.service.CreditDecisionService;
import com.engine.service.LoanSanctionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceImplTest {
	
	@InjectMocks
	LoanSanctionServiceImpl loanSanctionServiceImpl;

	@Mock
    CreditDecisionService creditDecisionService;
	
	@Mock
	LoanRepository loanRepository;
	
	@Test
	public void testApplyLoan(){
		LoanSanctionInputVo loanSanctionInputVoObj = buildLoanSanctionInputVo("840027967954");
		Mockito.when(loanRepository.findBySsnAndClosedDt("840027967954",null)).thenReturn(null);
		Mockito.when(creditDecisionService.getCreditScore("840027967954")).thenReturn(600l);
		assertTrue(2500.0==loanSanctionServiceImpl.applyLoan(loanSanctionInputVoObj));
	}
	
	@Test
	public void testApplyLoanWithMoreCreditScore(){
		LoanSanctionInputVo loanSanctionInputVoObj = buildLoanSanctionInputVo("840027967954");
		Mockito.when(loanRepository.findBySsnAndClosedDt("840027967954",null)).thenReturn(null);
		Mockito.when(creditDecisionService.getCreditScore("840027967954")).thenReturn(800l);
		assertTrue(5000.0==loanSanctionServiceImpl.applyLoan(loanSanctionInputVoObj));
	}
	
	@Test(expected=RuntimeException.class)
	public void testApplyLoanWithExistingLoan(){
		LoanSanctionInputVo loanSanctionInputVoObj = buildLoanSanctionInputVo("840027967954");
		Mockito.when(loanRepository.findBySsnAndClosedDt("840027967954",null)).thenReturn(buildLoans("840027967954"));
		Mockito.when(creditDecisionService.getCreditScore("840027967954")).thenReturn(800l);
		assertTrue(5000.0==loanSanctionServiceImpl.applyLoan(loanSanctionInputVoObj));
	}
	
	private LoanSanctionInputVo buildLoanSanctionInputVo(String ssn){
		LoanSanctionInputVo loanSanctionInputVo= new LoanSanctionInputVo();
		loanSanctionInputVo.setAnnualIncome(10000);
		loanSanctionInputVo.setLoanAmount(60000);
		loanSanctionInputVo.setSsnNumber(ssn);
		return loanSanctionInputVo;
	}
	
	private List<Loan> buildLoans(String ssn){
		List<Loan> loans = new ArrayList<>();
		Loan loan = new Loan();
		loan.setCrDt(LocalDate.now());
		loans.add(loan);
		return loans;		
	}
}
