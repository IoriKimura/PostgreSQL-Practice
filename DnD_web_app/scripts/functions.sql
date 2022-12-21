SET SCHEMA 'public';

CREATE FUNCTION findByEmail (TEXT) 
RETURNS BOOLEAN AS 
	'SELECT count(*)>0 FROM public.users WHERE $1 = public.users.email;'
language SQL;

CREATE PROCEDURE saveNewUser (TEXT, TEXT, TEXT, TEXT) AS
	'INSERT INTO public.users(user_name, nick_name, email, password) VALUES($1, $2, $3, $4);'
language SQL;

CREATE PROCEDURE savenewcharacter (INTEGER, INTEGER, INTEGER, INTEGER, INTEGER, INTEGER, TEXT) AS
	'INSERT INTO public.characters(user_id, class_id, race_id, subrace_id,
	weapon_id, armour_id, character_name) VALUES($1, $2, $3, $4, $5, $6, $7);'
language SQL;

CREATE PROCEDURE deleteCharacter (INTEGER) AS
	'DELETE FROM public.characters WHERE character_id = $1;'
language SQL;




CREATE FUNCTION findCharactersByUserId (INTEGER)
RETURNS TABLE ("id" INTEGER, 
			   character_name TEXT,
			   class_name TEXT,
			   race_name TEXT,
			   subrace_name TEXT,
			   user_id INTEGER)
AS 
'SELECT id, 
	character_name, 
	class_name, 
	race_name, 
	subrace_name, user_id FROM charactercard WHERE user_id = $1;'
language SQL;