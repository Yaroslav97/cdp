CREATE OR REPLACE FUNCTION average_mark_for_student(student text) RETURNS numeric AS $$
	SELECT AVG(results.mark)
	FROM students INNER JOIN results ON students.id = results.student_id
	WHERE students.last_name LIKE $1;
$$ LANGUAGE SQL;

DROP FUNCTION average_mark_for_student();

SELECT * FROM average_mark_for_student('last_name2342');
