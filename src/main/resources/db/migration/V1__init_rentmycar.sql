DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id           serial PRIMARY KEY,
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

DROP TABLE IF EXISTS car;
CREATE TABLE car
(
    id serial PRIMARY KEY,
    brand VARCHAR(50) NOT NULL,
    brand_type VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    license_plate_number VARCHAR(50) UNIQUE NOT NULL,
    consumption DOUBLE PRECISION NOT NULL,
    cost_price INTEGER NOT NULL,
    tco INTEGER NOT NULL,
    car_type car_type NOT NULL,
    user_id INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_car_user FOREIGN KEY(user_id)
        REFERENCES users(id)
);

DROP TABLE IF EXISTS timeslot;
CREATE TABLE timeslot
(
    id serial PRIMARY KEY,
    start_at TIME NOT NULL,
    end_at TIME NOT NULL
);

DROP TABLE IF EXISTS rental_plan;
CREATE TABLE rental_plan
(
    id              serial PRIMARY KEY,
    car_id          INTEGER   NOT NULL,
    user_id         INTEGER   NOT NULL,
    available_from  TIMESTAMP NOT NULL,
    available_until TIMESTAMP NOT NULL,
    price           DOUBLE PRECISION DEFAULT NULL,
    in_use          BOOLEAN NOT NULL,
    created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_car_rental_plan FOREIGN KEY (car_id)
        REFERENCES car(id),
    CONSTRAINT fk_car_rental_plan FOREIGN KEY (user_id)
        REFERENCES users(id)
);

DROP TABLE IF EXISTS reservation;
CREATE TABLE reservation
(
    id serial PRIMARY KEY,
    user_id INTEGER   NOT NULL,
    price DOUBLE PRECISION DEFAULT NULL,
    status reservation_status NOT NULL,
    paid_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_reservation FOREIGN KEY (user_id)
        REFERENCES users(id)
);