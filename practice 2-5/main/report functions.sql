SET SCHEMA 'little_company';

--Функция для получения количества задач работника
CREATE FUNCTION get_all_tasks(employee_id INTEGER, start_date TIMESTAMP WITHOUT TIME ZONE,
							 end_date TIMESTAMP WITHOUT TIME ZONE)
RETURNS INTEGER AS 
$$ SELECT count(*) FROM little_company.tasks
					WHERE ((employee_id = little_company.tasks.executor_id)
						  AND (start_date <= little_company.tasks.creation)
						  AND (end_date >= little_company.tasks.creation));
$$ language SQL;

--Функция для получения количества выполненных в срок заданий работника
CREATE FUNCTION get_complete_tasks_in_time(employee_id INTEGER, start_date TIMESTAMP WITHOUT TIME ZONE,
										  end_date TIMESTAMP WITHOUT TIME ZONE)
RETURNS INTEGER AS
$$ SELECT count(*) FROM little_company.tasks 
					WHERE ((employee_id = little_company.tasks.executor_id) 
						  AND (start_date <= little_company.tasks.creation)
						  AND (end_date >= little_company.tasks.creation)
						  AND (start_date <= little_company.tasks.finaltime)
						  AND (end_date >= little_company.tasks.finaltime)
						  AND (little_company.tasks.finaltime <= little_company.tasks.deadline));
$$ language SQL;

--Функция для получения количества выполненных заданий работник не в срок
CREATE FUNCTION get_complete_tasks_not_in_time(employee_id INTEGER, start_date TIMESTAMP WITHOUT TIME ZONE,
											  end_date TIMESTAMP WITHOUT TIME ZONE)
RETURNS INTEGER AS 
$$ SELECT count(*) FROM little_company.tasks
					WHERE ((employee_id = little_company.tasks.executor_id)
						  AND (start_date <= little_company.tasks.creation)
						  AND (end_date >= little_company.tasks.creation)
						  AND (start_date <= little_company.tasks.finaltime)
						  AND (end_date <= little_company.tasks.finaltime)
						  AND (little_company.tasks.finaltime > little_company.tasks.deadline));
$$ language SQL;

--Функция для получения количества невыполненных задний работника с истёкшим сроком
CREATE FUNCTION get_uncomplete_tasks(employee_id INTEGER, start_date TIMESTAMP WITHOUT TIME ZONE,
									end_date TIMESTAMP WITHOUT TIME ZONE)
RETURNS INTEGER AS 
$$ SELECT count(*) FROM little_company.tasks
					WHERE ((employee_id = little_company.tasks.executor_id)
						  AND (start_date <= little_company.tasks.creation)
						  AND (end_date >= little_company.tasks.creation)
						  AND (little_company.tasks.finaltime = NULL)
						  AND (now() > little_company.tasks.deadline));
$$ language SQL;

--Функция для получения количества заданий работника в процессе выполнения
CREATE FUNCTION get_in_progress_tasks(employee_id INTEGER, start_date TIMESTAMP WITHOUT TIME ZONE,
									 end_date TIMESTAMP WITHOUT TIME ZONE)
RETURNS INTEGER AS
$$ SELECT count(*) FROM little_company.tasks
					WHERE ((employee_id = little_company.tasks.executor_id)
						  AND (start_date <= little_company.tasks.creation)
						  AND (end_date >= little_company.tasks.creation)
						  AND (little_company.tasks.finaltime = NULL)
						  AND (now() < little_company.tasks.deadline));
$$ language SQL;


