-- database m2_final_project
BEGIN TRANSACTION;

-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS users, material, business, trades, electricity_usage, carbon_savings CASCADE;

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
<<<<<<< HEAD
	ON DELETE CASCADE
=======
>>>>>>> 96066255511e6580dfb7beb60aa08db4c52c2eaa
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
<<<<<<< HEAD
    CONSTRAINT FK_trade_offered_material FOREIGN KEY(offered_material_id) REFERENCES material(material_id)
    ON DELETE CASCADE,
	CONSTRAINT FK_trade_requested_material FOREIGN KEY(requested_material_id) REFERENCES material(material_id)
	ON DELETE CASCADE,
	CONSTRAINT FK_trade_offered_business FOREIGN KEY(offered_by_business_id) REFERENCES business(business_id)
	ON DELETE CASCADE,
	CONSTRAINT FK_trade_requested_business FOREIGN KEY(requested_to_business_id) REFERENCES business(business_id)
	ON DELETE CASCADE
=======
    CONSTRAINT FK_trade_offered_material FOREIGN KEY(offered_material_id) REFERENCES material(material_id),
	CONSTRAINT FK_trade_requested_material FOREIGN KEY(requested_material_id) REFERENCES material(material_id),
	CONSTRAINT FK_trade_offered_business FOREIGN KEY(offered_by_business_id) REFERENCES business(business_id),
	CONSTRAINT FK_trade_requested_business FOREIGN KEY(requested_to_business_id) REFERENCES business(business_id)
>>>>>>> 96066255511e6580dfb7beb60aa08db4c52c2eaa

);

CREATE TABLE electricity_usage (
   id serial,
   material_name VARCHAR(100),
   avg_electricity_kwh_per_kg DECIMAL(10,4),
   electricity_usage_unit VARCHAR(50),
   CONSTRAINT PK_electricity_usage PRIMARY KEY(id)

);

CREATE TABLE carbon_savings (
   savings_id serial,
   business_name VARCHAR(100),
   material_name VARCHAR(100),
   quantity_kg DECIMAL (10, 2),
   electricity_used_kwh DECIMAL (10,4),
   co2_savings_kg DECIMAL (10,2),
   CONSTRAINT PK_carbon_savings PRIMARY KEY(savings_id)

);

-- *************************************************************************************************
-- Insert some sample starting data
-- *************************************************************************************************

-- Users
-- Password for all users is password
INSERT INTO
    users (username, password_hash, role)
VALUES
    ('user', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER'),
    ('admin','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_ADMIN');


INSERT INTO business (business_name, business_type, city, state, country)
      VALUES  ('Photobug', 'Industrial Specialties', 'Huntington', 'WV', 'US'),
              ('Trilith', 'Metal Fabrications', 'Fort Wayne', 'IN', 'US'),
              ('Skipfire', 'Railroads', 'Los Angeles', 'CA', 'US'),
              ('Thoughtstorm', 'Plastic Manufacturing', 'Virginia Beach', 'VA', 'US'),
              ('Talane', 'Savings Institutions', 'Fort Lauderdale', 'FL', 'US'),
              ('Meezzy', 'Major Banks', 'Fort Wayne', 'IN', 'US'),
              ('Voonte', 'Meat/Poultry/Fish', 'Madison', 'WI', 'US'),
              ('Oyondu', 'Diversified Commercial Services', 'Portland', 'ME', 'US'),
              ('Wordpedia', 'Brass Manufacturing', 'Charlotte', 'NC', 'US'),
              ('Chatterpoint', 'Office Equipment/Supplies/Services', 'Charlotte', 'NC', 'US'),
              ('Realcube', 'ConstructionLLC', 'Detroit', 'MI', 'US'),
              ('Zooveo', 'Investment Managers', 'Santa Fe', 'NM', 'US'),
              ('Rhynoodle', 'Investment Bankers/Brokers/Service', 'Pittsburgh', 'PA', 'US'),
              ('Yotz', 'Major Pharmaceuticals', 'Savannah', 'GA', 'US'),
              ('Dabvine', 'Telecommunications Equipment', 'West Palm Beach', 'FL', 'US'),
              ('Zoonder', 'Transportation Services', 'Naples', 'FL', 'US'),
              ('Wordify', 'Real Estate Investment Trusts', 'New Orleans', 'LA', 'US'),
              ('Gigazoom', 'Meat/Poultry/Fish', 'Metairie', 'LA', 'US'),
              ('Jetwire', 'Integrated oil Companies', 'Peoria', 'IL', 'US'),
              ('Dabvine', 'Oilfield Services/Equipment', 'Jacksonville', 'FL', 'US');



INSERT INTO material (business_id, material_name, condition, price, quantity_kg, is_available_for_trading)
              VALUES (2, 'Wood', 'Good', 7575.85, 2.0, true),
                     (5, 'Glass', 'Brand New', 1475.1, 12.0, true),
                     (3, 'Plexiglass', 'Excellent', 1785.34, 5.0, true),
                     (10, 'Granite', 'Good', 4573.83, 2.7, true),
<<<<<<< HEAD
                     (12, 'Brass', 'New', 1159.73, 7.4, true),
                     (1, 'Pipe', 'Good', 305.58, 5.3, true),
                     (1, 'Plastic', 'Excellent', 772.38, 4.2, true),
                     (2, 'Cement', 'New-Sealed', 8107.31, 4.15, true),
                     (10, 'Marble', 'Good', 5839.03, 4.23, true),
                     (4, 'Paper', 'New', 545.25, 7.99, true);
=======
                     (12, 'Brass', 'New', 1159.73, 7.4, false),
                     (1, 'Pipe', 'Good', 305.58, 5.3, true),
                     (1, 'Plastic', 'Excellent', 772.38, 4.2, false),
                     (2, 'Cement', 'New-Sealed', 8107.31, 4.15, true),
                     (10, 'Marble', 'Good', 5839.03, 4.23, false),
                     (4, 'Paper', 'New', 545.25, 7.99, false);
>>>>>>> 96066255511e6580dfb7beb60aa08db4c52c2eaa


INSERT INTO trades (offered_material_id, requested_material_id, offered_by_business_id, requested_to_business_id, trade_status, trade_date, created_By)
             VALUES (1, 5, 10, 9, 'pending', '2024-01-31', null),
                    (7, 10, 1, 14, 'pending', '2024-05-20', null),
                    (6, 5, 12, 7, 'accepted', '2023-09-15', null),
                    (2, 1, 3, 2, 'accepted', '2024-10-11', null),
                    (8, 2, 15, 17, 'rejected', '2024-09-25', null),
<<<<<<< HEAD
                    (1, 5, 2, 10, 'accepted', '2024-02-16', null),
                    (5, 1, 1, 2, 'accepted', '2023-12-11', null),
                    (6, 5, 10, 5, 'pending', '2025-01-11', null);
=======
                    (1, 5, 2, 2, 'accepted', '2024-02-16', null),
                    (5, 1, 1, 2, 'accepted', '2023-12-11', null),
                    (6, 5, 10, 2, 'pending', '2025-01-11', null);
>>>>>>> 96066255511e6580dfb7beb60aa08db4c52c2eaa


INSERT INTO electricity_usage (material_name, avg_electricity_kwh_per_kg, electricity_usage_unit)
            VALUES('Wood', 4.5, 'kwh'),
                  ('Aluminum', 15, 'kwh'),
                  ('Plastic', 17.2, 'kwh'),
                  ('Glass', 5, 'kwh'),
                  ('Paper', 6.95, 'kwh'),
                  ('Iron', 5.55, 'kwh'),
                  ('Titanium', 250, 'kwh'),
                  ('Copper', 16.6, 'kwh'),
                  ('Cement', 0.11, 'kwh'),
                  ('Marble', 0.55, 'kwh'),
                  ('Granite', 0.22, 'kwh'),
                  ('Nickel', 63.9, 'kwh');


COMMIT TRANSACTION;
