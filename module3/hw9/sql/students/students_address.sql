CREATE TABLE public.student_address
(
    id bigint NOT NULL,
    student_id bigint NOT NULL,
    address VARCHAR(80) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (student_id) REFERENCES public.students (id)
);
