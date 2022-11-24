CREATE TABLE shipping(
    shipping_id INTEGER NOT NULL,
    shipping_order_id INTEGER,
    shipping_created TIMESTAMP,
    shipping_shipped boolean
);
create sequence shipping_id_sequence;

CREATE TABLE orders(
    orders_id INTEGER NOT NULL,
    orders_date TIMESTAMP,
    orders_paid bool,
    orders_is_shipped bool
);

create sequence orders_id_sequence;

CREATE TABLE payment(
    payment_id INTEGER NOT NULL,
    payment_orders_id INTEGER,
    payment_paid boolean,
    payment_created TIMESTAMP
);

create sequence payment_id_sequence;