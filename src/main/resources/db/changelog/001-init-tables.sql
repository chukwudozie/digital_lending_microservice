-- liquibase formatted sql

-- changeset ifeanyichukwuOtiwa-sports:001-init-tables.sql

CREATE TABLE IF NOT EXISTS customer
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    email        VARCHAR(300),
    first_name   VARCHAR(300)       NOT NULL,
    last_name    VARCHAR(300)       NOT NULL,
    password     VARCHAR(300)       NOT NULL,
    phone_number VARCHAR(11) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS mobile_wallet
(
    id                         BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_number             VARCHAR(11) NOT NULL,
    loan_maximum_qualification BIGINT      NOT NULL,
    wallet_balance             DECIMAL     NOT NULL,
    CONSTRAINT fk_customer_mobile_wallet FOREIGN KEY (account_number) REFERENCES customer (phone_number) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS transaction
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(11)  NOT NULL,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    status         VARCHAR(255) NOT NULL,
    loan_type      VARCHAR(10)  NOT NULL,
    CONSTRAINT fk_customer_transaction FOREIGN KEY (account_number) REFERENCES customer (phone_number) ON DELETE NO ACTION
);
