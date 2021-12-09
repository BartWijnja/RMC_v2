--
-- Table structure for table `users`
--
DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id           serial NOT NULL,
    first_name   VARCHAR(50) NOT NULL,
    last_name    VARCHAR(50) NOT NULL,
    street       VARCHAR(50) NOT NULL,
    house_number VARCHAR(50) NOT NULL,
    postal_code  VARCHAR(50) NOT NULL,
    city         VARCHAR(50) NOT NULL,
    country      VARCHAR(50) NOT NULL,
    phone_number VARCHAR(50) NOT NULL,
    iban         VARCHAR(50) UNIQUE NOT NULL,
    email        VARCHAR(50) UNIQUE NOT NULL,
    user_role    user_role   NOT NULL,
    created_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- -------------------------------------------------------------------------

--
-- Table structure for table `cars`
--
DROP TABLE IF EXISTS cars;
CREATE TABLE cars
(
    id serial NOT NULL,
    brand VARCHAR(50) NOT NULL,
    brand_type VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    license_plate_number VARCHAR(50) UNIQUE NOT NULL,
    consumption DOUBLE PRECISION NOT NULL,
    cost_price INTEGER NOT NULL,
    tco INTEGER NOT NULL,
    car_type car_type NOT NULL,
    user_id INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- -------------------------------------------------------------------------

--
-- Table structure for table `timeslots`
--

DROP TABLE IF EXISTS timeslots;
CREATE TABLE timeslots
(
    id serial NOT NULL,
    start_at TIME NOT NULL,
    end_at TIME NOT NULL
);
-- -------------------------------------------------------------------------

--
-- Table structure for table `rental_plans`
--
DROP TABLE IF EXISTS rental_plans;
CREATE TABLE rental_plans
(
    id              serial    NOT NULL,
    car_id          INTEGER   NOT NULL,
    user_id         INTEGER   NOT NULL,
    available_from  TIMESTAMP NOT NULL,
    available_until TIMESTAMP NOT NULL,
    price           DOUBLE PRECISION DEFAULT NULL,
    in_use          BOOLEAN NOT NULL,
    created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- -------------------------------------------------------------------------

--
-- Table structure for table `reservations`
--
DROP TABLE IF EXISTS reservations;
CREATE TABLE reservations
(
    id serial NOT NULL,
    user_id INTEGER   NOT NULL,
    price DOUBLE PRECISION DEFAULT NULL,
    status reservation_status NOT NULL,
    paid_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- -------------------------------------------------------------------------

--
-- Alterations for Primary Keys and Foreign Keys
--

ALTER TABLE users
    ADD PRIMARY KEY (id);

ALTER TABLE cars
    ADD PRIMARY KEY (id),
    ADD CONSTRAINT fk_cars_users FOREIGN KEY(user_id)
        REFERENCES users(id);

ALTER TABLE timeslots
    ADD PRIMARY KEY (id);

ALTER TABLE rental_plans
    ADD PRIMARY KEY (id),
    ADD CONSTRAINT fk_cars_rental_plans FOREIGN KEY (car_id)
        REFERENCES cars(id),
    ADD CONSTRAINT fk_cars_rental_plans FOREIGN KEY (user_id)
        REFERENCES users(id);

ALTER TABLE reservations
    ADD PRIMARY KEY (id),
    ADD CONSTRAINT fk_users_reservations FOREIGN KEY (user_id)
        REFERENCES users(id);
