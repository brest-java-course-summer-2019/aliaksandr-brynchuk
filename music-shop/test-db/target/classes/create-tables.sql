DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
  order_id INT NOT NULL AUTO_INCREMENT,
  order_number INT NOT NULL UNIQUE,
  order_date DATE NOT NULL,
  PRIMARY KEY (order_id)
);

CREATE TABLE items_group (
  group_id INT NOT NULL,
  group_name VARCHAR(45) NOT NULL,
  PRIMARY KEY(group_id)
);

CREATE TABLE item_firm (
  firm_id INT NOT NULL,
  firm_name VARCHAR(255) NOT NULL,
  PRIMARY KEY (firm_id)
);
DROP TABLE IF EXISTS items;
CREATE TABLE items (
  item_id INT NOT NULL AUTO_INCREMENT,
  group_id VARCHAR(45) NOT NULL,
  firm_id INT NOT NULL,
  item_name VARCHAR(255) NOT NULL,
  item_price DECIMAL NOT NULL,
  PRIMARY KEY(item_id),
  FOREIGN KEY (group_id) REFERENCES items_group,
  FOREIGN KEY (firm_id) REFERENCES item_firm
);

CREATE TABLE order_items_list(
  order_id INT NOT NULL,
  item_id INT NOT NULL,
  PRIMARY KEY (order_id, item_id),
  FOREIGN KEY(order_id) REFERENCES orders,
  FOREIGN KEY (item_id) REFERENCES items
);



