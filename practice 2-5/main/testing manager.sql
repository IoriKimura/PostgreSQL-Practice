SET SCHEMA 'little_company';
SET ROLE sergey;
INSERT INTO tasks(customer_id, author_id, executor_id,
								  goal, priority, creation, deadline)
VALUES ((SELECT personal_id FROM clients WHERE personal_id = 2), 
		(SELECT employee_id FROM employee WHERE employee_id = 3),
	    (SELECT employee_id FROM employee WHERE employee_id = 1),
	    'Осуществить доставку по адресу',
	    'medium', now(), now() + interval '10' hour);
UPDATE tasks SET executor_id = 2 WHERE task_id = 4; 

INSERT INTO little_company.tasks(customer_id, author_id, executor_id,
								goal, priority, creation, deadline)
VALUES ((SELECT personal_id FROM little_company.clients WHERE personal_id = 4), 
		(SELECT employee_id FROM little_company.employee WHERE employee_id = 3),
	    (SELECT employee_id FROM little_company.employee WHERE employee_id = 2),
	    'Подготовить отправку груза на склад',
	    'medium', now(), now() + interval '10 day');
INSERT INTO little_company.contracts(contract_id, task_id, equipment_id) 
VALUES (DEFAULT, (SELECT task_id FROM little_company.tasks WHERE task_id = 5), '123MMG-HR-192-RUS');



