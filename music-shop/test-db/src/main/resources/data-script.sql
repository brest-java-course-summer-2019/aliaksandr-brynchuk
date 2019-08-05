/*INSERT INTO orders (order_id, order_number, order_date) VALUES (1, 1001, '2019-08-1');
INSERT INTO orders (order_id, order_number, order_date) VALUES (2, 1002, '2019-08-2');
INSERT INTO orders (order_id, order_number, order_date) VALUES (3, 1003, '2019-08-3');

INSERT INTO items (item_id, group_name, item_name, item_price, order_id) VALUES(1, "Guitars", "Gibson Les Paul", 1100, 1);
INSERT INTO items (item_id, group_name, item_name, item_price, order_id) VALUES(2, "Guitars", "Fender Stratocaster", 1200, 2);
INSERT INTO items (item_id, group_name, item_name, item_price, order_id) VALUES(3, "Guitars", "Fender Telecaster", 900, 2);
INSERT INTO items (item_id, group_name, item_name, item_price, order_id) VALUES(4, "Drums", "Tama Drums", 1600, 1);
INSERT INTO items (item_id, group_name, item_name, item_price, order_id) VALUES(5, "Drums", "Soner", 1500, 3);
INSERT INTO items (item_id, group_name, item_name, item_price, order_id) VALUES(6, "Drums", "Yamaha", 1400, 2);
INSERT INTO items (item_id, group_name, item_name, item_price, order_id) VALUES(7, "Accessories", "Strings", 15, 1);
INSERT INTO items (item_id, group_name, item_name, item_price, order_id) VALUES(8, "Accessories", "Pics", 5, 1);
INSERT INTO items (item_id, group_name, item_name, item_price, order_id) VALUES(9, "Accessories", "Drum Kit", 30, 3);*/

INSERT INTO item_firm (firm_id, firm_name) VALUES (1, 'Fender');
INSERT INTO item_firm (firm_id, firm_name) VALUES (2, 'Gibson');
INSERT INTO item_firm (firm_id, firm_name) VALUES (3, 'Tama Drums');
INSERT INTO item_firm (firm_id, firm_name) VALUES (4, 'SONER');
INSERT INTO item_firm (firm_id, firm_name) VALUES (5, 'Yamaha');
INSERT INTO item_firm (firm_id, firm_name) VALUES (6, 'Dunlop');

INSERT INTO items_group (group_id, group_name) VALUES (1, 'Gutars');
INSERT INTO items_group (group_id, group_name) VALUES (2, 'Drums');
INSERT INTO items_group (group_id, group_name) VALUES (3, 'Accesories');

INSERT INTO items (item_id, group_id, firm_id, item_name, item_price) VALUES(1, 1, 2, 'Les Paul', 1100.1);
INSERT INTO items (item_id, group_id, firm_id, item_name, item_price) VALUES(2, 1, 1, 'Telecaster', 900.2);
INSERT INTO items (item_id, group_id, firm_id, item_name, item_price) VALUES(3, 1, 1, 'Stratocaster', 1200.3);
INSERT INTO items (item_id, group_id, firm_id, item_name, item_price) VALUES(4, 2, 3, 'ML40HZBN2-FBV', 2100.4);
INSERT INTO items (item_id, group_id, firm_id, item_name, item_price) VALUES(5, 2, 4, 'AQ1 Stage Set', 2000.5);
INSERT INTO items (item_id, group_id, firm_id, item_name, item_price) VALUES(6, 2, 5, 'TOUR CUSTOM', 1600.7);
INSERT INTO items (item_id, group_id, firm_id, item_name, item_price) VALUES(7, 3, 6, 'Strings', 10.8);
INSERT INTO items (item_id, group_id, firm_id, item_name, item_price) VALUES(8, 3, 6, 'Pics', 5.9);
INSERT INTO items (item_id, group_id, firm_id, item_name, item_price) VALUES(9, 3, 5, 'Drum Kit', 25.1);


INSERT INTO orders (order_id, order_number, order_date) VALUES (1, '1001', '2019-7-21');
INSERT INTO orders (order_id, order_number, order_date) VALUES (2, '1002', '2019-7-22');
INSERT INTO orders (order_id, order_number, order_date) VALUES (3, '1003', '2019-7-23');

INSERT INTO order_items_list (order_id, item_id) VALUES (1, 1);
INSERT INTO order_items_list (order_id, item_id) VALUES (1, 7);
INSERT INTO order_items_list (order_id, item_id) VALUES (1, 8);
INSERT INTO order_items_list (order_id, item_id) VALUES (2, 4);
INSERT INTO order_items_list (order_id, item_id) VALUES (2, 9);
INSERT INTO order_items_list (order_id, item_id) VALUES (3, 2);
INSERT INTO order_items_list (order_id, item_id) VALUES (3, 5);

