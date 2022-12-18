SET SCHEMA 'public';

CREATE OR REPLACE VIEW characterCard
AS 
SELECT 
	characters.character_id as "id", 
	characters.character_name as "character_name", 
	classes.class_name as class_name,
	races.race_name as race_name,
	subraces.subrace_name as subrace_name
FROM characters inner join classes on characters.class_id = classes.class_id
inner join races on characters.race_id = races.race_id
inner join subraces on characters.subrace_id = subraces.subrace_id;
WHERE character_id = $1
	AND character_name = (SELECT character_name FROM characters WHERE character_id = $1)
	AND class_name = (SELECT class_name FROM classes WHERE class_id = $2)
	AND race_name = (SELECT race_name FROM races WHERE race_id = $3)
	AND subrace_name = (SELECT subrace_name FROM subraces WHERE subrace_id = $4);
	
	
charactercard()
