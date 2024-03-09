INSERT INTO user (firstname, lastname, address, postcode, number, birthdate, phone, mail)
VALUES ('John', 'Doe', '123 Main St', '12345', '123456789', '1990-01-01', '1234567890', 'john@example.com'),
       ('Jane', 'Doe', '456 Elm St', '54321', '987654321', '1995-01-01', '0987654321', 'jane@example.com');

INSERT INTO orders (user_id, content, date, userInfo) VALUES (1, 'Content for Order 1', CURRENT_TIMESTAMP, 'bbl'),
                                                             (2, 'Content for Order 2', CURRENT_TIMESTAMP, 'bbl'),
                                                             (1,'Content for Order 3', CURRENT_TIMESTAMP, 'bbl');

INSERT INTO product (name, description, price, quantity, categories) VALUES
                                                                         ('Tropical Fish Food', 'High-quality fish food for tropical fish.', 9.99, 0, 1),
                                                                         ('Fish Tank Filter', 'Efficient filter for fish tanks.', 19.99, 0, 1),
                                                                         ('Goldfish Bowl', 'Classic bowl for goldfish.', 14.99, 0, 1),
                                                                         ('Dog Collar', 'Adjustable collar for dogs.', 12.99, 0, 2),
                                                                         ('Dog Chew Toy', 'Durable chew toy for dogs.', 7.99, 0, 2),
                                                                         ('Dog Shampoo', 'Gentle shampoo for dog grooming.', 11.99, 0, 2),
                                                                         ('Dog Treats', 'Tasty treats for rewarding your dog.', 5.99, 0, 2),
                                                                         ('Cat Litter', 'Absorbent litter for cats.', 8.99, 0, 3),
                                                                         ('Cat Scratching Post', 'Cat furniture for scratching.', 29.99, 0, 3),
                                                                         ('Cat Carrier', 'Convenient carrier for transporting cats.', 24.99, 0, 3),
                                                                         ('Cat Bed', 'Cozy bed for cats.', 19.99, 0, 3),
                                                                         ('Fish Tank Heater', 'Reliable heater for fish tanks.', 17.99, 0, 1),
                                                                         ('Dog Leash', 'Sturdy leash for walking dogs.', 9.99, 0, 2),
                                                                         ('Cat Food', 'Nutritious food for cats.', 12.99, 0, 3),
                                                                         ('Dog Bed', 'Comfortable bed for dogs.', 34.99, 0, 2),
                                                                         ('Fish Tank Decor', 'Decorative elements for fish tanks.', 14.99, 0, 1),
                                                                         ('Dog Bowl', 'Food and water bowl for dogs.', 6.99, 0, 2),
                                                                         ('Cat Toy', 'Interactive toy for entertaining cats.', 3.99, 0, 3),
                                                                         ('Dog Crate', 'Spacious crate for dogs.', 49.99, 0, 2),
                                                                         ('Fish Net', 'Handy net for handling fish.', 5.99, 0, 1),
                                                                         ('Cat Collar', 'Stylish collar for cats.', 8.99, 0, 3);

