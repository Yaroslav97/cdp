CREATE INDEX hash_student_first_name ON students USING hash(first_name);
CREATE INDEX hash_student_last_name ON students USING hash(last_name);
CREATE INDEX hash_student_phone ON students USING hash(phone);
