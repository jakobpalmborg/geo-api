USE geographically;



INSERT INTO category (name, symbol, description) VALUES ('category 1', 'U+1F6B5', 'This the description of category one');
INSERT INTO category (name, symbol, description) VALUES ('category 2', 'U+1F6F4', 'This the description of category two');
INSERT INTO category (name, symbol, description) VALUES ('category 3', 'U+1F680', 'This the description of category three');

INSERT INTO user(user_name) VALUE ('user1');
INSERT INTO user(user_name) VALUE ('user2');
INSERT INTO user(user_name) VALUE ('user3');


INSERT INTO place(name, category_id, created_by, private, time_modified, description, coordinates, time_created)
VALUES ('Stockholm central station', 1, 1, true, timestamp(now()), 'This is the train station in Stockholm', point (59.33, 18.056) , timestamp(now()));
INSERT INTO place(name, category_id, created_by, private, time_modified, description, coordinates, time_created)
VALUES ('Malmö central station', 2, 3, false, timestamp(now()), 'This is the train station in Malmö', point (55.605664244, 13.000483) , timestamp(now()));
INSERT INTO place(name, category_id, created_by, private, time_modified, description, coordinates, time_created)
VALUES ('Göteborg central station', 3, 2, true, timestamp(now()), 'This is the train station in Göteborg', point (57.705247179, 11.970579) , timestamp(now()));
INSERT INTO place(name, category_id, created_by, private, time_modified, description, coordinates, time_created)
VALUES ('Bromma Airport', 1, 2, false, timestamp(now()), 'This is Bromma Airport', point (59.354812, 17.9427216) , timestamp(now()));
