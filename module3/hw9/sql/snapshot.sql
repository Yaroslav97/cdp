COPY (SELECT students.first_name, students.last_name, subjects.subject_name, results.mark
FROM students JOIN result ON students.id = results.student_id
JOIN subjects ON results.subject_id = subjects.id)
TO 'D:/snapshot.tsv'