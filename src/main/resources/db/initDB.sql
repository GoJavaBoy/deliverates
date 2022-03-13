DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS restaurants;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE restaurants
(
    id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name    VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX restaurants_unique_name_address_idx ON restaurants (name, address);

CREATE TABLE orders
(
    id                 INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    order_number       INTEGER   NOT NULL,
    time_waited        TIME NOT NULL,
    date_time_reported TIMESTAMP NOT NULL,
    restaurant_id      INTEGER   NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX orders_unique_order_number_idx ON orders (order_number);
