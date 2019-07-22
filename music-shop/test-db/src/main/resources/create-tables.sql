DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
  order_id INT NOT NULL AUTO_INCREMENT,
  order_number INT NOT NULL UNIQUE,
  order_date DATE NOT NULL,
  order_cost DECIMAL,
  PRIMARY KEY (order_id)
)