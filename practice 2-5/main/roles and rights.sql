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
GRANT SELECT, UPDATE ON tasks TO worker;

