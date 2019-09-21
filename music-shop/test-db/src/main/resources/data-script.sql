INSERT INTO orders (order_date) VALUES ('2019-08-1');
INSERT INTO orders (order_date) VALUES ('2019-08-2');
INSERT INTO orders (order_date) VALUES ('2019-08-3');

INSERT INTO items (item_id, item_name, item_price, is_reserved) VALUES(1, 'Gibson Les Paul', 1100, true);
INSERT INTO items (item_id, item_name, item_price, is_reserved) VALUES(2, 'Fender Stratocaster', 1200, true);
INSERT INTO items (item_id, item_name, item_price, is_reserved) VALUES(3, 'Fender Telecaster', 900, true);
INSERT INTO items (item_id, item_name, item_price, is_reserved) VALUES(4, 'Tama Drums', 1600, true);
INSERT INTO items (item_id, item_name, item_price, is_reserved) VALUES(5, 'Soner', 1500, true);
INSERT INTO items (item_id, item_name, item_price, is_reserved) VALUES(6, 'Yamaha', 1400, true);
INSERT INTO items (item_id, item_name, item_price, is_reserved) VALUES(7, 'Strings', 15, true);
INSERT INTO items (item_id, item_name, item_price, is_reserved) VALUES(8, 'Pics', 5, true);
INSERT INTO items (item_id, item_name, item_price, is_reserved) VALUES(9, 'Drum Kit', 30, true);
INSERT INTO items (item_id, item_name, item_price, is_reserved) VALUES(10, 'smth0', 30, false);
INSERT INTO items (item_id, item_name, item_price, is_reserved) VALUES(11, 'smth1', 31, false);
INSERT INTO items (item_id, item_name, item_price, is_reserved) VALUES(12, 'smth3', 32, false);
INSERT INTO items (item_id, item_name, item_price, is_reserved) VALUES(13, 'smth3', 33, false);
INSERT INTO items (item_id, item_name, item_price, is_reserved) VALUES(14, 'smth4', 34, false);
INSERT INTO items (item_id, item_name, item_price, is_reserved) VALUES(15, 'smth5', 35, false);
INSERT INTO items (item_id, item_name, item_price, is_reserved) VALUES(16, 'smth6', 36, false);

INSERT INTO order_items (order_id, item_id) VALUES (1, 1);
INSERT INTO order_items (order_id, item_id) VALUES (1, 7);
INSERT INTO order_items (order_id, item_id) VALUES (1, 8);
INSERT INTO order_items (order_id, item_id) VALUES (2, 4);
INSERT INTO order_items (order_id, item_id) VALUES (2, 9);
INSERT INTO order_items (order_id, item_id) VALUES (3, 2);
INSERT INTO order_items (order_id, item_id) VALUES (3, 5);
