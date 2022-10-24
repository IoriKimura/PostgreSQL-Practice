SET SCHEMA 'little_company';

CREATE TRIGGER forgotten_on_year AFTER INSERT OR UPDATE ON little_company.tasks FOR EACH STATEMENT
EXECUTE FUNCTION remove_after_year();

CREATE FUNCTION remove_after_year() RETURNS TRIGGER AS $$
	BEGIN 
		DELETE FROM little_company.tasks WHERE CURRENT_TIMESTAMP >= little_company.tasks.deadline 
		+ make_interval(years := 1);
		END;
$$ LANGUAGE plpgsql;
	