CREATE TABLE public.students
(
    id bigint NOT NULL,
    firs_tname VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    birthday date,
    phone VARCHAR(10),
    primary_skill VARCHAR(100),
    created_datetime time without time zone,
    updated_datetime time without time zone,
    PRIMARY KEY (id)
);
