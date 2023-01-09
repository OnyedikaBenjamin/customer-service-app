create database customerdb;

create user 'customer_user'@'localhost' identified by 'customer_password';
grant all privileges on customerdb.* to 'customer_user'@'localhost';
flush privileges;