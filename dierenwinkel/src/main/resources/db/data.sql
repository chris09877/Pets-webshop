DELETE FROM orders;
-- DELETE FROM orders_content;
-- DELETE FROM orders_product;
DELETE from user;
DELETE from product;



INSERT INTO user (firstname, lastname, address, postcode, number, birthdate, phone, mail,password) VALUES
    ('John', 'Doe', '123 Main St', '12345', '123456789', '1990-01-01', '1234567890', 'john@example.com', '$2a$10$z38hVC0aW9BCAAHz3CfTAuqNdB1QV4OYnq1jvyEJnatDB.QPPVmF2'),

    ('Jane', 'Doe', '456 Elm St', '54321', '987654321', '1995-01-01', '0987654321', 'jane@example.com', '$2a$10$z38hVC0aW9BCAAHz3CfTAuqNdB1QV4OYnq1jvyEJnatDB.QPPVmF2');



INSERT INTO product (name, description, price, quantity, categories) VALUES
                                                                         ('Tropical Fish Food', 'High-quality fish food for tropical fish.', 9.99, 0, 0),
                                                                         ('Fish Tank Filter', 'Efficient filter for fish tanks.', 19.99, 0, 0),
                                                                         ('Goldfish Bowl', 'Classic bowl for goldfish.', 14.99, 0, 0),
                                                                         ('Dog Collar', 'Adjustable collar for dogs.', 12.99, 0, 1),
                                                                         ('Dog Chew Toy', 'Durable chew toy for dogs.', 7.99, 0, 1),
                                                                         ('Dog Shampoo', 'Gentle shampoo for dog grooming.', 11.99, 0, 1),
                                                                         ('Dog Treats', 'Tasty treats for rewarding your dog.', 5.99, 0, 1),
                                                                         ('Cat Litter', 'Absorbent litter for cats.', 8.99, 0, 2),
                                                                         ('Cat Scratching Post', 'Cat furniture for scratching.', 29.99, 0, 2),
                                                                         ('Cat Carrier', 'Convenient carrier for transporting cats.', 24.99, 0, 2),
                                                                         ('Cat Bed', 'Cozy bed for cats.', 19.99, 0, 2),
                                                                         ('Fish Tank Heater', 'Reliable heater for fish tanks.', 17.99, 0, 0),
                                                                         ('Dog Leash', 'Sturdy leash for walking dogs.', 9.99, 0, 1),
                                                                         ('Cat Food', 'Nutritious food for cats.', 12.99, 0, 2),
                                                                         ('Dog Bed', 'Comfortable bed for dogs.', 34.99, 0, 1),
                                                                         ('Fish Tank Decor', 'Decorative elements for fish tanks.', 14.99, 0, 0),
                                                                         ('Dog Bowl', 'Food and water bowl for dogs.', 6.99, 0, 1),
                                                                         ('Cat Toy', 'Interactive toy for entertaining cats.', 3.99, 0, 2),
                                                                         ('Dog Crate', 'Spacious crate for dogs.', 49.99, 0, 1),
                                                                         ('Fish Net', 'Handy net for handling fish.', 5.99, 0, 0),
                                                                         ('Cat Collar', 'Stylish collar for cats.', 8.99, 0, 2);



INSERT INTO orders (user_id, delivery_information, date, userInfo) VALUES
                                                          (1, 'Content for Order 1', CURRENT_TIMESTAMP, 'bbl'),
                                                          (2, 'Content for Order 2', CURRENT_TIMESTAMP, 'bbl'),
                                                          (1,'Content for Order 3', CURRENT_TIMESTAMP, 'bbl');

