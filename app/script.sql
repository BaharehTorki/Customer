create database item_db;
create database customer_db;
create database purchases_db;

CREATE USER 'micro_user'@'localhost' IDENTIFIED BY 'micro_password';
GRANT ALL PRIVILEGES ON customer_db.* TO 'micro_user'@'localhost';

CREATE USER 'item_user'@'localhost' IDENTIFIED BY 'item_password';
GRANT ALL PRIVILEGES ON item_db.* TO 'item_user'@'localhost';

CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'springuser';
GRANT ALL PRIVILEGES ON purchases_db.* TO 'springuser'@'localhost';


