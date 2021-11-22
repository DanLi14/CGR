DROP TABLE IF EXISTS `game` CASCADE;
DROP TABLE IF EXISTS `review` CASCADE;
CREATE TABLE `game`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(80) NOT NULL UNIQUE,
    `descript` VARCHAR(400) DEFAULT NULL,
    `pc` BOOLEAN NOT NULL,
    `playstation` BOOLEAN NOT NULL,
	`xbox` BOOLEAN NOT NULL,
    `nintendo` BOOLEAN NOT NULL,
    `image` VARCHAR(600) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `review` (
    `id` INT AUTO_INCREMENT,
    `username` VARCHAR(40) NOT NULL, 
    `score`INT,
    `review` VARCHAR(800) NOT NULL, 
    `fk_game_id` INT,
    CHECK (score>=1 AND score<=10),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`fk_game_id`) REFERENCES game(id)
);