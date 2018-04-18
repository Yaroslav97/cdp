CREATE OR REPLACE FUNCTION validate_student_name() RETURNS TRIGGER AS $$
BEGIN
	IF NEW.first_name like '%@%' OR NEW.first_name like '%#%' OR NEW.first_name like '%$%' THEN
		RAISE EXCEPTION 'Name contains illegal symbols';
	ELSE
		RETURN NEW;
	END IF;

END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER validate_student_name
BEFORE INSERT ON students
FOR EACH ROW EXECUTE PROCEDURE validate_student_name();

DROP FUNCTION validate_student_name();
DROP TRIGGER IF EXISTS validate_student_name ON students;
