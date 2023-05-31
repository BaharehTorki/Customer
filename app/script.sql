create database item_db;
create database customer_db;
create database purchases_db;

CREATE USER 'micro_user'@'%' IDENTIFIED BY 'micro_password';
GRANT ALL PRIVILEGES ON customer_db.* to 'micro_user'@'%';

CREATE USER 'item_user'@'%' IDENTIFIED BY 'item_password';
GRANT ALL PRIVILEGES ON item_db.* TO 'item_user'@'%';

CREATE USER 'springuser'@'%' IDENTIFIED BY 'springuser';
GRANT ALL PRIVILEGES ON purchases_db.* TO 'springuser'@'%';


