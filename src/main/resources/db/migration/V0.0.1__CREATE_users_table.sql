CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL,
    age        INT,
    gender     VARCHAR(50),
    interests  TEXT,
    city       VARCHAR(100)
);
