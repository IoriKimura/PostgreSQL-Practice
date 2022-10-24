SET ROLE artem;
SELECT * FROM little_company.tasks WHERE executor_id = 1;
UPDATE little_company.tasks SET finaltime = now() WHERE executor_id = 1;
