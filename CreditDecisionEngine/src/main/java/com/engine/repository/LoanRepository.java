package com.engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.engine.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer>{
	
	List<Loan> findBySsnAndClosedDt(String ssnNumber, String closedDt);

}
