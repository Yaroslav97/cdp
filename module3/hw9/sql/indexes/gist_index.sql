CREATE INDEX gist_student_first_name ON students USING GIST(to_tsvector('english', first_name));
CREATE INDEX gist_student_last_name ON students USING GIST(to_tsvector('english', last_name));
CREATE INDEX gist_student_phone ON students USING GIST(to_tsvector('english', phone));
