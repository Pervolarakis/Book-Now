CREATE TYPE roleEnum AS ENUM ('USER', 'ADMIN');

ALTER TABLE user_account
ADD role roleEnum;