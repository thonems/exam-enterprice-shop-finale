
insert into shipping
values(nextval('shipping_id_sequence'), 1, now(),false);

insert into shipping
values(nextval('shipping_id_sequence'), 2, now(),false);

insert into shipping
values(nextval('shipping_id_sequence'), 3, now(),false);

insert into shipping
values(nextval('shipping_id_sequence'), 4, now(),false);

insert into shipping
values(nextval('shipping_id_sequence'), 5, now(),false);

insert into shipping
values(nextval('shipping_id_sequence'), 6, now(),false);


insert into orders
values(nextval('orders_id_sequence'), now(), false,false);

insert into orders
values(nextval('orders_id_sequence'), now(),false,false);

insert into orders
values(nextval('orders_id_sequence'), now(),false,false);

insert into orders
values(nextval('orders_id_sequence'), now(),false,false);

insert into orders
values(nextval('orders_id_sequence'), now(),false,false);

insert into orders
values(nextval('orders_id_sequence'), now(),false,false);

insert into orders
values(nextval('orders_id_sequence'), now(),false,false);


insert into payment
values(nextval('payment_id_sequence'),1,false,null);

insert into payment
values(nextval('payment_id_sequence'),2,false,null);

insert into payment
values(nextval('payment_id_sequence'),3,false,null);

insert into payment
values(nextval('payment_id_sequence'),4,false,null);

insert into payment
values(nextval('payment_id_sequence'),5,false,null);

insert into payment
values(nextval('payment_id_sequence'),6,false,null);

insert into payment
values(nextval('payment_id_sequence'),7,false,null);
