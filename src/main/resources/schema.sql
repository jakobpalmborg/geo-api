-- DROP DATABASE geographically;

CREATE DATABASE geographically;

USE geographically;

CREATE TABLE category (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(255),
    symbol varchar(255),
    description varchar(255)
);

CREATE TABLE user (
    id int PRIMARY KEY AUTO_INCREMENT,
    user_name varchar(255)
);

DROP TABLE place;

CREATE TABLE place (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    category_id int,
    FOREIGN KEY (category_id) REFERENCES category(id),
    created_by int,
    FOREIGN KEY (created_by) REFERENCES user(id),
    private boolean DEFAULT false,
    time_modified timestamp,
    description varchar(255),
    coordinates point,
    time_created timestamp
);

