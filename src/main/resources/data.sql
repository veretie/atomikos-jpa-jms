DROP TABLE IF EXISTS billionaires;

CREATE TABLE names (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL
);

INSERT INTO names (first_name) VALUES
  ('Ed');
