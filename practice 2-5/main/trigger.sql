SET SCHEMA 'little_company';
RESET ROLE;
CREATE TRIGGER forgotten_on_year BEFORE INSERT OR UPDATE ON tasks FOR EACH ROW EXECUTE PROCEDURE
remove_after_year();

CREATE FUNCTION remove_after_year() RETURNS TRIGGER AS $$
	BEGIN 
		DELETE FROM tasks WHERE CURRENT_TIMESTAMP >= (tasks.deadline 
		+ make_interval(years => 1));
		END;
$$ LANGUAGE plpgsql;
	