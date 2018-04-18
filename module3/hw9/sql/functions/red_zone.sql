CREATE OR REPLACE FUNCTION get_red_zone() RETURNS SETOF students AS $$
        SELECT st.* FROM students st
        JOIN results r ON st.id = r.student_id
        JOIN subjects sb ON sb.id = r.subject_id
        GROUP BY st.id
        HAVING COUNT(r.mark <= 6) >= 2;
$$ LANGUAGE SQL;

SELECT * FROM get_red_zone();