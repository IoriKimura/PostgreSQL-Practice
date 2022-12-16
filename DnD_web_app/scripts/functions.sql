SET SCHEMA 'public';

CREATE FUNCTION findByEmail (TEXT) 
RETURNS BOOLEAN AS 
	'SELECT count(*)>0 FROM public.users WHERE $1 = public.users.email;'
language SQL;

CREATE PROCEDURE saveNewUser (TEXT, TEXT, TEXT, TEXT) AS
	'INSERT INTO public.users(user_name, nick_name, email, password) VALUES($1, $2, $3, $4);'
language SQL;