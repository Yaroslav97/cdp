CREATE TABLE public.results
(
    id bigint NOT NULL,
    student_id bigint NOT NULL,
    subject_id bigint NOT NULL,
    mark smallint,
    PRIMARY KEY (id),
    FOREIGN KEY (student_id) REFERENCES public.students (id),
    FOREIGN KEY (subject_id) REFERENCES public.subjects (id)
);