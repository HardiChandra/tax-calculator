CREATE TABLE IF NOT EXISTS transaction (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  tax_code INTEGER NOT NULL,
  price DECIMAL(19, 2) NOT NULL
);
