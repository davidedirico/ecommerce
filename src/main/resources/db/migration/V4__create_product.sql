CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(255) NOT NULL,
    category VARCHAR(40) NOT NULL,

    unit_price NUMERIC(19, 2) NOT NULL,
    available_quantity INTEGER NOT NULL,

    active BOOLEAN NOT NULL,

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    version BIGINT NOT NULL
);
