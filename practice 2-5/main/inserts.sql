SET SCHEMA 'little_company';

INSERT INTO little_company.clients(personal_id, "name", phone, email, post_address, client_type) VALUES
	(DEFAULT, 'МФО Алмаз', 88003905024, 'almazcom@gmail.com','Свердловская область, город Ступино, шоссе Ленина, 57', 'curent'::client),
	(DEFAULT, 'ОАО ГазТяжМяс', 88008263524, 'gaztyajmyas@gmail.com', 'Брянская область, город Ногинск, проезд Балканская, 27', 'potential'::client),
	(DEFAULT, 'МКК Реч', 88005894549, 'speachcompanyrus@gmail.com', 'Томская область, город Шатура, спуск Гоголя, 37', 'curent'::client);
	
INSERT INTO little_company.employee(employee_id, "name", phone, email, "position") VALUES
	(DEFAULT, 'Артемий', 88121523603, 'rfunk@jast.com', 'worker'::employee_role),
	(DEFAULT, 'Дмитрий', 84952893980, 'sreynolds@rohan.com', 'worker'::employee_role),
	(DEFAULT, 'Сергей', 79221820791, 'wisoky.anabelle@yahoo.com','manager'::employee_role);