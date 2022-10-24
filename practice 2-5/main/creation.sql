CREATE SCHEMA IF NOT EXISTS little_company;

SET SCHEMA 'little_company';

CREATE TYPE little_company.client as ENUM ('curent', 'potential');
CREATE TYPE little_company.employee_role as ENUM('worker', 'manager');

CREATE TABLE little_company.clients
(
	personal_id SERIAl PRIMARY KEY UNIQUE NOT NULL,
	"name" TEXT NOT NULL,
	phone VARCHAR(12) NOT NULL,
	email VARCHAR(30) NOT NULL,
	post_address TEXT NOT NULL,
	client_type client NOT NULL
);

CREATE TABLE little_company.employee
(
	employee_id SERIAl PRIMARY KEY UNIQUE NOT NULL,
	"name" TEXT NOT NULL,
	phone VARCHAR(12) NOT NULL,
	email VARCHAR(30) NOT NULL,
	"position" employee_role NOT NULL
	nickname TEXT UNIQUE NOT NULL
);

CREATE TABLE little_company.tasks
(
	task_id SERIAL PRIMARY KEY UNIQUE NOT NULL,
	customer_id SERIAL REFERENCES clients (personal_id),
	author_id SERIAL REFERENCES employee (employee_id),
	executor_id SERIAL REFERENCES employee (employee_id),
	goal TEXT NOT NULL,
	priority VARCHAR(6) NOT NULL,
	creation TIMESTAMP WITHOUT TIME ZONE NOT NULL, 
	deadline TIMESTAMP WITHOUT TIME ZONE NOT NULL,
	finaltime  TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE little_company.contracts
(
	contract_id SERIAL PRIMARY KEY UNIQUE NOT NULL,
	task_id SERIAL REFERENCES tasks (task_id),
	equipment_id TEXT UNIQUE NOT NULL
);



