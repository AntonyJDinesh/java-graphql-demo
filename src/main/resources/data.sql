DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  age INT NOT NULL
);

INSERT INTO `employee` (name, age) VALUES
('Aliko', 28),
('Bill', 29),
('Folrunsho', 30),
('Steve', 28),
('Ellon', 29),
('Mark', 30),
('Indira', 28),
('Jack', 29),
('Jeff', 30),
('Kennedy', 28),
('Gates', 29),
('Folrunsho', 32);