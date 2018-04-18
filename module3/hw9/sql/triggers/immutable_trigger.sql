CREATE OR REPLACE FUNCTION reject_update() RETURNS trigger AS $$
	BEGIN
	    RAISE EXCEPTION 'Cannot update student_addresess table';
	END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER reject_any_update_for_student_address
    BEFORE UPDATE ON student_addresses
    EXECUTE PROCEDURE reject_update();
