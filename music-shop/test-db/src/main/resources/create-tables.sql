DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
  order_id INT NOT NULL AUTO_INCREMENT,
  order_number INT NOT NULL UNIQUE,
  order_date DATE NOT NULL,
  PRIMARY KEY (order_id)
);
DROP TABLE IF EXISTS items;
CREATE TABLE items (
  item_id INT NOT NULL AUTO_INCREMENT,
  item_group VARCHAR(45) NOT NULL,
  item_name VARCHAR(255) NOT NULL,
  item_cost DECIMAL NOT NULL,
  PRIMARY KEY(item_id)
);

CREATE TABLE items_list(
  order_id INT NOT NULL,
  item_id INT NOT NULL,
  PRIMARY KEY (order_id, item_id),
  FOREIGN KEY(order_id) REFERENCES orders,
  FOREIGN KEY (item_id) REFERENCES items
)