-- liquibase formatted sql

-- changeset ifeanyichukwuOtiwa-sports:002-add-amount-column-transaction.sql
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) from information_schema.columns where column_name='amount' and table_name='transaction'

ALTER TABLE transaction
ADD COLUMN amount DECIMAL NOT NULL AFTER account_number;