-- liquibase formatted sql

-- changeset ifeanyichukwuOtiwa-sports:004-add-description-column-transaction.sql
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) from information_schema.columns where column_name='description' and table_name='transaction'

ALTER TABLE transaction
ADD COLUMN description TEXT NOT NULL AFTER account_number;