-- Create the ticket purchase table
CREATE TABLE ticket_purchase (
    ticket_purchase_id BIGSERIAL PRIMARY KEY,
    number_of_tickets INTEGER NOT NULL,
    total_cost DECIMAL(12, 2) NOT NULL,
    confirmation_id TEXT UNIQUE NOT NULL,
    date_created TIMESTAMP WITHOUT TIME ZONE NOT NULL
);