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
GRANT SELECT, UPDATE ON tasks TO worker;

ALTER TABLE tasks ENABLE ROW LEVEL SECURITY;

CREATE POLICY worker_select ON tasks FOR SELECT TO worker USING
(
	(SELECT employee_id FROM employee WHERE 'position' = 'worker'
	AND
	(SELECT rolname FROM pg_roles WHERE pg_has_role(current_user, 'mamber') AND rolname = 'worker') = 'worker'
	) = tasks.executor_id
);

CREATE POLICY worker_update ON tasks FOR UPDATE TO worker USING
(
	(SELECT employee_id FROM employee WHERE 'position' = 'worker'
	AND
	(SELECT rolname FROM pg_roles WHERE pg_has_role(current_user, 'mamber') AND rolname = 'worker') = 'worker'
	) = tasks.executor_id
);

CREATE POLICY manager_update ON tasks FOR UPDATE TO  manager USING
(
	(SELECT employee_id FROM employee WHERE 'position' = 'manager'
	AND
	(SELECT rolname FROM pg_roles WHERE pg_has_role(current_user, 'mamber') AND rolname = 'manager') = 'manager'
	) = tasks.author_id 
	OR 
	(SELECT employee_id FROM employee WHERE 'position' = 'manager'
	AND
	(SELECT rolname FROM pg_roles WHERE pg_has_role(current_user, 'mamber') AND rolname = 'manager') = 'manager'
	) = tasks.executor_id
);



--SELECT rolname FROM pg_roles WHERE pg_has_role(user, 'member') and rolname = 'manager';
