ALTER TABLE users DROP CONSTRAINT IF EXISTS users_role_check;

ALTER TABLE users ALTER COLUMN role DROP DEFAULT;

ALTER TABLE users ADD COLUMN role_new VARCHAR(50);

UPDATE users
SET role_new = CASE role
                   WHEN 0 THEN 'USER_ROLE'
                   WHEN 1 THEN 'ADMIN_ROLE'
                   ELSE NULL
    END;

ALTER TABLE users DROP COLUMN role;
ALTER TABLE users RENAME COLUMN role_new TO role;

ALTER TABLE users ALTER COLUMN role SET NOT NULL;

ALTER TABLE users ADD CONSTRAINT users_role_check
    CHECK (role IN ('USER_ROLE', 'ADMIN_ROLE'));