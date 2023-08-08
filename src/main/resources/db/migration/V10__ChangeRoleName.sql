ALTER TYPE roleEnum RENAME VALUE 'ROLE_ADMIN' TO 'SCOPE_ROLE_ADMIN';
UPDATE user_account set role='SCOPE_ROLE_ADMIN' where email='admin@mail.com'