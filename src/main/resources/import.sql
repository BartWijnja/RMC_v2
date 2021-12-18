--
-- Dumping data for table `users`
--
INSERT INTO users (first_name, last_name, street, house_number, postal_code, city, country, phone_number, iban, email, user_role, created_at)
VALUES ('John','Doe','Veemarktstraat','12','4811ZJ','Breda','Nederland','+31618588947','NL12INGB047583365','john.doe@gmail.com','OWNER',current_timestamp),
       ('Bart','Grootoonk','Stationsstraat','47','5867HH','Tilburg','Nederland','+31676893445','NL23ABNA083465899','bart.grootoonk@email.com','OWNER',CURRENT_TIMESTAMP),
       ('Johan','de Visser','Zuidermarkt','7C','2298HK','Groningen','Nederland','+31623354789','NL12INGB014365578','johandevisser@live.nl', 'OWNER', CURRENT_TIMESTAMP),
       ('Berend','Wolfstra','Beverweg','156','8766UO','Breda','Nederland','+31615477998','NL63RABO043667890','berendwolfstra@yahoo.com','OWNER', CURRENT_TIMESTAMP),
       ('Kees','van Dongen','Parelsebaan','2','2857TY','Eindhoven','Nederland','+31665877034','NL12INGB031224567','kees.vandongen@hetnet.nl','OWNER', CURRENT_TIMESTAMP),
       ('Merel','van Laren','Poolseweg','48D','6755JQ','Breda','Nederland','+31664855136','NL12INGB078695722','merelvanlaren@gmail.com','OWNER',CURRENT_TIMESTAMP),
       ('Sofia','Bulgara','Torenstraat','18','4758UY','Amsterdam','Nederland','+31618675075','NL63RABO027586957','sofiabulgara@hotmail.com','OWNER',CURRENT_TIMESTAMP);
-- -------------------------------------------------------------------------

--
-- Dumping data for table `cars`
--
INSERT INTO cars (brand, brand_type, model, license_plate_number, consumption, cost_price, tco,  created_at, car_type, user_id)
VALUES ('Honda', 'H-RV', 'EX-L', '465-HK-3', 5.7, 33450, 0, CURRENT_TIMESTAMP, 'ICE', 1),
       ('Volkswagen', 'Polo', '1.6 TDI', '4-YY-685', 4.45, 25960, 0, CURRENT_TIMESTAMP, 'ICE', 2),
       ('Hyundai', 'ix35', 'FCEV', '739-PD-2', 0.59, 27050, 0, CURRENT_TIMESTAMP, 'FCEV', 3),
       ('Peugeot', '208', 'e', '3-UP-869', 16.4, 28650, 0, CURRENT_TIMESTAMP, 'BEV', 4),
       ('Tesla', 'Model', '3', '890-AH-1', 5.7, 33450, 0, CURRENT_TIMESTAMP, 'BEV', 5);
-- -------------------------------------------------------------------------

--
-- Dumping data for table `timeslots`
--

INSERT INTO timeslots (start_at, end_at)
VALUES ('07:00:00','11:30:00'),
       ('12:00:00','16:30:00'),
       ('17:00:00','21:30:00');

-- -------------------------------------------------------------------------

--
-- Dumping data for table `rental_plans`
--
INSERT INTO rental_plans (car_id, user_id, available_from, available_until, price, in_use, created_at)
VALUES (1, 1, '2021-11-01', '2021-11-03', 80.00, true, CURRENT_TIMESTAMP),
       (2, 2, '2021-12-01', '2021-12-03', 60.00, true, CURRENT_TIMESTAMP),
       (3, 3, '2021-11-01', '2021-11-03', 90.00, true, CURRENT_TIMESTAMP),
       (4, 4, '2021-11-01', '2021-12-01', 60.00, true, CURRENT_TIMESTAMP);

-- -------------------------------------------------------------------------

--
-- Dumping data for table `reservations`
--

INSERT INTO reservations (user_id, price, status, paid_at, created_at)
VALUES (1, 1129.99, 'COMPLETED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 409.99, 'COMPLETED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 789.99, 'COMPLETED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 839.99, 'COMPLETED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (5, 434.99, 'COMPLETED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (6, 1659.99, 'COMPLETED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- -------------------------------------------------------------------------