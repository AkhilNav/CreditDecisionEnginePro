package com.engine.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engine.model.CreditScore;
import com.engine.repository.CreditScoreRepository;
import static com.engine.util.Constants.SSN_NUMBER_NOT_EXISTS_ERROR_MESSAGE;

@Service
public class CreditDecisionServiceImpl implements CreditDecisionService{
	
	@Autowired
	CreditScoreRepository creditScoreRepository;
	
	@Override
	public long getCreditScore(String ssn){
		Optional<CreditScore> creditScore = creditScoreRepository.findById(ssn);
		if(creditScore.isPresent()){
			return creditScore.get().getCreditScore();
		}
		else {
			throw new RuntimeException(SSN_NUMBER_NOT_EXISTS_ERROR_MESSAGE);
		}
	}
	
	
}
