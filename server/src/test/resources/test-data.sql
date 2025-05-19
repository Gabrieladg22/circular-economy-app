BEGIN TRANSACTION;

-- Users
INSERT INTO users (username, password_hash, role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('user3','user3','ROLE_USER');


--Data for tables

INSERT INTO business (business_name, business_type, city, state, country)
      VALUES  ('Photobug', 'Industrial Specialties', 'Huntington', 'West Virginia', 'US'),
              ('Trilith', 'Metal Fabrications', 'Fort Wayne', 'Indiana', 'US'),
              ('Skipfire', 'Railroads', 'Los Angeles', 'California', 'US'),
              ('Thoughtstorm', 'Plastic Manufacturing', 'Virginia Beach', 'Virginia', 'US'),
              ('Talane', 'Savings Institutions', 'Fort Lauderdale', 'Florida', 'US');


INSERT INTO material (business_id, material_name, condition, price, quantity_kg, is_available_for_trading, created_by)
              VALUES (5, 'Wood', 'Good', 7575.85, 2.50, true, null),
                     (4, 'Glass', 'Brand New', 1475.18, 13.1, true, null),
                     (1, 'Plexiglass', 'Excellent', 1785.34, 25.50, true, null),
                     (2, 'Granite', 'Good', 4573.83, 18.56, true, null),
                     (3, 'Brass', 'New', 1159.73, 11.44, false, null),
                     (5, 'Pipe', 'Good', 305.58, 12.45, true, null),
                     (1, 'Metal', 'Old', 309.50, 25.60, true, null);


INSERT INTO trades (offered_material_id, requested_material_id, offered_by_business_id, requested_to_business_id, trade_status, trade_date, created_by)
             VALUES (1, 5, 1, 5, 'pending', '2024-01-31', null),
                    (5, 3, 4, 1, 'pending', '2024-05-20', null),
                    (6, 4, 1, 2, 'accepted', '2023-09-15', null),
                    (2, 3, 1, 2, 'accepted', '2024-10-11', null);


COMMIT TRANSACTION;
