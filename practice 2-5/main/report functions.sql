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
						  AND (little_company.tasks.finaltime is NULL)
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
						  AND (little_company.tasks.finaltime is NULL)
						  AND (now() < little_company.tasks.deadline));
$$ language SQL;


CREATE FUNCTION get_report(employee_id INTEGER, start_date TIMESTAMP WITHOUT TIME ZONE,
						  end_date TIMESTAMP WITHOUT TIME ZONE)
RETURNS TABLE (employee_id INTEGER,
			  count_of_tasks INTEGER,
			  count_of_tasks_in_time INETGER,
			  count_of_tasks_not_in_time INTEGER,
			  count_of_uncomplete_tasks INTEGER,
			  count_of_in_progress_tasks INTEGER) AS 
$$ SELECT employee_id, 
			get_all_tasks(employee_id, start_date, end_date),
			get_complete_tasks_in_time(employee_id, start_date, end_date),
			get_complete_tasks_not_in_time(employee_id, start_date, end_date),
			get_uncomplete_tasks(employee_id, start_date, end_date),
			get_in_progress_tasks(employee_id, start_date, end_date);
$$ language SQL;
RESET ROLE;
CREATE TEMP TABLE report ON COMMIT DROP 
	AS SELECT  * FROM get_report(1, '2022-10-24'::timestamp without time zone, 
								 '2022-10-30'::timestamp without time zone);
COPY (SELECT * FROM report) TO 'C:\Users\Public\Documents\report.csv' CSV HEADER;

--Выгрузка отчёта в json формат
-- 1. Нужно преобразовать строки в json объекты с помощью команды row_to_json()
 COPY (SELECT row_to_json(report) FROM get_report(1, '2022-10-24'::timestamp without time zone, 
								 '2022-11-30'::timestamp without time zone) as report) 
TO 'C:\Users\Public\Documents\report.json';-- WITH (FORMAT text, HEADER false) применяется дефолтно
