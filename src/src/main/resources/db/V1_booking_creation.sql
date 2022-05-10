CREATE TABLE booking (
     id VARCHAR(255) NOT NULL ,
     personId VARCHAR(255) NOT NULL,
     check_in_date DATE NOT NULL,
     check_out_date DATE NOT NULL,

     PRIMARY KEY(id),
     FOREIGN KEY(personId) references person(id)
);