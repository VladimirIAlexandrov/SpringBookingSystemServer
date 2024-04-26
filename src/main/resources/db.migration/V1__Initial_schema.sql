CREATE TABLE IF NOT EXISTS clients (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    roles TEXT[] NOT NULL
);

CREATE TABLE IF NOT EXISTS events (
    id BIGSERIAL PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL,
    description TEXT,
    date_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    location VARCHAR(255) NOT NULL,
    image_path TEXT
);

CREATE TABLE IF NOT EXISTS bookings (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES clients(id),
    event_id BIGINT REFERENCES events(id),
    booking_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    booking_time_start TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    booking_time_end TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    status VARCHAR(50) NOT NULL
);