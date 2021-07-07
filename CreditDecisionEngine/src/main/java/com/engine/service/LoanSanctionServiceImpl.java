package com.engine.service;

import static com.engine.util.Constants.LOAN_DIVISOR_BY_CREDIT_SCORE;
import static com.engine.util.Constants.LOAN_DIVISOR_BY_FOUR;
import static com.engine.util.Constants.LOAN_DURATION;
import static com.engine.util.Constants.EXISTING_LOAN_ERROR_MESSAGE;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.engine.model.Loan;
import com.engine.model.LoanSanctionInputVo;
import com.engine.repository.LoanRepository;


@Service
public class LoanSanctionServiceImpl implements LoanSanctionService{
	
	@Autowired
    CreditDecisionService creditDecisionService;
	
	@Autowired
	LoanRepository loanRepository;
	
	@Override
	@Transactional
	public Double applyLoan(LoanSanctionInputVo loanSanctionInputVo){
		List<Loan> loans = loanRepository.findBySsnAndClosedDt(loanSanctionInputVo.getSsnNumber(),null);
		Loan existingLoan = null;
		if(!CollectionUtils.isEmpty(loans) && loans.size()>0){
			existingLoan = loans.get(0);
			if(ChronoUnit.DAYS.between(LocalDate.now(), existingLoan.getCrDt()) <=LOAN_DURATION)
			{
				throw new RuntimeException(EXISTING_LOAN_ERROR_MESSAGE);
			}
			
		}
		long creditScore = creditDecisionService.getCreditScore(loanSanctionInputVo.getSsnNumber());
		Double amountSanctioned = null;
		if(creditScore>700){
			amountSanctioned = loanSanctionInputVo.getAnnualIncome() / LOAN_DIVISOR_BY_CREDIT_SCORE;
		}else {
			amountSanctioned = loanSanctionInputVo.getAnnualIncome() / LOAN_DIVISOR_BY_FOUR ;
		}
		Loan loan = new Loan();
		loan.setSsn(loanSanctionInputVo.getSsnNumber());
		loan.setAnnualInc(loanSanctionInputVo.getAnnualIncome());
		loan.setLoanAmount(amountSanctioned);
		loan.setCrDt(LocalDate.now());
		loanRepository.save(loan);
		return amountSanctioned;
	}

}
