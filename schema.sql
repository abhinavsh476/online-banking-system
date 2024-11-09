-- Drop the database if it exists (this will remove the old one completely)
DROP DATABASE IF EXISTS online_banking;

-- Create a new database
CREATE DATABASE online_banking;

-- Switch to the new database
USE online_banking;

-- Branches Table
CREATE TABLE Branches (
    branch_id INT PRIMARY KEY AUTO_INCREMENT,
    branch_name VARCHAR(100),
    branch_address TEXT,
    branch_phone VARCHAR(15)
) ENGINE=InnoDB;

-- Customers Table
CREATE TABLE Customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(255),
    phone_number VARCHAR(15),
    address TEXT,
    date_of_birth DATE,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- Accounts Table
CREATE TABLE Accounts (
    account_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    account_type VARCHAR(50),
    balance DECIMAL(15,2),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50),
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
) ENGINE=InnoDB;

-- Transactions Table
CREATE TABLE Transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT,
    transaction_type VARCHAR(50),
    amount DECIMAL(15,2),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description TEXT,
    FOREIGN KEY (account_id) REFERENCES Accounts(account_id)
) ENGINE=InnoDB;

-- Loans Table
CREATE TABLE Loans (
    loan_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    loan_type VARCHAR(50),
    loan_amount DECIMAL(15,2),
    interest_rate DECIMAL(5,2),
    start_date DATE,
    end_date DATE,
    status VARCHAR(50),
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
) ENGINE=InnoDB;

-- Employees Table
CREATE TABLE Employees (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(255),
    job_title VARCHAR(100),
    branch_id INT,
    hire_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (branch_id) REFERENCES Branches(branch_id)
) ENGINE=InnoDB;
