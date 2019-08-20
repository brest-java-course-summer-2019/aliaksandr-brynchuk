DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
  order_id     INT         NOT NULL AUTO_INCREMENT UNIQUE,
  order_date   DATE        NOT NULL,
  client_id    INT         NOT NULL,
  order_status VARCHAR(45) NOT NULL,
  PRIMARY KEY (order_id)
);

DROP TABLE IF EXISTS items;
CREATE TABLE items
(
  item_id     INT          NOT NULL AUTO_INCREMENT UNIQUE,
  group_name  VARCHAR(255) NOT NULL,
  item_name   VARCHAR(255) NOT NULL,
  item_price  DECIMAL      NOT NULL,
  item_status VARCHAR(45)  NOT NULL,
  item_count  INT          NOT NULL,
  PRIMARY KEY (item_id),
);

CREATE TABLE order_items
(
  order_id INT NOT NULL,
  item_id  INT NOT NULL,
  count    INT NOT NULL,
  PRIMARY KEY (order_id, item_id),
  FOREIGN KEY (order_id) REFERENCES orders,
  FOREIGN KEY (item_id) REFERENCES items
);


