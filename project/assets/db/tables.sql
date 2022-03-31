-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema login_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema login_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `login_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `login_db` ;

-- -----------------------------------------------------
-- Table `login_db`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `login_db`.`users` (
                                                  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                  `enabled` INT NOT NULL,
                                                  `password` VARCHAR(255) NOT NULL,
    `username` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `UK_r43af9ap4edm43mmtq01oddj6` (`username` ASC) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 6
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `login_db`.`requests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `login_db`.`requests` (
                                                     `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                     `purpose` VARCHAR(45) NOT NULL,
    `status` VARCHAR(45) NOT NULL,
    `receiver_id` BIGINT NOT NULL,
    `sender_id` BIGINT NOT NULL,
    `timestamp` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `FK8kh2eaehckhr55seyhe5k7vdy` (`receiver_id` ASC) VISIBLE,
    INDEX `FKg1js12lxokyqtj936eqv1mvmx` (`sender_id` ASC) VISIBLE,
    CONSTRAINT `FK8kh2eaehckhr55seyhe5k7vdy`
    FOREIGN KEY (`receiver_id`)
    REFERENCES `login_db`.`users` (`user_id`),
    CONSTRAINT `FKg1js12lxokyqtj936eqv1mvmx`
    FOREIGN KEY (`sender_id`)
    REFERENCES `login_db`.`users` (`user_id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 22
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `login_db`.`lessons`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `login_db`.`lessons` (
                                                    `lesson_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                    `grade` VARCHAR(45) NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `semester` INT NOT NULL,
    `requests_id` BIGINT NOT NULL,
    PRIMARY KEY (`lesson_id`),
    INDEX `FKksjm8u46p73j5mskyy61c5t93` (`requests_id` ASC) VISIBLE,
    CONSTRAINT `FKksjm8u46p73j5mskyy61c5t93`
    FOREIGN KEY (`requests_id`)
    REFERENCES `login_db`.`requests` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 11
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `login_db`.`recommendation_letter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `login_db`.`recommendation_letter` (
                                                                  `letter_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                                  `text` TEXT NOT NULL,
                                                                  `requests_id` BIGINT NOT NULL,
                                                                  `timestamp` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`letter_id`),
    INDEX `fk_recommendationLetter_requests1_idx` (`requests_id` ASC) VISIBLE,
    CONSTRAINT `fk_recommendationLetter_requests1`
    FOREIGN KEY (`requests_id`)
    REFERENCES `login_db`.`requests` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `login_db`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `login_db`.`roles` (
                                                  `role_id` INT NOT NULL AUTO_INCREMENT,
                                                  `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`role_id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 4
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `login_db`.`users_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `login_db`.`users_roles` (
                                                        `user_id` BIGINT NOT NULL,
                                                        `role_id` INT NOT NULL,
                                                        PRIMARY KEY (`user_id`, `role_id`),
    INDEX `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id` ASC) VISIBLE,
    CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa`
    FOREIGN KEY (`user_id`)
    REFERENCES `login_db`.`users` (`user_id`),
    CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy`
    FOREIGN KEY (`role_id`)
    REFERENCES `login_db`.`roles` (`role_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

use login_db;
insert into users (user_id,username,password,enabled) values ('1','admin@gmail.com','$2a$12$jsvf6S4wD3MexUJDdFaaKOJDNtaMn57BICEQ65y7uxX8fBP/gIPe.',1);
insert into users (user_id,username,password,enabled) values ('2','student@gmail.com','$2a$12$wmpePALL618K9K1PnsQw9u12zBkzb2namL60yRAmCB3hPlnChTptu',1);
insert into users (user_id,username,password,enabled) values ('3','teacher@gmail.com','$2a$12$jsvf6S4wD3MexUJDdFaaKOJDNtaMn57BICEQ65y7uxX8fBP/gIPe.',1);

insert into roles(role_id,name) values ('1','ADMIN');
insert into roles (role_id,name) values ('2','STUDENT');
insert into roles (role_id,name) values ('3','TEACHER');

insert into users_roles values('1','1');
insert into users_roles values('2','2');
insert into users_roles values('3','3');