CREATE TABLE IF NOT EXISTS product
(
    id    VARCHAR(50)  NOT NULL,
    name  VARCHAR(100) NOT NULL,
    price INT          NOT NULL,
    count INT          NOT NULL,
    PRIMARY KEY (id)
);