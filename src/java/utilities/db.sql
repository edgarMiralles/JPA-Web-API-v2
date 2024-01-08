INSERT INTO CREDENTIALS (ID, USERNAME, PASSWORD) VALUES (1, 'sob', 'sob')

INSERT INTO CONSOLE (ID, NAME) VALUES (1, 'PlayStation 5')
INSERT INTO CONSOLE (ID, NAME) VALUES (2, 'Xbox Series X')
INSERT INTO CONSOLE (ID, NAME) VALUES (3, 'Nintendo Switch')
INSERT INTO CONSOLE (ID, NAME) VALUES (4, 'PC Gaming Master Race')

INSERT INTO GAMETYPE (ID, NAME) VALUES (1, 'Adventure')
INSERT INTO GAMETYPE (ID, NAME) VALUES (2, 'FPS')
INSERT INTO GAMETYPE (ID, NAME) VALUES (3, 'RPG')
INSERT INTO GAMETYPE (ID, NAME) VALUES (4, 'Sports')

INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID)
VALUES (2, 'Super Mario Odyssey', 2, 20, 'Embark on an incredible adventure with Mario as he sets out to rescue Princess Peach from the evil Bowser. Discover new worlds, face challenging enemies, and collect moons to progress in your mission. A unique gaming experience filled with fun and surprises!', 10.0, 'Cityville', 'Stateville', '123 Main St', '12345', 3);

INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID)
VALUES (3, 'Call of Duty: Modern Warfare' , 3, 15, 'Immerse yourself in the intensity of modern warfare with this thrilling first-person shooter game. Experience an epic campaign, engage in exciting multiplayer battles, and showcase your tactical skills. Get ready for action!', 10.0, 'Cityville', 'Stateville', '123 Main St', '12345', 1);

INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID)
VALUES (4, 'The Witcher 3: Wild Hunt', 4, 10, 'Dive into the vast world of The Witcher 3, an epic open-world role-playing game. Embark on a thrilling quest to find your adopted daughter and face moral choices that shape the outcome of the story. An immersive and gripping RPG experience!', 10.0, 'Townsville', 'Provinceville', '456 Oak St', '67890', 2);

INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID)
VALUES (5, 'FIFA 25', 5, 30, 'Experience the excitement of football with FIFA 25, a cutting-edge football simulation game. Enjoy realistic gameplay, stunning graphics, and immersive modes that capture the essence of the sport. Compete with your favorite teams and players!', 10.0, 'Townsville', 'Provinceville', '456 Oak St', '67890', 4);

INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID)
VALUES (6, 'FIFA 26', 6, 0, 'Continue the legacy of FIFA with the 26th edition of the acclaimed football simulation game. Immerse yourself in the world of competitive football, featuring updated teams, improved graphics, and thrilling gameplay. Take your skills to the next level!', 10.0, 'Cityville', 'Stateville', '123 Main St', '12345', 1);

INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID)
VALUES (7, 'FIFA 27', 6, 0, 'Explore the latest innovations in football gaming with FIFA 27. Dive into realistic matches, enjoy enhanced visuals, and experience the excitement of virtual football. Compete in various leagues and tournaments to prove your skills!', 10.0, 'Cityville', 'Stateville', '123 Main St', '12345', 1);

INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID)
VALUES (8, 'FIFA 28', 6, 0, 'Enter the world of FIFA 28 and witness the evolution of football simulation. With improved features, updated rosters, and thrilling gameplay, this edition promises an unparalleled gaming experience. Join the global football community and compete at the highest level!', 10.0, 'Cityville', 'Stateville', '123 Main St', '12345', 1);

INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID)
VALUES (9, 'FIFA 29', 6, 0, 'Take your football journey to new heights with FIFA 29. Featuring cutting-edge graphics, realistic player movements, and engaging game modes, this edition delivers an immersive football experience. Become a football legend in this thrilling simulation!', 10.0, 'Cityville', 'Stateville', '123 Main St', '12345', 1);

INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID)
VALUES (10, 'FIFA 30', 6, 0, 'Experience the future of football gaming with FIFA 30. Immerse yourself in a world of unparalleled realism, dynamic gameplay, and advanced features. Join the global FIFA community and compete for glory in the ultimate football simulation!', 10.0, 'Cityville', 'Stateville', '123 Main St', '12345', 1);

INSERT INTO CUSTOMER (ID, NAME, EMAIL, PASSWORD) VALUES (2, 'John Doe', 'john@example.com', 'john_password')
INSERT INTO CUSTOMER (ID, NAME, EMAIL, PASSWORD) VALUES (3, 'Jane Doe', 'jane@example.com', 'jane_password')
INSERT INTO CUSTOMER (ID, NAME, EMAIL, PASSWORD) VALUES (4, 'Alice Johnson', 'alice@example.com', 'alice_password')
INSERT INTO CUSTOMER (ID, NAME, EMAIL, PASSWORD) VALUES (5, 'Bob Smith', 'bob@example.com', 'bob_password')

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
