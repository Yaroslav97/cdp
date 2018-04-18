CREATE OR REPLACE FUNCTION update_students_timestamp() RETURNS TRIGGER AS $$
BEGIN
	NEW.updated_datetime  := current_timestamp; /*now()*/
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_students_timestamp
BEFORE UPDATE ON students
FOR EACH ROW EXECUTE PROCEDURE update_students_timestamp();

DROP FUNCTION public.update_students_timestamp();
DROP TRIGGER IF EXISTS update_students_timestamp ON students;

UPDATE public.students SET first_name = 'Name' WHERE id = 32425;
SELECT * FROM public.students WHERE id = 32425;