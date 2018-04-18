/*B-Tree*/
SELECT * FROM public.students WHERE first_name = 'fist_name65344'; /*83 msec*/
SELECT * FROM public.students WHERE last_name LIKE '%name65344'; /*91 msec*/
SELECT * FROM public.students WHERE phone LIKE '%09965344'; /*84 msec*/
SELECT students.last_name, results.mark FROM students JOIN results ON students.id = results.student_id WHERE students.last_name LIKE '%63478'; /*170 msec*/

/*GIST*/
SELECT * FROM public.students WHERE first_name = 'fist_name65344'; /*86 msec*/
SELECT * FROM public.students WHERE last_name LIKE '%name65344'; /*101 msec*/
SELECT * FROM public.students WHERE phone LIKE '%09965344'; /*85 msec*/
SELECT students.last_name, results.mark FROM students JOIN results ON students.id = results.student_id WHERE students.last_name LIKE '%63478'; /*176 msec*/

/*GIN*/
SELECT * FROM public.students WHERE first_name = 'fist_name75344'; /*86 msec*/
SELECT * FROM public.students WHERE last_name LIKE '%name4344'; /*86 msec*/
SELECT * FROM public.students WHERE phone LIKE '%0995344'; /*67 msec*/
SELECT students.last_name, results.mark FROM students JOIN results ON students.id = results.student_id WHERE students.last_name LIKE '%63478'; /*158 msec*/

/*HASH*/
SELECT * FROM public.students WHERE first_name = 'fist_name9999'; /*86 msec*/
SELECT * FROM public.students WHERE last_name LIKE '%name66666'; /*84 msec*/
SELECT * FROM public.students WHERE phone LIKE '%09999999'; /*86 msec*/
SELECT students.last_name, results.mark FROM students JOIN results ON students.id = results.student_id WHERE students.last_name LIKE '%99996' /*162 msec*/