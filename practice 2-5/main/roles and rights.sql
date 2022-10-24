SET SCHEMA 'little_company';

--Создание груповых ролей, которым будут выдавать привилегии в соответствии с ТЗ, 
--а в них уже добавляться наши рядовые работники и менеджеры.
CREATE ROLE worker;
CREATE ROLE manager;

--Создание пользователей работников компании
CREATE ROLE Artem LOGIN PASSWORD 'little_artemius';
CREATE ROLE Dmitry LOGIN PASSWORD 'little_dmitry';
CREATE ROLE Sergey LOGIN PASSWORD 'little_sergey';

GRANT worker TO Artem, Dmitry;
GRANT manager TO Sergey;

GRANT SELECT, INSERT, UPDATE ON tasks TO manager;
GRANT SELECT, INSERT, UPDATE ON contracts to manager;
GRANT SELECT ON clients, employee TO manager;

GRANT SELECT ON employee TO worker;
GRANT SELECT, UPDATE(finaltime) ON tasks TO worker;
RESET ROLE;
GRANT USAGE ON SCHEMA little_company to manager;
GRANT USAGE ON SCHEMA little_company to worker;

ALTER TABLE tasks ENABLE ROW LEVEL SECURITY;

CREATE POLICY worker_select ON tasks FOR SELECT TO worker USING
(
	(SELECT employee_id FROM employee WHERE "position" = 'worker'::employee_role
	AND  nickname = user) = tasks.executor_id
);

CREATE POLICY worker_update ON tasks FOR UPDATE TO worker USING
(
	(SELECT employee_id FROM employee WHERE "position" = 'worker'::employee_role
	AND nickname = user) = tasks.executor_id
	AND 
	(finaltime = Null)
);

CREATE POLICY manager_update ON tasks FOR UPDATE TO manager USING
(
	(SELECT employee_id FROM employee WHERE "position" = 'manager'::employee_role
	AND nickname = user) = tasks.author_id 
	OR 
	(SELECT employee_id FROM employee WHERE "position" = 'manager'::employee_role
	AND nickname = user) = tasks.executor_id
);

CREATE POLICY manager_insert_tasks ON tasks FOR INSERT TO manager with check (True);
CREATE POLICY manager_select_tasks ON tasks FOR SELECT TO manager USING (True);

