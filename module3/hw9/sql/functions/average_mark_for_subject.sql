DROP FUNCTION IF EXISTS average_mark_for_subject();
CREATE OR REPLACE FUNCTION average_mark_for_subject(subject text) RETURNS numeric AS $$
	SELECT AVG(results.mark)
	FROM subjects
	INNER JOIN results ON subjects.id = results.subject_id
	WHERE subjects.subject_name LIKE $1
$$ LANGUAGE SQL;

SELECT * FROM average_mark_for_subject('subject6');
