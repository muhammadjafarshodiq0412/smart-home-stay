INSERT INTO smart_home_stay.customer
(created_date, id, updated_date, created_by, email, first_name, last_name, nik, password, phone_number, status, updated_by, username)
VALUES('2023-08-01 02:33:45', 1, NULL, 'Admin', 'Faqih@gmail.com', 'Faqih', 'Egy', '315151204970001', '123', '+6281545975154', 'ACTIVED', NULL, 'faqih240497');

INSERT INTO smart_home_stay.room_type
(price_per_night, created_date, updated_date, created_by, description, name, updated_by)
VALUES(100, now(), NULL, 'SYSTEM', 'Kamar Single cocok untuk joblo biar hemat', 'Single', NULL);

INSERT INTO smart_home_stay.room_type
(price_per_night, created_date, updated_date, created_by, description, name, updated_by)
VALUES(200, now(), NULL, 'SYSTEM', 'Kamar 2 orang', 'Twin', NULL);

INSERT INTO smart_home_stay.room_type
(price_per_night, created_date, updated_date, created_by, description, name, updated_by)
VALUES(400, now(), NULL, 'SYSTEM', 'Kamar Mewah', 'Deluxe', NULL);

INSERT INTO smart_home_stay.room_type
(price_per_night, created_date, updated_date, created_by, description, name, updated_by)
VALUES(600, now(), NULL, 'SYSTEM', 'Kamar Keluarga', 'Family', NULL);

INSERT INTO smart_home_stay.room_facilities
(price, created_date, updated_date, created_by, description, name, updated_by)
VALUES(20, now(), NULL, 'SYSTEM', 'Nasi Goreng', 'Breakfast', NULL);

INSERT INTO smart_home_stay.room_facilities
(price, created_date, updated_date, created_by, description, name, updated_by)
VALUES(50, now(), NULL, 'SYSTEM', 'Kasur Busa', 'Extra Bed', NULL);

INSERT INTO smart_home_stay.room
(created_date, id, room_type_id, updated_date, capacity, created_by, floor, room_number, status, updated_by)
VALUES('2023-08-01 02:34:09', 1, 4, '2023-08-01 02:34:32', '5', 'Admin', '2', '4', 'AVAILABLE', NULL);
