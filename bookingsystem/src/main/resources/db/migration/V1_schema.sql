
CREATE TABLE person (
                        id BIGINT  IDENTITY(1,1) PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        surname VARCHAR(255) NOT NULL,
                        serial_number VARCHAR(255) NOT NULL,
                        address VARCHAR(255) NOT NULL,
                        email VARCHAR(255) NOT NULL
);

CREATE TABLE invoice (
                         id BIGINT  IDENTITY(1,1) PRIMARY KEY,
                         is_paid BIT NOT NULL,
                         person_id BIGINT NOT NULL,
                         price BIGINT NOT NULL,
                         discount BIGINT NOT NULL,

                         FOREIGN KEY(person_id) references person(id)
);

CREATE TABLE booking(
                        id BIGINT  IDENTITY(1,1) PRIMARY KEY,
                        invoice_id BIGINT NOT NULL,
                        check_in_date DATE NOT NULL,
                        check_out_date DATE NOT NULL,

                        FOREIGN KEY(invoice_id) references invoice(id)
);

CREATE TABLE extra_service(
                              id BIGINT  IDENTITY(1,1) PRIMARY KEY,
                              service_type BIGINT NOT NULL,
                              added_cost INT NOT NULL
);

CREATE TABLE room (
                      id BIGINT  IDENTITY(1,1) PRIMARY KEY,
                      capacity VARCHAR(255) NOT NULL,
                      has_view BIT NOT NULL,
                      price INT NOT NULL,
);

CREATE TABLE BOOKING_ROOMS (
                               BOOKING_ID      BIGINT,
                               ROOM_ID        BIGINT,
                               FOREIGN KEY (BOOKING_ID)   REFERENCES booking(id),
                               FOREIGN KEY (ROOM_ID)      REFERENCES room(id)
);

CREATE TABLE BOOKING_SERVICES (
                                  BOOKING_ID      BIGINT,
                                  SERVICE_ID        BIGINT,
                                  FOREIGN KEY (BOOKING_ID)   REFERENCES booking(id),
                                  FOREIGN KEY (SERVICE_ID)      REFERENCES extra_service(id)
);

CREATE TABLE STATISTIC (
                           id BIGINT  IDENTITY(1,1) PRIMARY KEY,
                           start_date DATE NOT NULL,
                           end_date DATE NOT NULL,
                           number_of_rooms_booked BIGINT NOT NULL,
                           occupancy_rate BIGINT NOT NULL,
                           total_income BIGINT NOT NULL
);


CREATE TABLE users (
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       enabled TINYINT NOT NULL DEFAULT 1,
                       PRIMARY KEY (username)
);

CREATE TABLE authorities (
                             username VARCHAR(50) NOT NULL,
                             authority VARCHAR(50) NOT NULL,
                             FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username
  on authorities (username,authority);

INSERT INTO users (username, password, enabled)values ('guest','$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',1);
INSERT INTO authorities (username, authority)values ('guest', 'ROLE_GUEST');
INSERT INTO users (username, password, enabled)values ('customer','$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',1);
INSERT INTO authorities (username, authority)values ('customer', 'ROLE_CUSTOMER');
