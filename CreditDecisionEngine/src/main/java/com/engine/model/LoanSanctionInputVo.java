package com.engine.model;

public class LoanSanctionInputVo {
	private String ssnNumber;
	private double loanAmount;
	private double annualIncome;

	public LoanSanctionInputVo() {

	}

	public LoanSanctionInputVo(String ssnNumber, double loanAmount, double annualIncome) {
		this.ssnNumber = ssnNumber;
		this.loanAmount = loanAmount;
		this.annualIncome = annualIncome;
	}

	public String getSsnNumber() {
		return ssnNumber;
	}

	public void setSsnNumber(String ssnNumber) {
		this.ssnNumber = ssnNumber;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}

}
