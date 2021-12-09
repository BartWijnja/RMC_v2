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
    created_at   TIMESTAMP   NOT NULL
);

DROP TABLE IF EXISTS cars;
CREATE TABLE cars
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
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_car_user FOREIGN KEY(user_id)
        REFERENCES users(id)
);