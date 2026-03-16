CREATE DATABASE IF NOT EXISTS mybatis_test;
USE mybatis_test;

-- DROP TABLE user;

CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50),
    password VARCHAR(50)
);

INSERT INTO user (username, password) VALUES ('harris', '123456'), ('unsw_student', '654321'), ('Suyu', '1234567');

-- SELECT * FROM user;

-- 