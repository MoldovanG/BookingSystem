
CREATE TABLE person (
                        id BIGINT  IDENTITY(1,1) PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        surname VARCHAR(255) NOT NULL,
                        serial_number VARCHAR(255) NOT NULL,
                        address VARCHAR(255) NOT NULL,
                        email VARCHAR(255) NOT NULL
);

CREATE TABLE booking(
                        id BIGINT  IDENTITY(1,1) PRIMARY KEY,
                        person_id BIGINT NOT NULL,
                        check_in_date DATE NOT NULL,
                        check_out_date DATE NOT NULL,

                        FOREIGN KEY(person_id) references person(id)
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