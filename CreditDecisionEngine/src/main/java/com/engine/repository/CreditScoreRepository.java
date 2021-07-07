package com.engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.engine.model.CreditScore;

public interface CreditScoreRepository extends JpaRepository<CreditScore, String>{

}
