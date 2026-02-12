ALTER TABLE users_profile RENAME TO user_profile;

ALTER TABLE user_profile ALTER COLUMN user_id DROP DEFAULT;
ALTER TABLE user_profile ALTER COLUMN user_id TYPE BIGINT;
DROP SEQUENCE IF EXISTS users_profile_user_id_seq;

ALTER TABLE user_profile
    ADD CONSTRAINT fk_user_profile_user
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;

ALTER TABLE user_profile RENAME COLUMN createdAt TO created_at;