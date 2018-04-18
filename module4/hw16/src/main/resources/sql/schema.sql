CREATE TABLE user
(
    id BIGINT NOT NULL,
    name VARCHAR(40) NOT NULL,
    email VARCHAR(30),
    PRIMARY KEY (id)
);

CREATE TABLE user_account
(
    user_id BIGINT NOT NULL,
    score FLOAT NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE event
(
    id BIGINT NOT NULL,
    title VARCHAR(32) NOT NULL,
    date TIME,
--  date TIME WITHOUT TIME ZONE,
    price FLOAT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE ticket
(
    id BIGINT NOT NULL,
    event_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    category VARCHAR(15),
    place INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (event_id) REFERENCES public.event (id),
    FOREIGN KEY (user_id) REFERENCES public.user (id)
);
