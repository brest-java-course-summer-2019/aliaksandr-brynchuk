DROP TABLE IF EXISTS films;
CREATE TABLE films
(
  id     INT            NOT NULL AUTO_INCREMENT UNIQUE,
  name   VARCHAR(255)   NOT NULL,
  genre  VARCHAR(255)   NOT NULL,
  director VARCHAR(255) NOT NULL
);



