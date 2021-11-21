DROP TABLE IF EXISTS `game` CASCADE;
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