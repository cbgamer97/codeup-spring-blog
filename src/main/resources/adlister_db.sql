CREATE DATABASE IF NOT EXISTS adlister_db;

CREATE USER root IDENTIFIED BY 'codeup';
GRANT ALL ON adlister_db.* TO root;
