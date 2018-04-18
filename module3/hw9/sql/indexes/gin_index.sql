CREATE INDEX gin_student_first_name ON students USING GIN(to_tsvector('english', first_name));
CREATE INDEX gin_student_last_name ON students USING GIN(to_tsvector('english', last_name));
CREATE INDEX gin_student_phone ON students USING GIN(to_tsvector('english', phone));
