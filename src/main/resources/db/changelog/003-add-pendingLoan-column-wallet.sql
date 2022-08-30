-- liquibase formatted sql

-- changeset ifeanyichukwuOtiwa-sports:003-add-pendingLoan-column-wallet.sql
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) from information_schema.columns where column_name='pending_loan' and table_name='mobile_wallet'

ALTER TABLE mobile_wallet
ADD COLUMN pending_loan DECIMAL NOT NULL DEFAULT 0.0 AFTER wallet_balance;