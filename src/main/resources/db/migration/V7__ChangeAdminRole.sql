ALTER TYPE roleEnum RENAME VALUE 'ADMIN' TO 'ROLE_ADMIN';
UPDATE user_account set role='ROLE_ADMIN' where email='admin@mail.com'