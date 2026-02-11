CREATE TABLE users_profile
(
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    user_id    BIGSERIAL NOT NULL UNIQUE,
    createdAt TIMESTAMP NOT NULL
)