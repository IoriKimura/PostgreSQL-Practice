SET SCHEMA 'public';

CREATE FUNCTION findByEmail (TEXT) 
RETURNS BOOLEAN AS 
	'SELECT count(*)>0 FROM public.users WHERE $1 = public.users.email;'
language SQL;
