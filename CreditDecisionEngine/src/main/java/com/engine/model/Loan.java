package com.engine.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LOANS")
public class Loan {
	
	public Loan(){
		}
	@Id
	@GeneratedValue
	@Column(name="loan_id")
	private int loan_id;
	@Column(name="ssn")
	private String ssn;
	@Column(name="approved_loan_amount")
	private double loanAmount;
    @Column(name="annual_income")
	private double annualInc;
	@Column(name="cr_dt")
	private LocalDate crDt;
	@Column(name="closed_dt")
	private LocalDate closedDt;
	
	public int getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public double getAnnualInc() {
		return annualInc;
	}
	public void setAnnualInc(double annualIncome) {
		this.annualInc = annualIncome;
	}
	public LocalDate getCrDt() {
		return crDt;
	}
	public void setCrDt(LocalDate crDt) {
		this.crDt = crDt;
	}
	public LocalDate getClosedDt() {
		return closedDt;
	}
	public void setClosedDt(LocalDate closedDt) {
		this.closedDt = closedDt;
	}
}
