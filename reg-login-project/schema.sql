-- Run this once in MySQL Workbench / mysql CLI before starting the app.

CREATE DATABASE IF NOT EXISTS reg_db;
USE reg_db;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    enroll_id VARCHAR(50) NOT NULL,
    batch VARCHAR(50) NOT NULL,
    dob DATE NOT NULL,
    official_email VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    mobile_no VARCHAR(20) NOT NULL,
    user_id VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(64) NOT NULL
);

-- No sample rows this time on purpose: the whole point of this project is
-- that rows get created by using the Register page yourself.
