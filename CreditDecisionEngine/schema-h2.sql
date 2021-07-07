DROP TABLE IF EXISTS CREDIT_SCORE;

DROP TABLE IF EXISTS LOANS;

CREATE TABLE CREDIT_SCORE (
  ssn VARCHAR(250) PRIMARY KEY,
  score INT NOT NULL
);

CREATE TABLE LOANS (
  loan_id INT AUTO_INCREMENT  PRIMARY KEY,
  ssn VARCHAR(250) ,
  annual_income float ,
  approved_loan_amount float ,
  cr_dt DATE,
  closed_dt DATE 
);
