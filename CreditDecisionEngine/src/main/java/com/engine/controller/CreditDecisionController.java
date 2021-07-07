package com.engine.controller;

import static com.engine.util.Constants.CALCULATE_LOAN_SANCTION_AMOUNT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.engine.model.LoanSanctionInputVo;
import com.engine.service.LoanSanctionService;

@RestController
public class CreditDecisionController {

	public static final Logger logger = LoggerFactory.getLogger(CreditDecisionController.class);

	@Autowired
	LoanSanctionService loanSanctionService;	
	
	@RequestMapping(value = CALCULATE_LOAN_SANCTION_AMOUNT, method = RequestMethod.POST)
	public ResponseEntity<?> calculateLoanSanctionAmount(@RequestBody LoanSanctionInputVo loanSanctionInputVo) {
		logger.info("Calculate Loan Sanction: {}", loanSanctionInputVo);
		Double sanctionedAmount =loanSanctionService.applyLoan(loanSanctionInputVo);
		return new ResponseEntity<String>("Sanctioned Loan is:"+sanctionedAmount,HttpStatus.OK);
	}

}