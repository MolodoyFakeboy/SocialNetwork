SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Hotel
-- -----------------------------------------------------

CREATE SCHEMA IF NOT EXISTS `Hotel` DEFAULT CHARACTER SET utf8 ;
USE `Hotel` ;

-- -----------------------------------------------------
-- Table `Hotel`.`Guest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotel`.`Guest` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `surname` VARCHAR(30) NOT NULL,
  `PhoneNumber` VARCHAR(20) NOT NULL,
  `LocalDate` TIMESTAMP NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotel`.`Room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotel`.`Room` (
  `roomID` INT NOT NULL AUTO_INCREMENT,
  `roomNumber` SMALLINT NOT NULL,
  `numberOfStars` TINYINT NOT NULL,
  `floor` TINYINT NOT NULL,
  `status` ENUM('BOOK_ROOM', 'FREE_ROOM', 'ROOM_CLEANING') NULL DEFAULT NULL,
  `numBed` TINYINT NOT NULL,
  `basePrice` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`roomID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotel`.`Service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotel`.`Service` (
  `idService` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `description` TINYTEXT NOT NULL,
  `date` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`idService`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Hotel`.`Guest_has_Service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotel`.`Guest_has_Service` (
  `Guest_id` INT NOT NULL,
  `Service_idService` INT NOT NULL,
  PRIMARY KEY (`Guest_id`, `Service_idService`),
  INDEX `fk_Guest_has_Service_Service1_idx` (`Service_idService` ASC) VISIBLE,
  INDEX `fk_Guest_has_Service_Guest_idx` (`Guest_id` ASC) VISIBLE,
  CONSTRAINT `fk_Guest_has_Service_Guest`
    FOREIGN KEY (`Guest_id`)
    REFERENCES `Hotel`.`Guest` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Guest_has_Service_Service1`
    FOREIGN KEY (`Service_idService`)
    REFERENCES `Hotel`.`Service` (`idService`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotel`.`Guest_has_Room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotel`.`Guest_has_Room` (
  `Guest_id` INT NOT NULL,
  `Room_roomID` INT NOT NULL,
  PRIMARY KEY (`Guest_id`, `Room_roomID`),
  INDEX `fk_Guest_has_Room_Room1_idx` (`Room_roomID` ASC) VISIBLE,
  INDEX `fk_Guest_has_Room_Guest1_idx` (`Guest_id` ASC) VISIBLE,
  CONSTRAINT `fk_Guest_has_Room_Guest1`
    FOREIGN KEY (`Guest_id`)
    REFERENCES `Hotel`.`Guest` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Guest_has_Room_Room1`
    FOREIGN KEY (`Room_roomID`)
    REFERENCES `Hotel`.`Room` (`roomID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (1, 101, 2, 3, 3, 8584);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (2, 102, 3, 3, 2, 4667);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (3, 103, 4, 2, 1, 3059);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (4, 104, 5, 2, 2, 3969);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (5, 105, 2, 2, 6, 5396);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (6, 106, 3, 1, 4, 7594);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (7, 107, 4, 1, 2, 7094);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (8, 108, 2, 3, 4, 3756);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (9, 109, 5, 1, 1, 7679);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (10, 110, 5, 4, 6, 8765);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (11, 111, 2, 1, 3, 5170);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (12, 112, 4, 3, 3, 4583);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (13, 113, 4, 2, 2, 4644);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (14, 114, 5, 4, 3, 4386);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (15, 115, 2, 3, 3, 6848);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (16, 116, 4, 1, 6, 4168);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (17, 117, 5, 4, 3, 7225);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (18, 118, 4, 3, 4, 5163);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (19, 119, 5, 1, 2, 2290);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (20, 120, 3, 3, 2, 5935);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (21, 121, 3, 3, 1, 9258);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (22, 122, 4, 2, 4, 3791);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (23, 123, 4, 3, 1, 5757);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (24, 124, 4, 3, 3, 5534);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (25, 125, 5, 3, 4, 4794);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (26, 126, 2, 1, 6, 5205);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (27, 127, 2, 3, 6, 4032);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (28, 128, 4, 1, 6, 4535);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (29, 129, 4, 3, 6, 8128);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (30, 130, 5, 2, 3, 2559);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (31, 131, 3, 1, 3, 2216);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (32, 132, 3, 1, 2, 5391);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (33, 133, 2, 4, 2, 2663);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (34, 134, 4, 4, 2, 5158);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (35, 135, 3, 4, 4, 4680);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (36, 136, 4, 4, 2, 4799);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (37, 137, 3, 4, 3, 6759);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (38, 138, 5, 4, 6, 4861);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (39, 139, 2, 4, 6, 7352);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (40, 140, 5, 2, 3, 1728);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (41, 141, 4, 1, 4, 5925);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (42, 142, 5, 1, 4, 1734);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (43, 143, 3, 2, 3, 1678);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (44, 144, 3, 4, 3, 4748);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (45, 145, 3, 1, 3, 4364);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (46, 146, 5, 2, 1, 3697);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (47, 147, 2, 1, 6, 2055);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (48, 148, 4, 4, 3, 8281);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (49, 149, 4, 3, 1, 9445);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (50, 150, 3, 1, 4, 9024);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (51, 151, 2, 1, 2, 7785);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (52, 152, 4, 1, 1, 6610);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (53, 153, 3, 4, 3, 5815);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (54, 154, 3, 4, 4, 5532);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (55, 155, 4, 3, 4, 6427);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (56, 156, 3, 3, 3, 4878);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (57, 157, 2, 4, 1, 3454);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (58, 158, 4, 3, 3, 2783);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (59, 159, 4, 1, 6, 3858);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (60, 160, 4, 2, 3, 5921);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (61, 161, 4, 2, 4, 9274);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (62, 162, 2, 2, 2, 7109);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (63, 163, 5, 4, 6, 9821);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (64, 164, 3, 2, 3, 5091);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (65, 165, 3, 1, 3, 3833);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (66, 166, 4, 1, 4, 9961);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (67, 167, 4, 3, 3, 8131);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (68, 168, 5, 3, 6, 5135);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (69, 169, 4, 1, 4, 9292);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (70, 170, 2, 2, 1, 5345);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (71, 171, 3, 4, 6, 5302);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (72, 172, 4, 4, 6, 2101);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (73, 173, 4, 4, 3, 3938);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (74, 174, 2, 1, 1, 5696);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (75, 175, 4, 1, 1, 3093);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (76, 176, 4, 1, 2, 2078);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (77, 177, 3, 4, 3, 5204);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (78, 178, 4, 1, 2, 7144);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (79, 179, 2, 1, 1, 1759);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (80, 180, 3, 3, 2, 9473);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (81, 181, 2, 2, 1, 9796);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (82, 182, 2, 3, 1, 8473);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (83, 183, 3, 2, 6, 2052);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (84, 184, 4, 1, 6, 9135);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (85, 185, 4, 4, 1, 6267);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (86, 186, 3, 3, 2, 7591);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (87, 187, 2, 2, 6, 8260);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (88, 188, 5, 2, 3, 6113);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (89, 189, 5, 4, 3, 9966);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (90, 190, 5, 1, 6, 8066);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (91, 191, 5, 4, 1, 1672);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (92, 192, 5, 2, 3, 4162);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (93, 193, 5, 2, 2, 9048);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (94, 194, 4, 4, 3, 2982);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (95, 195, 2, 4, 4, 5320);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (96, 196, 3, 2, 6, 8377);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (97, 197, 3, 2, 3, 6961);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (98, 198, 3, 3, 4, 4328);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (99, 199, 2, 3, 4, 6676);
insert into Room (RoomID, roomNumber, numberOfStars, floor, numBed, basePrice) values (100, 200, 3, 2, 6, 9123);

insert into Service (idService, name, price, description) values (1, 'Кофе в номер', 300, 'Самое вкусное кофе!');
insert into Service (idService, name, price, description) values (2, 'поменять полотенце', 100, 'Будь всегда чистым');
insert into Service (idService, name, price, description) values (3, 'напитки в бар ', 655, 'Кора-рора в вашем баре');

insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (1, 'Shepperd', 'Dreakin', '931-584-7251', '2021-05-24 00:00:00', 1, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (2, 'Lorilee', 'Keenlyside', '821-558-7795', '2021-05-24 00:00:00', 2, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (3, 'Eduardo', 'Cliff', '720-840-8043', '2021-05-24 00:00:00', 3, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (4, 'Noell', 'Hartfield', '624-561-2227', '2021-05-24 00:00:00', 4, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (5, 'Tomi', 'Parnell', '343-643-8882', '2021-05-24 00:00:00', 5, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (6, 'Kerrie', 'Kohnert', '865-103-7434', '2021-05-24 00:00:00', 6, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (7, 'Waylen', 'Wyldes', '386-388-6655', '2021-05-24 00:00:00', 7, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (8, 'Doralin', 'Kinze', '253-905-9299', '2021-05-24 00:00:00', 8, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (9, 'Jory', 'Jeandel', '968-998-4753', '2021-05-24 00:00:00', 9, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (10, 'Karil', 'Keattch', '191-596-0325', '2021-05-24 00:00:00', 10, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (11, 'Carney', 'Marzelo', '199-656-8235', '2021-05-24 00:00:00', 11, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (12, 'Elisha', 'McClary', '609-436-0787', '2021-05-24 00:00:00', 12, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (13, 'Chico', 'Mortimer', '865-804-5065', '2021-05-24 00:00:00', 13, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (14, 'Lucretia', 'Putten', '288-119-4953', '2021-05-24 00:00:00', 14, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (15, 'Savina', 'Elizabeth', '380-650-9223', '2021-05-24 00:00:00', 15, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (16, 'Lucien', 'Kirtley', '169-206-3739', '2021-05-24 00:00:00', 16, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (17, 'Cass', 'Tupling', '166-159-3684', '2021-05-24 00:00:00', 17, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (18, 'Moselle', 'Yellowlees', '801-160-7258', '2021-05-24 00:00:00', 18, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (19, 'Perry', 'Walcot', '867-801-0564', '2021-05-24 00:00:00', 19, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (20, 'Shena', 'Jowle', '999-163-3756', '2021-05-24 00:00:00', 20, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (21, 'Amelia', 'Stappard', '507-346-9699', '2021-05-24 00:00:00', 21, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (22, 'Zaccaria', 'Hollindale', '583-266-8506', '2021-05-24 00:00:00', 22, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (23, 'Roslyn', 'Clayson', '826-745-2298', '2021-05-24 00:00:00', 23, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (24, 'Dominick', 'Greenard', '196-537-9343', '2021-05-24 00:00:00', 24, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (25, 'Ealasaid', 'Manhood', '389-174-3812', '2021-05-24 00:00:00', 25, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (26, 'Nadya', 'Englefield', '796-336-1046', '2021-05-24 00:00:00', 26, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (27, 'Bette', 'Bilbrooke', '904-588-8965', '2021-05-24 00:00:00', 27, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (28, 'Myrle', 'Fugere', '604-530-2979', '2021-05-24 00:00:00', 28, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (29, 'Shannah', 'Kabos', '427-505-9149', '2021-05-24 00:00:00', 29, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (30, 'Ruddie', 'Brede', '741-453-7911', '2021-05-24 00:00:00', 30, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (31, 'Jodie', 'Rivelin', '121-350-2934', '2021-05-24 00:00:00', 31, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (32, 'Diahann', 'Pobjoy', '883-619-5754', '2021-05-24 00:00:00', 32, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (33, 'Corabella', 'Spaven', '504-988-0601', '2021-05-24 00:00:00', 33, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (34, 'Walliw', 'Celler', '960-641-4715', '2021-05-24 00:00:00', 34, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (35, 'Artur', 'Deetlefs', '984-839-7567', '2021-05-24 00:00:00', 35, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (36, 'Seana', 'Simonaitis', '705-594-0926', '2021-05-24 00:00:00', 36, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (37, 'Mauricio', 'Beard', '938-656-8702', '2021-05-24 00:00:00', 37, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (38, 'Selle', 'Neylon', '402-446-3247', '2021-05-24 00:00:00', 38, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (39, 'Teddi', 'Sawer', '471-275-5352', '2021-05-24 00:00:00', 39, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (40, 'Sebastiano', 'Whitington', '641-800-7626', '2021-05-24 00:00:00', 40, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (41, 'Emili', 'Fellgatt', '697-289-1732', '2021-05-24 00:00:00', 41, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (42, 'Alric', 'Ashley', '312-609-9751', '2021-05-24 00:00:00', 42, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (43, 'Dennison', 'Jouannin', '958-429-9278', '2021-05-24 00:00:00', 43, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (44, 'Cristie', 'Grinyakin', '958-926-3601', '2021-05-24 00:00:00', 44, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (45, 'Tessa', 'Boutell', '859-490-4645', '2021-05-24 00:00:00', 45, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (46, 'Rudyard', 'Durn', '114-915-2998', '2021-05-24 00:00:00', 46, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (47, 'Borden', 'Pallent', '446-837-1618', '2021-05-24 00:00:00', 47, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (48, 'Emalee', 'Nelane', '957-520-5610', '2021-05-24 00:00:00', 48, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (49, 'George', 'Jory', '638-410-8722', '2021-05-24 00:00:00', 49, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (50, 'Charlot', 'Rambadt', '887-413-5254', '2021-05-24 00:00:00', 50, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (51, 'Lawton', 'Boyland', '602-932-0834', '2021-05-24 00:00:00', 51, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (52, 'Sal', 'Hallsworth', '694-968-2738', '2021-05-24 00:00:00', 52, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (53, 'Mendie', 'Sales', '229-234-3964', '2021-05-24 00:00:00', 53, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (54, 'Fraser', 'Helwig', '580-765-6785', '2021-05-24 00:00:00', 54, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (55, 'Kittie', 'Charity', '149-671-2769', '2021-05-24 00:00:00', 55, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (56, 'Sindee', 'D''Onise', '460-516-1651', '2021-05-24 00:00:00', 56, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (57, 'Linet', 'Pipes', '372-765-0096', '2021-05-24 00:00:00', 57, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (58, 'Aurora', 'MacFadzean', '530-848-1453', '2021-05-24 00:00:00', 58, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (59, 'Fleur', 'Rosenvasser', '412-877-5816', '2021-05-24 00:00:00', 59, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (60, 'Johan', 'Daouze', '187-254-7358', '2021-05-24 00:00:00', 60, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (61, 'Wilona', 'Drivers', '904-241-6269', '2021-05-24 00:00:00', 61, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (62, 'Eran', 'Gorham', '110-345-7612', '2021-05-24 00:00:00', 62, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (63, 'Ezequiel', 'Lemmen', '331-507-8385', '2021-05-24 00:00:00', 63, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (64, 'Marrissa', 'Di Boldi', '379-872-7977', '2021-05-24 00:00:00', 64, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (65, 'Nanete', 'Kilfeder', '769-996-9621', '2021-05-24 00:00:00', 65, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (66, 'Alayne', 'Lorraway', '588-801-3573', '2021-05-24 00:00:00', 66, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (67, 'Grayce', 'Sainsbury-Brown', '669-220-6077', '2021-05-24 00:00:00', 67, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (68, 'Herbie', 'Siburn', '236-599-9895', '2021-05-24 00:00:00', 68, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (69, 'Katina', 'Moizer', '517-159-3714', '2021-05-24 00:00:00', 69, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (70, 'Joby', 'Hailes', '793-131-4364', '2021-05-24 00:00:00', 70, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (71, 'Maurise', 'Caldero', '267-353-5724', '2021-05-24 00:00:00', 71, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (72, 'Sutton', 'Pennuzzi', '591-661-8340', '2021-05-24 00:00:00', 72, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (73, 'Doy', 'Weatherburn', '898-766-8614', '2021-05-24 00:00:00', 73, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (74, 'Milena', 'Bruffell', '362-683-2808', '2021-05-24 00:00:00', 74, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (75, 'Corinna', 'Haggerty', '409-481-2326', '2021-05-24 00:00:00', 75, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (76, 'Delmor', 'Halladey', '777-147-6740', '2021-05-24 00:00:00', 76, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (77, 'Gram', 'Elliston', '803-614-2735', '2021-05-24 00:00:00', 77, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (78, 'Emilio', 'Weich', '806-640-0531', '2021-05-24 00:00:00', 78, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (79, 'Torin', 'O''Loinn', '881-620-6553', '2021-05-24 00:00:00', 79, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (80, 'Emalee', 'Sackur', '593-433-2978', '2021-05-24 00:00:00', 80, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (81, 'Mada', 'Reskelly', '478-545-4959', '2021-05-24 00:00:00', 81, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (82, 'Claudius', 'Bortoletti', '139-564-6459', '2021-05-24 00:00:00', 82, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (83, 'Gabriel', 'Elce', '952-231-4484', '2021-05-24 00:00:00', 83, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (84, 'Yuma', 'Ondrusek', '276-501-7056', '2021-05-24 00:00:00', 84, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (85, 'Whitaker', 'Wickey', '323-183-9812', '2021-05-24 00:00:00', 85, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (86, 'Julianna', 'Cockhill', '809-799-5434', '2021-05-24 00:00:00', 86, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (87, 'Eddy', 'Lownes', '109-479-0042', '2021-05-24 00:00:00', 87, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (88, 'Mahalia', 'Faveryear', '143-371-2379', '2021-05-24 00:00:00', 88, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (89, 'Dom', 'Whiteland', '596-883-8009', '2021-05-24 00:00:00', 89, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (90, 'Emili', 'Baldcock', '402-777-1866', '2021-05-24 00:00:00', 90, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (91, 'Sonny', 'Gallacher', '264-670-9128', '2021-05-24 00:00:00', 91, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (92, 'Cloe', 'Mauro', '503-197-4946', '2021-05-24 00:00:00', 92, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (93, 'Ricca', 'Laven', '217-898-6505', '2021-05-24 00:00:00', 93, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (94, 'Georas', 'Strickett', '970-853-9192', '2021-05-24 00:00:00', 94, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (95, 'Coletta', 'Windridge', '425-663-9274', '2021-05-24 00:00:00', 95, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (96, 'Joli', 'Dunston', '573-556-1232', '2021-05-24 00:00:00', 96, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (97, 'Anthe', 'Mynett', '828-548-8097', '2021-05-24 00:00:00', 97, 1);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (98, 'Stephani', 'Putman', '556-133-0074', '2021-05-24 00:00:00', 98, 3);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (99, 'Chrisse', 'Ortet', '719-143-1873', '2021-05-24 00:00:00', 99, 2);
insert into Guest (id, name, surname, PhoneNumber, LocalDate, Room_roomID, Service_idService) values (100, 'Tobiah', 'Gusney', '746-477-4901', '2021-05-24 00:00:00', 100, 1);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;