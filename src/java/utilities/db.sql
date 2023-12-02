/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  edgar y jordi
 * Created: 30 nov 2023
 */
INSERT INTO CREDENTIALS (ID, USERNAME, PASSWORD) VALUES (1, 'sob', 'sob')

/* Ejemplos para la clase GAME*/

INSERT INTO GAME (ID, NAME, STOCK, DESCRIPTION, CITY, STATE, STREET, ZIP_CODE) VALUES (2, 'Super Mario Odyssey', 20, 'An amazing adventure game', 'Cityville', 'Stateville', '123 Main St', '12345')
INSERT INTO GAME (ID, NAME, STOCK, DESCRIPTION, CITY, STATE, STREET, ZIP_CODE) VALUES (3, 'Call of Duty: Modern Warfare', 15, 'Intense first-person shooter', 'Cityville', 'Stateville', '123 Main St', '12345')
INSERT INTO GAME (ID, NAME, STOCK, DESCRIPTION, CITY, STATE, STREET, ZIP_CODE) VALUES (4, 'The Witcher 3: Wild Hunt', 10, 'Epic open-world RPG', 'Townsville', 'Provinceville', '456 Oak St', '67890')
INSERT INTO GAME (ID, NAME, STOCK, DESCRIPTION, CITY, STATE, STREET, ZIP_CODE) VALUES (5, 'FIFA 22', 30, 'Football simulation game', 'Townsville', 'Provinceville', '456 Oak St', '67890')
INSERT INTO GAME (ID, NAME, STOCK, DESCRIPTION, CITY, STATE, STREET, ZIP_CODE) VALUES (6, 'FIFA 23', 0, 'Football simulation game', 'Cityville', 'Stateville', '123 Main St', '12345')

/* Ejemplos para la clase CONSOLE*/

INSERT INTO CONSOLE (ID, NAME) VALUES (1, 'PlayStation 5')
INSERT INTO CONSOLE (ID, NAME) VALUES (2, 'Xbox Series X')
INSERT INTO CONSOLE (ID, NAME) VALUES (3, 'Nintendo Switch')
INSERT INTO CONSOLE (ID, NAME) VALUES (4, 'PC Gaming Master Race')

/* Ejemplos para la clase CUSTOMER*/

INSERT INTO CUSTOMER (ID, NAME, EMAIL, PASSWORD) VALUES ('AO123456', 'John Doe', 'john@example.com', 'john_password')
INSERT INTO CUSTOMER (ID, NAME, EMAIL, PASSWORD) VALUES ('123456', 'Jane Doe', 'jane@example.com', 'jane_password')
INSERT INTO CUSTOMER (ID, NAME, EMAIL, PASSWORD) VALUES ('ASDQWE', 'Alice Johnson', 'alice@example.com', 'alice_password')
INSERT INTO CUSTOMER (ID, NAME, EMAIL, PASSWORD) VALUES ('QWERT631', 'Bob Smith', 'bob@example.com', 'bob_password')

/* Ejemplos para la clase GAMETYPE*/

INSERT INTO GAMETYPE (ID, NAME) VALUES (1, 'Action/Adventure')
INSERT INTO GAMETYPE (ID, NAME) VALUES (2, 'First Person Shooter')
INSERT INTO GAMETYPE (ID, NAME) VALUES (3, 'Role-Playing Game')
INSERT INTO GAMETYPE (ID, NAME) VALUES (4, 'Sports')