-- Run this once in MySQL Workbench / mysql CLI before starting the app.

CREATE DATABASE IF NOT EXISTS ems_db;
USE ems_db;

CREATE TABLE IF NOT EXISTS employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL,
    salary DECIMAL(10,2) NOT NULL
);

-- 5 sample records so the app has data to show right away
INSERT INTO employees (name, email, department, salary) VALUES
('Anik Biswas', 'anik.biswas@example.com', 'Engineering', 55000.00),
('Riya Sharma', 'riya.sharma@example.com', 'Human Resources', 48000.00),
('Karan Mehta', 'karan.mehta@example.com', 'Finance', 52000.00),
('Sneha Roy', 'sneha.roy@example.com', 'Marketing', 45000.00),
('Arjun Nair', 'arjun.nair@example.com', 'Engineering', 60000.00);
