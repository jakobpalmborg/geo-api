USE geographically;

INSERT INTO category (name, symbol, description) VALUES ('Train Stations', 'U+1F686', 'Train stations in Sweden');
INSERT INTO category (name, symbol, description) VALUES ('Airports', 'U+2708', 'Airports in Sweden');
INSERT INTO category (name, symbol, description) VALUES ('Bike parks', 'U+1F6B2', 'Bike parks in Sweden');

INSERT INTO user(user_name) VALUE ('John');
INSERT INTO user(user_name) VALUE ('Linda');
INSERT INTO user(user_name) VALUE ('Mike');

-- Train stations
INSERT INTO place(name, category_id, created_by, is_private, time_modified, description, coordinates, time_created)
VALUES ('Stockholm central station', 1, 1, true, timestamp(now()), 'This is the train station in Stockholm', point (59.33, 18.056) , timestamp(now()));
INSERT INTO place(name, category_id, created_by, is_private, time_modified, description, coordinates, time_created)
VALUES ('Malmö central station', 1, 3, false, timestamp(now()), 'This is the train station in Malmö', point (55.605664244, 13.000483) , timestamp(now()));
INSERT INTO place(name, category_id, created_by, is_private, time_modified, description, coordinates, time_created)
VALUES ('Göteborg central station', 1, 2, true, timestamp(now()), 'This is the train station in Göteborg', point (57.705247179, 11.970579) , timestamp(now()));

-- Airports
INSERT INTO place(name, category_id, created_by, is_private, time_modified, description, coordinates, time_created)
VALUES ('Bromma Airport', 2, 2, false, timestamp(now()), 'This is Bromma Airport', point (59.354812, 17.9427216) , timestamp(now()));

-- Bike parks
INSERT INTO place(name, category_id, created_by, is_private, time_modified, description, coordinates, time_created)
VALUES ('Åre Bikep ark', 3, 2, true, timestamp(now()), 'This is Åre Bike park', point (63.391567981179406, 13.150904292976737) , timestamp(now()));
INSERT INTO place(name, category_id, created_by, is_private, time_modified, description, coordinates, time_created)
VALUES ('Järvsö bergcykelpark', 3, 1, false, timestamp(now()), 'This is Järvsö Bergcykelpark', point (61.7122199640948, 16.159677985715447) , timestamp(now()));
INSERT INTO place(name, category_id, created_by, is_private, time_modified, description, coordinates, time_created)
VALUES ('Vallåsen bike park', 3, 3, false, timestamp(now()), 'This is Järvsö Bergcykelpark', point (56.38502778384934, 13.109019625670276) , timestamp(now()));

