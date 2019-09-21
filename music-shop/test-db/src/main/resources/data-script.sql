INSERT INTO orders (order_date) VALUES ('2019-08-1');
INSERT INTO orders (order_date) VALUES ('2019-08-2');
INSERT INTO orders (order_date) VALUES ('2019-08-3');

INSERT INTO items (item_id, item_name, item_price) VALUES(1, 'Gibson Les Paul', 1100);
INSERT INTO items (item_id, item_name, item_price) VALUES(2, 'Fender Stratocaster', 1200);
INSERT INTO items (item_id, item_name, item_price) VALUES(3, 'Fender Telecaster', 900);
INSERT INTO items (item_id, item_name, item_price) VALUES(4, 'Tama Drums', 1600);
INSERT INTO items (item_id, item_name, item_price) VALUES(5, 'Soner', 1500);
INSERT INTO items (item_id, item_name, item_price) VALUES(6, 'Yamaha', 1400);
INSERT INTO items (item_id, item_name, item_price) VALUES(7, 'Strings', 15);
INSERT INTO items (item_id, item_name, item_price) VALUES(8, 'Pics', 5);
INSERT INTO items (item_id, item_name, item_price) VALUES(9, 'Drum Kit', 30);

INSERT INTO order_items (order_id, item_id) VALUES (1, 1);
INSERT INTO order_items (order_id, item_id) VALUES (1, 7);
INSERT INTO order_items (order_id, item_id) VALUES (1, 8);
INSERT INTO order_items (order_id, item_id) VALUES (2, 4);
INSERT INTO order_items (order_id, item_id) VALUES (2, 9);
INSERT INTO order_items (order_id, item_id) VALUES (3, 2);
INSERT INTO order_items (order_id, item_id) VALUES (3, 5);
