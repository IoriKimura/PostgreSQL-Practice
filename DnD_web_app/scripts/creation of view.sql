SET SCHEMA 'public';

CREATE TABLE public.users
(
	user_id SERIAL PRIMARY KEY UNIQUE NOT NULL,
	user_name TEXT NOT NULL,
	nick_name TEXT NOT NULL,
	email VARCHAR(30) NOT NULL,
	"password" TEXT NOT NULL
);

CREATE TABLE public.classes
(
	class_id SERIAL PRIMARY KEY UNIQUE NOT NULL,
	class_name INTEGER NOT NULL,
	class_hp INTEGER NOT NULL,
	class_attribute TEXT NOT NULL
);

CREATE TABLE public.races
(
	race_id SERIAL PRIMARY KEY UNIQUE NOT NULL,
	race_name INTEGER NOT NULL,
	race_description INTEGER NOT NULL,
	race_attribute TEXT NOT NULL,
	race_hp INTEGER NOT NULL,
	race_mp INTEGER NOT NULL
);

CREATE TABLE public.subraces
(
	subrace_id SERIAL PRIMARY KEY UNIQUE NOT NULL,
	race_id REFERENCES races (race_id),
	subrace_name TEXT NOT NULL,
	subrace_attribute TEXT NOT NULL
);

CREATE TABLE public.weapons
(
	weapon_id SERIAL PRIMARY KEY UNIQUE NOT NULL,
	weapon_name TEXT NOT NULL,
	weapon_dmg INTEGER NOT NULL,
	weapon_mana_cost INTEGER NOT NULL,
	weapon_price NUMERIC NOT NULL,
	weapon_class_id REFERENCES classes (class_id)
);

CREATE TABLE public.armours
(
	armour_id SERIAL PRIMARY KEY UNIQUE NOT NULL,
	armour_name TEXT NOT NULL,
	armour_points INTEGER NOT NULL,
	armour_price NUMERIC NOT NULL,
	armour_class_id REFERENCES classes (class_id)
);

CREATE TABLE public.characters
(
	character_id SERIAL PRIMARY KEY UNIQUE NOT NULL,
	user_id REFERENCES users (user_id),
	class_id REFERENCES classes (class_id),
	race_id REFERENCES races (race_id),
	subrace_id REFERENCES subraces (subrace_id),
	weapon_id REFERENCES weapons (weapon_id),
	armour_id REFERENCES armours (armour_id),
	character_name TEXT NOT NULL
);

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

	
