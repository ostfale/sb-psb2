DROP TABLE IF EXISTS Customer;
CREATE TABLE Customer
(
    id         IDENTITY,
    first_name VARCHAR(200),
    dob        DATE
);