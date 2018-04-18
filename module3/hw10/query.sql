/*1.*/ SELECT * FROM public.students WHERE primary_skill LIKE '%,%';

/*2.*/ SELECT * FROM public.students WHERE last_name NOT SIMILAR TO '[A-z]{2,}';

/*3.*/ SELECT COUNT(results.student_id) as number_of_students, subjects.subject_name
FROM subjects JOIN results ON subjects.id = results.subject_id GROUP BY subjects.subject_name ORDER BY number_of_students DESC;

/*4.*/ SELECT COUNT(results.student_id) as number_of_students, subjects.subject_name, results.mark
FROM subjects JOIN results ON subjects.id = results.subject_id GROUP BY subjects.subject_name, results.mark ORDER BY results.mark DESC;

/*5.*/ SELECT DISTINCT students.*, COUNT(DISTINCT results.subject_id) AS passed_exam
FROM students JOIN results ON students.id = results.student_id GROUP BY students.id HAVING COUNT(results.mark) >= 2;

/*6.*/ SELECT DISTINCT students.*, COUNT(results.subject_id) AS passed_exam, subjects.subject_name
FROM students JOIN results ON students.id = results.student_id
JOIN subjects ON results.subject_id = subjects.id
GROUP BY students.id, subjects.id HAVING COUNT(results.mark) >= 2;

/*7.*/ SELECT DISTINCT students.*, subjects.subject_name AS passed_exam FROM students JOIN results ON students.id = results.student_id
JOIN subjects ON results.subject_id = subjects.id
WHERE students.primary_skill ~ subjects.subject_name;

/*8.*/ SELECT subjects.subject_name, students.id, students.first_name, students.last_name,students.primary_skill
FROM students JOIN results ON students.id = results.student_id
JOIN subjects ON results.subject_id = subjects.id
WHERE students.primary_skill !~ subjects.subject_name;

/*9.*/

/*a)*/ SELECT DISTINCT students.* FROM students FULL OUTER JOIN results ON students.id = results.student_id
WHERE results.mark IS NULL ORDER BY students.id

/*b)*/ SELECT DISTINCT students.* FROM students WHERE students.id = ANY(
SELECT DISTINCT students.id FROM results RIGHT JOIN students
ON results.student_id = students.id WHERE results.mark IS NULL)

/*c)*/ SELECT DISTINCT students.* FROM students
NOT IN (SELECT DISTINCT students.id FROM results RIGHT JOIN students ON results.student_id = students.id WHERE results.mark IS NOT NULL)

/*10.*/ SELECT students.*, AVG(results.mark) FROM students JOIN results ON students.id = results.student_id GROUP BY students.id
HAVING AVG(results.mark) > (SELECT AVG(results.mark) FROM results);

/*11.*/ SELECT students.*, AVG(results.mark) AS average_mark FROM students JOIN results ON students.id = results.student_id GROUP BY students.id
HAVING AVG(results.mark) > (SELECT AVG(results.mark) FROM results) ORDER BY average_mark DESC LIMIT 5;

/*12.*/SELECT students.*, COALESCE(results.mark, 0) AS mark,
       CASE WHEN results.mark = 1 OR results.mark = 2 OR results.mark = 3 THEN 'BAD'
            WHEN results.mark = 4 OR results.mark = 5 OR results.mark = 6 THEN 'AVERAGE'
            WHEN results.mark = 7 OR results.mark = 8 THEN 'GOOD'
            WHEN results.mark = 9 OR results.mark = 10 THEN 'EXCELLENT'
            ELSE 'NOT PASSED'
       END AS result
    FROM students FULL OUTER JOIN results ON students.id = results.student_id;

/*13.*/SELECT count(results.mark) AS number_of_marks,
       CASE WHEN results.mark = 1 OR results.mark = 2 OR results.mark = 3 THEN 'BAD'
            WHEN results.mark = 4 OR results.mark = 5 OR results.mark = 6 THEN 'AVERAGE'
            WHEN results.mark = 7 OR results.mark = 8 THEN 'GOOD'
            WHEN results.mark = 9 OR results.mark = 10 THEN 'EXCELLENT'
            ELSE 'NOT PASSED'
       END as subject_mark
    FROM students FULL OUTER JOIN results ON students.id = results.student_id
    GROUP BY subject_mark ORDER BY number_of_marks;
