package com.engine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="CREDIT_SCORE")
public class CreditScore {
	
	@Id
	private String ssn;
	@Column(name="score")
	private Long creditScore;
	
	public CreditScore(){
		
	}
	
	public CreditScore(String ssn, long creditScore) {
		this.ssn=ssn;
		this.creditScore=creditScore;
	}
	
	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Long getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(Long creditScore) {
		this.creditScore = creditScore;
	}
}