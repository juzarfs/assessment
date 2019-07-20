DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS user_accounts;
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS account_transactions;
 
CREATE TABLE users (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  user_name VARCHAR(250) NOT NULL
);

CREATE TABLE user_roles (
  user_id INT NOT NULL,
  roles VARCHAR(250) NOT NULL
);


CREATE TABLE user_accounts (
  user_id INT NOT NULL,
  account_id INT NOT NULL
);

CREATE TABLE accounts (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  account_number VARCHAR(15) NOT NULL,
  account_name VARCHAR(250) NOT NULL,
  account_type VARCHAR(10) NOT NULL,
  balance DECIMAL NOT NULL,
  ccy VARCHAR(3),
  last_updated date
);

CREATE TABLE transactions (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  transaction_amnt DECIMAL NOT NULL,
  transaction_narrative VARCHAR(250) NOT NULL,
  transaction_type VARCHAR(10) NOT NULL,
  ccy VARCHAR(3),
  value_date date NOT NULL
);

CREATE TABLE account_transactions (
  account_id INT NOT NULL,
  transaction_id INT NOT NULL,
  UNIQUE KEY account_transaction_UNIQUE (account_id,transaction_id)
);

INSERT INTO users (user_name) VALUES
  ('testuser01');
INSERT INTO user_roles (user_id,roles) VALUES
  (1,'ACCESS,UPDATE');

  
INSERT INTO accounts (account_number,account_name,account_type,balance,ccy,last_updated) VALUES
  ('122-1234567-123','testAccount1','Savings',1232.232,'AUD',sysdate);
INSERT INTO accounts (account_number,account_name,account_type,balance,ccy,last_updated) VALUES
  ('122-8893454-123','testAccount2','Current',2232.232,'USD',sysdate);


  
INSERT INTO transactions (transaction_narrative,transaction_type,transaction_amnt,ccy,value_date) VALUES
  ('Online Debit','CREDIT',2232.232,'AUD',sysdate - 5);
INSERT INTO transactions (transaction_narrative,transaction_type,transaction_amnt,ccy,value_date) VALUES
  ('Cheque CL','DEBIT',1000.0,'AUD',sysdate -3 );
INSERT INTO transactions (transaction_narrative,transaction_type,transaction_amnt,ccy,value_date) VALUES
  ('Cheque CL','CREDIT',2232.232,'USD',sysdate -2);
  
  
INSERT INTO user_accounts (user_id,account_id) VALUES
  (1,1);
INSERT INTO user_accounts (user_id,account_id) VALUES
  (1,2);

  
INSERT INTO account_transactions (account_id,transaction_id) VALUES
  (1,1);
INSERT INTO account_transactions (account_id,transaction_id) VALUES
  (1,2);
INSERT INTO account_transactions (account_id,transaction_id) VALUES
  (2,3);

  