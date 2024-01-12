INSERT INTO CREDENTIALS (ID, USERNAME, PASSWORD) VALUES (1, 'sob', 'sob')

INSERT INTO CONSOLE (ID, NAME) VALUES (1, 'PlayStation 5')
INSERT INTO CONSOLE (ID, NAME) VALUES (2, 'Xbox Series X')
INSERT INTO CONSOLE (ID, NAME) VALUES (3, 'Nintendo Switch')
INSERT INTO CONSOLE (ID, NAME) VALUES (4, 'PC Gaming Master Race')

INSERT INTO GAMETYPE (ID, NAME) VALUES (1, 'Adventure')
INSERT INTO GAMETYPE (ID, NAME) VALUES (2, 'FPS')
INSERT INTO GAMETYPE (ID, NAME) VALUES (3, 'RPG')
INSERT INTO GAMETYPE (ID, NAME) VALUES (4, 'Sports')

INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID) VALUES (2, 'Super Mario Odyssey', 2, 20, 'An amazing adventure game', 10.0, 'Cityville', 'Stateville', '123 Main St', '12345', 3)
INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID) VALUES (3, 'Call of Duty: Modern Warfare' , 3, 15, 'Call of Duty: Modern Warfare III (abreviado COD: MW III) es un videojuego de disparos en primera persona desarrollado por Infinity Ward y Sledgehammer Games y publicado por Activision, lanzándose para PlayStation 4, PlayStation 5, Xbox One, Xbox Series X|S y Microsoft Windows. Es la secuela de la anterior entrega y es la vigésima entrega de la serie Call of Duty. El lanzamiento oficial del juego fue el 10 de noviembre de 2023 y la revelación oficial tuvo lugar el 17 de agosto en un evento en Warzone 2.0. ', 10.0, 'Cityville', 'Stateville', '123 Main St', '12345', 1)
INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID) VALUES (4, 'The Witcher 3: Wild Hunt', 4, 10, 'Epic open-world RPG', 10.0, 'Townsville', 'Provinceville', '456 Oak St', '67890', 2)
INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID) VALUES (5, 'FIFA 25', 5, 30, 'Football simulation game' , 10.0, 'Townsville', 'Provinceville', '456 Oak St', '67890', 4)
INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID) VALUES (6, 'FIFA 26', 6, 0, 'Football simulation game', 10.0, 'Cityville', 'Stateville', '123 Main St', '12345', 1)
INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID) VALUES (7, 'FIFA 27', 6, 0, 'Football simulation game', 10.0, 'Cityville', 'Stateville', '123 Main St', '12345', 1)
INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID) VALUES (8, 'FIFA 28', 6, 0, 'Football simulation game', 10.0, 'Cityville', 'Stateville', '123 Main St', '12345', 1)
INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID) VALUES (9, 'FIFA 29', 6, 0, 'Football simulation game', 10.0, 'Cityville', 'Stateville', '123 Main St', '12345', 1)
INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID) VALUES (10, 'FIFA 30', 6, 0, 'Football simulation game', 10.0, 'Cityville', 'Stateville', '123 Main St', '12345', 1)

INSERT INTO CUSTOMER (ID, USERNAME, EMAIL, PASSWORD) VALUES (2, 'John Doe', 'john@example.com', 'john_password')
INSERT INTO CUSTOMER (ID, USERNAME, EMAIL, PASSWORD) VALUES (3, 'Jane Doe', 'jane@example.com', 'jane_password')
INSERT INTO CUSTOMER (ID, USERNAME, EMAIL, PASSWORD) VALUES (4, 'Alice Johnson', 'alice@example.com', 'alice_password')
INSERT INTO CUSTOMER (ID, USERNAME, EMAIL, PASSWORD) VALUES (5, 'Bob Smith', 'bob@example.com', 'bob_password')
INSERT INTO CUSTOMER (ID, USERNAME, EMAIL, PASSWORD) VALUES (6, 'sob', 'sob@sob.com', 'sob_password')
INSERT INTO CUSTOMER (ID, USERNAME, EMAIL, PASSWORD) VALUES (7, 'test', 'test@test', 'test1234')

INSERT INTO GAME_GAMETYPE(GAMES_ID, TYPES_ID) VALUES (2,1)
INSERT INTO GAME_GAMETYPE(GAMES_ID, TYPES_ID) VALUES (3,1)
INSERT INTO GAME_GAMETYPE(GAMES_ID, TYPES_ID) VALUES (3,2)
INSERT INTO GAME_GAMETYPE(GAMES_ID, TYPES_ID) VALUES (4,1)
INSERT INTO GAME_GAMETYPE(GAMES_ID, TYPES_ID) VALUES (4,3)
INSERT INTO GAME_GAMETYPE(GAMES_ID, TYPES_ID) VALUES (5,4)
INSERT INTO GAME_GAMETYPE(GAMES_ID, TYPES_ID) VALUES (6,4)
INSERT INTO GAME_GAMETYPE(GAMES_ID, TYPES_ID) VALUES (7,4)
INSERT INTO GAME_GAMETYPE(GAMES_ID, TYPES_ID) VALUES (8,4)
INSERT INTO GAME_GAMETYPE(GAMES_ID, TYPES_ID) VALUES (9,4)
INSERT INTO GAME_GAMETYPE(GAMES_ID, TYPES_ID) VALUES (10,4)
