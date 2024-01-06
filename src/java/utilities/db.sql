INSERT INTO CREDENTIALS (ID, USERNAME, PASSWORD) VALUES (1, 'sob', 'sob')

INSERT INTO CONSOLE (ID, NAME) VALUES (1, 'PlayStation 5')
INSERT INTO CONSOLE (ID, NAME) VALUES (2, 'Xbox Series X')
INSERT INTO CONSOLE (ID, NAME) VALUES (3, 'Nintendo Switch')
INSERT INTO CONSOLE (ID, NAME) VALUES (4, 'PC Gaming Master Race')

INSERT INTO GAMETYPE (ID, NAME) VALUES (1, 'Action/Adventure')
INSERT INTO GAMETYPE (ID, NAME) VALUES (2, 'First Person Shooter')
INSERT INTO GAMETYPE (ID, NAME) VALUES (3, 'Role-Playing Game')
INSERT INTO GAMETYPE (ID, NAME) VALUES (4, 'Sports')

ALTER TABLE GAME MODIFY COLUMN DESCRIPTION VARCHAR(2048);
INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID) VALUES (2, 'Super Mario Odyssey', 2, 20, 'Embark on a whimsical adventure with Mario and his new companion, Cappy, in "Super Mario Odyssey." Travel across captivating kingdoms aboard the Odyssey airship, exploring diverse landscapes, encountering quirky characters, and collecting Power Moons to rescue Princess Peach from Bowsers clutches. Utilize Cappys unique abilities to possess objects and enemies, unveiling hidden secrets and overcoming challenges. With its charming narrative, imaginative worlds, and innovative gameplay mechanics, Super Mario Odyssey is a delightful journey through the magical and colorful Mushroom Kingdom.', 10.0, 'Cityville', 'Stateville', '123 Main St', '12345', 3)
INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID) VALUES (3, 'Call of Duty: Modern Warfare' , 3, 15, 'Immerse yourself in the intense and gripping narrative of global conflict in "Call of Duty: Modern Warfare 3." As a part of an elite military unit, players navigate through a world on the brink of destruction, facing terrorism and political turmoil. The game features a compelling single-player campaign, with heart-pounding action and dramatic storytelling. Additionally, experience the adrenaline-pumping multiplayer mode, offering an array of maps, weapons, and game modes for competitive online play. Modern Warfare 3 delivers an immersive and cinematic gaming experience within the renowned Call of Duty franchise.', 10.0, 'Cityville', 'Stateville', '123 Main St', '12345', 1)
INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID) VALUES (4, 'The Witcher 3: Wild Hunt', 4, 10, 'Step into the vast and immersive open world of The Witcher 3: Wild Hunt, where you assume the role of Geralt of Rivia, a monster hunter for hire. Embark on a quest to find Geralts adopted daughter, Ciri, while navigating a morally gray universe filled with political intrigue, mythical creatures, and complex characters. The game boasts a rich narrative, player-driven choices, and a living, breathing world that reacts to your decisions. With its breathtaking visuals, dynamic storytelling, and deep role-playing elements, The Witcher 3 stands as a pinnacle in the action RPG genre.', 10.0, 'Townsville', 'Provinceville', '456 Oak St', '67890', 2)
INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID) VALUES (5, 'FIFA 22', 5, 30, 'Experience the thrill of the beautiful game with FIFA 22. The latest installment in the renowned football simulation franchise brings enhanced realism, improved player AI, and innovative features. Dive into the revamped Career Mode, where you can manage every aspect of your teams journey to success. Feel the pulse-pounding action on the pitch with realistic ball physics, precise player movements, and dynamic match atmospheres. With new gameplay mechanics and updated rosters, FIFA 22 continues to capture the essence of football and offers an unparalleled gaming experience for both casual and hardcore fans.' , 10.0, 'Townsville', 'Provinceville', '456 Oak St', '67890', 4)
INSERT INTO GAME (ID, NAME, IMAGE, STOCK, DESCRIPTION, PRICE, CITY, STATE, STREET, ZIP_CODE, CONSOLE_ID) VALUES (6, 'FIFA 23', 6, 0, 'Anticipate the next evolution of football gaming with FIFA 23. Building on the success of its predecessor, FIFA 23 introduces groundbreaking features, cutting-edge graphics, and refined gameplay mechanics. Immerse yourself in the drama of each match with improved ball physics, lifelike player animations, and realistic stadium environments. Explore fresh modes and enhanced career options, shaping your football legacy as a manager or player. With its commitment to authenticity and innovation, FIFA 23 promises to deliver an exhilarating and immersive football experience for fans worldwide.', 10.0, 'Cityville', 'Stateville', '123 Main St', '12345', 1)

INSERT INTO CUSTOMER (ID, USERNAME, EMAIL, PASSWORD) VALUES (1, 'John Doe', 'john@example.com', 'john_password')
INSERT INTO CUSTOMER (ID, USERNAME, EMAIL, PASSWORD) VALUES (2, 'Jane Doe', 'jane@example.com', 'jane_password')
INSERT INTO CUSTOMER (ID, USERNAME, EMAIL, PASSWORD) VALUES (3, 'Alice Johnson', 'alice@example.com', 'alice_password')
INSERT INTO CUSTOMER (ID, USERNAME, EMAIL, PASSWORD) VALUES (4, 'Bob Smith', 'bob@example.com', 'bob_password')

INSERT INTO GAME_GAMETYPE(GAMES_ID, TYPES_ID) VALUES (2,1)
INSERT INTO GAME_GAMETYPE(GAMES_ID, TYPES_ID) VALUES (3,1)
INSERT INTO GAME_GAMETYPE(GAMES_ID, TYPES_ID) VALUES (3,2)
INSERT INTO GAME_GAMETYPE(GAMES_ID, TYPES_ID) VALUES (4,1)
INSERT INTO GAME_GAMETYPE(GAMES_ID, TYPES_ID) VALUES (4,3)
INSERT INTO GAME_GAMETYPE(GAMES_ID, TYPES_ID) VALUES (5,4)
INSERT INTO GAME_GAMETYPE(GAMES_ID, TYPES_ID) VALUES (6,4)
