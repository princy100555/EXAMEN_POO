  CREATE DATABASE cash_flow_db;
  Creation de enum:
  CREATE TYPE expense_frequency AS ENUM (
      'NONE',
      'MONTHLY',
      'WEEKLY',
      'YEARLY'
  );

    TABLE USER:
  CREATE TABLE users (
      id VARCHAR(50) PRIMARY KEY,
      ref VARCHAR(100) NOT NULL UNIQUE,
      first_name VARCHAR(100) NOT NULL,
      last_name VARCHAR(100) NOT NULL,
      email VARCHAR(255) NOT NULL UNIQUE,
      phone VARCHAR(30)
  );

  TABLE CASH_FLOWS:

  CREATE TABLE cash_flows (
      id VARCHAR(50) PRIMARY KEY,
      user_id VARCHAR(50) NOT NULL,
      created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
      amount NUMERIC(15, 2) NOT NULL,

      CONSTRAINT fk_cash_flow_user
          FOREIGN KEY (user_id)
          REFERENCES users(id)
          ON DELETE CASCADE,

      CONSTRAINT check_cash_flow_amount
          CHECK (amount >= 0)
  );

  TABLE DONATIONS:

  CREATE TABLE donations (
      id VARCHAR(50) PRIMARY KEY,
      comment TEXT,

      CONSTRAINT fk_donation_cash_flow
          FOREIGN KEY (id)
          REFERENCES cash_flows(id)
          ON DELETE CASCADE
  );

  TABLE EXPENSES:

  CREATE TABLE expenses (
      id VARCHAR(50) PRIMARY KEY,
      reason VARCHAR(255) NOT NULL,
      frequency expense_frequency NOT NULL DEFAULT 'NONE',

      CONSTRAINT fk_expense_cash_flow
          FOREIGN KEY (id)
          REFERENCES cash_flows(id)
          ON DELETE CASCADE
  );