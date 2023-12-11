create database tourism;

use tourism;

create table tours(
	id INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255),
    `description` VARCHAR(255),
    price DECIMAL(10,2),
    PRIMARY KEY (id)
);


CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  user_role ENUM('CUSTOMER', 'ADMIN', 'OPERATOR') NOT NULL,
  PRIMARY KEY (id)
  );

Create table orders(
	  id INT NOT NULL AUTO_INCREMENT,
      user_id INT,
      tour_id INT,
      order_status enum('OPENED', 'PREPARING','DONE','CLOSED'),
      price decimal(10,2),
      primary key(id),
      foreign key(user_id) references users(id),
      foreign key(tour_id) references tours(id)
);




INSERT INTO users (first_name, last_name, email, `password`, user_role)
VALUES ('Admin', 'Admin', 'admin@mail.com', 'admin', 'ADMIN');

INSERT INTO users (first_name, last_name, email, `password`, user_role)
VALUES ('operator_anya', 'anya', 'op@mail.com', 'op', 'OPERATOR');