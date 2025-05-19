-- database m2_final_project
BEGIN TRANSACTION;

-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS users, material, business, trades;

-- *************************************************************************************************
-- Create the tables and constraints
-- *************************************************************************************************

--users (name is pluralized because 'user' is a SQL keyword)
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE business (
	business_id serial,
	business_name VARCHAR(100) NOT NULL,
	business_type VARCHAR(100) NOT NULL,
	city VARCHAR(100) NOT NULL,
	state VARCHAR(100) NOT NULL,
	country VARCHAR(100) NOT NULL,
	CONSTRAINT PK_business PRIMARY KEY(business_id)
);

CREATE TABLE material (
	material_id serial,
	business_id INT NOT NULL,
	material_name VARCHAR(100) NOT NULL,
	condition TEXT NOT NULL,
	price DOUBLE PRECISION NOT NULL,
	quantity_kg DECIMAL (10, 2) NOT NULL,
    is_available_for_trading boolean NOT NULL,
    created_by VARCHAR(100),
	CONSTRAINT PK_material PRIMARY KEY(material_id),
	CONSTRAINT FK_material_business FOREIGN KEY(business_id) REFERENCES business(business_id)
);

CREATE TABLE trades (
	trade_id serial,
	offered_material_id INT,
	requested_material_id INT,
	offered_by_business_id INT,
	requested_to_business_id INT,
	trade_status VARCHAR(50),
	trade_date VARCHAR(100),
	created_by VARCHAR(100),
	CONSTRAINT PK_trades PRIMARY KEY(trade_id),
    CONSTRAINT FK_trade_offered_material FOREIGN KEY(offered_material_id) REFERENCES material(material_id),
	CONSTRAINT FK_trade_requested_material FOREIGN KEY(requested_material_id) REFERENCES material(material_id),
	CONSTRAINT FK_trade_offered_business FOREIGN KEY(offered_by_business_id) REFERENCES business(business_id),
	CONSTRAINT FK_trade_requested_business FOREIGN KEY(requested_to_business_id) REFERENCES business(business_id)

);

COMMIT TRANSACTION;
