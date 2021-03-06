SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema NetworkSocial
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema NetworkSocial
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `NetworkSocial` DEFAULT CHARACTER SET utf8 ;
USE `NetworkSocial` ;

-- -----------------------------------------------------
-- Table `NetworkSocial`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NetworkSocial`.`Role` (
  `idRole` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idRole`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetworkSocial`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NetworkSocial`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `birthday` DATE NOT NULL,
  `Role_idRole` INT NOT NULL,
  `Bio` TEXT NULL,
  PRIMARY KEY (`idUser`, `Role_idRole`),
  INDEX `fk_User_Role1_idx` (`Role_idRole` ASC) VISIBLE,
  CONSTRAINT `fk_User_Role1`
    FOREIGN KEY (`Role_idRole`)
    REFERENCES `NetworkSocial`.`Role` (`idRole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetworkSocial`.`Post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NetworkSocial`.`Post` (
  `idPost` INT NOT NULL AUTO_INCREMENT,
  `createdDate` DATETIME NOT NULL,
  `title` TEXT NOT NULL,
  `User_idUser` INT NOT NULL,
  PRIMARY KEY (`idPost`, `User_idUser`),
  INDEX `fk_Post_User1_idx` (`User_idUser` ASC) VISIBLE,
  CONSTRAINT `fk_Post_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `NetworkSocial`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetworkSocial`.`Publication`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NetworkSocial`.`Publication` (
  `idPublication` INT NOT NULL AUTO_INCREMENT,
  `createTime` DATETIME NOT NULL,
  `Information` TEXT NOT NULL,
  PRIMARY KEY (`idPublication`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetworkSocial`.`Image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NetworkSocial`.`Image` (
  `idImage` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Photo` BLOB NOT NULL,
  `Publication_idPublication` INT NOT NULL,
  PRIMARY KEY (`idImage`, `Publication_idPublication`),
  INDEX `fk_Image_Publication1_idx` (`Publication_idPublication` ASC) VISIBLE,
  CONSTRAINT `fk_Image_Publication1`
    FOREIGN KEY (`Publication_idPublication`)
    REFERENCES `NetworkSocial`.`Publication` (`idPublication`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetworkSocial`.`Friends`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NetworkSocial`.`Friends` (
  `User_idUser` INT NOT NULL,
  `User_idFrend` INT NOT NULL,
  PRIMARY KEY (`User_idUser`, `User_idFrend`),
  INDEX `fk_User_has_User_User2_idx` (`User_idFrend` ASC) VISIBLE,
  INDEX `fk_User_has_User_User1_idx` (`User_idUser` ASC) VISIBLE,
  CONSTRAINT `fk_User_has_User_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `NetworkSocial`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_User_User2`
    FOREIGN KEY (`User_idFrend`)
    REFERENCES `NetworkSocial`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetworkSocial`.`Public`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NetworkSocial`.`Public` (
  `publicID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  `number_subscribers` INT NULL,
  PRIMARY KEY (`publicID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetworkSocial`.`Chat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NetworkSocial`.`Chat` (
  `idChat` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idChat`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetworkSocial`.`Message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NetworkSocial`.`Message` (
  `idMessage` INT NOT NULL AUTO_INCREMENT,
  `SendText` TEXT NOT NULL,
  `SendTime` TIMESTAMP NOT NULL,
  `Chat_idChat` INT NOT NULL,
  `User_idUser` INT NOT NULL,
  PRIMARY KEY (`idMessage`, `Chat_idChat`, `User_idUser`),
  INDEX `fk_Message_Chat1_idx` (`Chat_idChat` ASC) VISIBLE,
  INDEX `fk_Message_User1_idx` (`User_idUser` ASC) VISIBLE,
  CONSTRAINT `fk_Message_Chat1`
    FOREIGN KEY (`Chat_idChat`)
    REFERENCES `NetworkSocial`.`Chat` (`idChat`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Message_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `NetworkSocial`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetworkSocial`.`User_has_Chat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NetworkSocial`.`User_has_Chat` (
  `User_idUser` INT NOT NULL,
  `Chat_idChat` INT NOT NULL,
  PRIMARY KEY (`User_idUser`, `Chat_idChat`),
  INDEX `fk_User_has_Chat_Chat1_idx` (`Chat_idChat` ASC) VISIBLE,
  INDEX `fk_User_has_Chat_User1_idx` (`User_idUser` ASC) VISIBLE,
  CONSTRAINT `fk_User_has_Chat_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `NetworkSocial`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Chat_Chat1`
    FOREIGN KEY (`Chat_idChat`)
    REFERENCES `NetworkSocial`.`Chat` (`idChat`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetworkSocial`.`Group_has_Publication`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NetworkSocial`.`Group_has_Publication` (
  `Group_idGroup` INT NOT NULL,
  `Publication_idPublication` INT NOT NULL,
  PRIMARY KEY (`Group_idGroup`, `Publication_idPublication`),
  INDEX `fk_Group_has_Publication_Publication1_idx` (`Publication_idPublication` ASC) VISIBLE,
  INDEX `fk_Group_has_Publication_Group1_idx` (`Group_idGroup` ASC) VISIBLE,
  CONSTRAINT `fk_Group_has_Publication_Group1`
    FOREIGN KEY (`Group_idGroup`)
    REFERENCES `NetworkSocial`.`Public` (`publicID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Group_has_Publication_Publication1`
    FOREIGN KEY (`Publication_idPublication`)
    REFERENCES `NetworkSocial`.`Publication` (`idPublication`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetworkSocial`.`User_has_Public`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NetworkSocial`.`User_has_Public` (
  `User_idUser` INT NOT NULL,
  `Public_publicID` INT NOT NULL,
  PRIMARY KEY (`User_idUser`, `Public_publicID`),
  INDEX `fk_User_has_Public_Public1_idx` (`Public_publicID` ASC) VISIBLE,
  INDEX `fk_User_has_Public_User1_idx` (`User_idUser` ASC) VISIBLE,
  CONSTRAINT `fk_User_has_Public_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `NetworkSocial`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Public_Public1`
    FOREIGN KEY (`Public_publicID`)
    REFERENCES `NetworkSocial`.`Public` (`publicID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NetworkSocial`.`Comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NetworkSocial`.`Comment` (
  `idComment` INT NOT NULL AUTO_INCREMENT,
  `Message` TEXT NOT NULL,
  `createTime` DATETIME NOT NULL,
  `User_idUser` INT NOT NULL,
  `Publication_idPublication` INT NOT NULL,
  PRIMARY KEY (`idComment`, `Publication_idPublication`, `User_idUser`),
  INDEX `fk_Comment_User1_idx` (`User_idUser` ASC) VISIBLE,
  INDEX `fk_Comment_Publication1_idx` (`Publication_idPublication` ASC) VISIBLE,
  CONSTRAINT `fk_Comment_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `NetworkSocial`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_Publication1`
    FOREIGN KEY (`Publication_idPublication`)
    REFERENCES `NetworkSocial`.`Publication` (`idPublication`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

insert into Role (idRole, Name) values (1, 'ROLE_USER');
insert into Role (idRole, Name) values (2, 'ROLE_ADMIN');

insert into User (idUser, username, password, email, birthday, Role_idRole) values (1, 'Zahar', '$2a$12$HKP5wxGLlJCwYbamLy.Fzeyh3YrCsxFZzDBTmZd0CHy7V0JNZ7pm.', 'zahar@gmail.com', '2001-04-26', 2);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (2, 'Betteanne', '4neNNC', 'bfirmin1@friendfeed.com', '2001-11-09', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (3, 'Meade', 'bpO1rNisr', 'mburchard2@issuu.com', '2000-11-12', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (4, 'Dorolice', 'RlJb9woVv', 'djaquiss3@shareasale.com', '2002-05-04', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (5, 'Todd', '97GScD', 'tbastable4@sitemeter.com', '2001-08-24', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (6, 'Shep', '6UJL7Qldmt', 'sradband5@google.ca', '2000-10-14', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (7, 'Elsbeth', 'pDteHC', 'ecovet6@fema.gov', '2000-05-30', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (8, 'Lorelle', 'XNrQLI4', 'lfirks7@kickstarter.com', '2000-09-29', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (9, 'Stan', 'hbfMcz0u', 'stoolan8@weibo.com', '2000-06-27', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (10, 'Kean', 'ePvxSH', 'kaaronson9@geocities.com', '1998-04-06', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (11, 'Linzy', '9O3wN2pRIoH', 'loreillya@taobao.com', '2002-08-11', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (12, 'Romeo', 'HhrrQEToTp', 'rgilkisonb@yelp.com', '2002-03-26', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (13, 'Dorothee', 'w6jtjy', 'dwalcarc@blogs.com', '2001-01-15', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (14, 'Kizzee', 'EJkpsI', 'kbarnewelled@marriott.com', '2002-01-12', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (15, 'Anthia', '5139Wu3qjKP', 'ahueye@indiegogo.com', '1998-10-25', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (16, 'Karrah', 'ja3NcThtN', 'kfisbeyf@spotify.com', '2001-09-30', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (17, 'Kit', 'K2H3JdUN1jz', 'kranklingg@youtube.com', '1998-08-02', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (18, 'Rochella', 'CA6U7FvEY', 'rinnsh@xing.com', '1998-11-23', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (19, 'Aloysia', 'V3kB6cFwkN', 'ajammei@skyrock.com', '2002-02-17', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (20, 'Bernadina', 'IRvMnZZ6VhUD', 'bdelgadoj@alibaba.com', '2001-07-10', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (21, 'Eloisa', 'UYsRBJ', 'eberk@furl.net', '1998-04-06', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (22, 'Denny', 'RjsC55', 'dcordlel@ftc.gov', '2002-02-03', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (23, 'Harwell', 'ruFXenWSg', 'hfassanm@redcross.org', '2000-07-22', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (24, 'Rodd', 'IIYDmzlOVD7I', 'rtotmann@theglobeandmail.com', '1999-09-10', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (25, 'Erie', 'AAVqtMC', 'emainstono@facebook.com', '1999-07-07', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (26, 'Sybyl', 'dwOWSQnGK', 'slelievrep@patch.com', '1998-08-25', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (27, 'Devlen', 'OjtGtgKfdLw', 'dgoggenq@slate.com', '1999-09-30', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (28, 'Royall', '1pARm4nHlt1R', 'rpayfootr@ezinearticles.com', '2002-01-15', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (29, 'Merridie', 'OIAoFW', 'mwreaks@google.cn', '1998-09-21', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (30, 'Marilyn', 'DroNmW2dy', 'mwilcockst@theguardian.com', '1999-08-21', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (31, 'Fulvia', '6ZeTWT78wTpX', 'fgrishinovu@sun.com', '1998-11-13', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (32, 'Shell', '7ZKYy6bS9HwA', 'sreamv@house.gov', '1998-06-20', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (33, 'Mannie', 'JmTEtAAG', 'mkelceyw@uiuc.edu', '2000-08-02', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (34, 'Cobb', 'yLTMT37YtU', 'ckoopmanx@w3.org', '2001-06-25', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (35, 'Francene', 'GUtdPpD', 'fbeldeny@1und1.de', '2001-10-26', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (36, 'Felisha', '548Ib75aGRTz', 'fbygreavesz@simplemachines.org', '2001-03-19', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (37, 'Evangelina', 'XlzyJLP', 'estapele10@home.pl', '2000-11-26', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (38, 'Coletta', 'or4AqTJHVZG5', 'cjenking11@newyorker.com', '2001-09-01', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (39, 'Selena', 'LW2P2J1WI', 'swindmill12@washington.edu', '1998-10-15', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (40, 'Eddi', 'jM7dipSymt0', 'eroyall13@nih.gov', '1999-11-23', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (41, 'Meade', 'PE77TK7MXRX', 'mdarville14@dmoz.org', '2001-08-25', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (42, 'Del', 'PDCNbylUX', 'dmcreath15@boston.com', '1999-11-11', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (43, 'Gerry', 'wjCMXkO', 'gbonniface16@nbcnews.com', '1998-08-10', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (44, 'Georgena', 'x0T4Vf1ahjDu', 'gbranford17@walmart.com', '2002-06-22', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (45, 'Demetris', 'dt8Vs3', 'dbodley18@thetimes.co.uk', '1998-11-26', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (46, 'Albina', 'Ryey6KomfT', 'aleal19@bluehost.com', '1999-09-22', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (47, 'Isabeau', 'vwxdca1Zmq2', 'iguidi1a@cornell.edu', '1999-02-07', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (48, 'Quint', 'wCkl3Em', 'qshiers1b@wix.com', '1999-03-18', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (49, 'Modesty', 'wIOwetcG5jE', 'msiemens1c@ox.ac.uk', '1998-04-13', 1);
insert into User (idUser, username, password, email, birthday, Role_idRole) values (50, 'Test', '$2a$12$FgwGz6ELQqwZIfteXtLpReyQvLj.ZE4t1eoMZuc549iHDo9Wl.z.6', 'smorgen1d@dedecms.com', '2000-04-24', 2);

insert into Chat (idChat, Name) values (1, 'User 1 + User 50');

insert into User_has_Chat (User_idUser, Chat_idChat) values (1, 1);
insert into User_has_Chat (User_idUser, Chat_idChat) values (50, 1);

insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (1, '??????????????????', '2021-08-17 04:21:13', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (2, '??????????', '2021-08-17 00:10:37', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (3, '??????????????', '2021-08-17 05:48:42', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (4, '??????', '2021-08-17 12:50:42', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (5, '??????????', '2021-08-18 10:13:17', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (6, '??????', '2021-08-17 09:59:52', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (7, '??????', '2021-08-18 18:20:43', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (8, '????????????', '2021-08-17 01:10:50', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (9, '??????????', '2021-08-18 18:14:21', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (10, '??????????????????', '2021-08-17 10:42:49', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (11, '??????????', '2021-08-18 06:26:53', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (12, '???????????? ????????!', '2021-08-17 21:19:08', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (13, '?????? ?????????', '2021-08-17 14:55:58', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (14, '???????????? ????????!', '2021-08-17 09:15:03', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (15, '?????? ?????????', '2021-08-17 06:22:24', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (16, '??????????', '2021-08-17 18:17:32', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (17, '??????????????????', '2021-08-17 08:23:12', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (18, '?? ????????', '2021-08-18 07:31:02', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (19, '??????', '2021-08-18 07:11:40', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (20, '?????? ?????????', '2021-08-18 04:22:26', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (21, '??????????????', '2021-08-18 17:32:23', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (22, '?????? ?????????', '2021-08-17 16:31:23', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (23, '?? ????????', '2021-08-18 21:38:54', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (24, '???????????? ????????!', '2021-08-17 02:34:28', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (25, '?? ????????', '2021-08-18 06:11:54', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (26, '??????????', '2021-08-18 16:21:58', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (27, '????????????', '2021-08-18 09:22:53', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (28, '?? ??????????', '2021-08-17 15:37:12', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (29, '???????????? ????????!', '2021-08-17 11:29:36', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (30, '??????????', '2021-08-17 00:22:41', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (31, '?? ??????????', '2021-08-17 00:25:37', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (32, '?????? ?????????', '2021-08-17 16:29:34', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (33, '??????????', '2021-08-17 21:51:34', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (34, '??????????????????', '2021-08-17 11:17:50', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (35, '???????????? ????????!', '2021-08-18 16:53:51', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (36, '????????????', '2021-08-18 06:49:55', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (37, '??????', '2021-08-17 22:28:22', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (38, '?? ????????', '2021-08-17 23:53:34', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (39, '??????', '2021-08-18 11:06:27', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (40, '?????? ?????????', '2021-08-18 16:53:21', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (41, '?? ????????', '2021-08-17 16:53:06', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (42, '?? ????????', '2021-08-18 18:58:29', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (43, '??????????????????', '2021-08-18 17:53:14', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (44, '??????????', '2021-08-17 19:36:45', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (45, '?? ??????????', '2021-08-17 08:35:13', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (46, '??????????', '2021-08-18 11:16:10', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (47, '???????????? ????????!', '2021-08-17 00:44:24', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (48, '???????????? ????????!', '2021-08-18 05:49:03', 1, 1);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (49, '?? ??????????', '2021-08-18 07:48:38', 1, 50);
insert into Message (idMessage, SendText, SendTime, Chat_idChat, User_idUser) values (50, '????????????', '2021-08-18 16:39:06', 1, 1);

insert into Friends (User_idUser, User_idFrend) values (2, 1);
insert into Friends (User_idUser, User_idFrend) values (1, 3);
insert into Friends (User_idUser, User_idFrend) values (2, 4);
insert into Friends (User_idUser, User_idFrend) values (1, 5);
insert into Friends (User_idUser, User_idFrend) values (1, 6);
insert into Friends (User_idUser, User_idFrend) values (1, 7);
insert into Friends (User_idUser, User_idFrend) values (1, 8);
insert into Friends (User_idUser, User_idFrend) values (2, 9);
insert into Friends (User_idUser, User_idFrend) values (1, 10);
insert into Friends (User_idUser, User_idFrend) values (1, 11);
insert into Friends (User_idUser, User_idFrend) values (1, 12);
insert into Friends (User_idUser, User_idFrend) values (2, 13);
insert into Friends (User_idUser, User_idFrend) values (1, 14);
insert into Friends (User_idUser, User_idFrend) values (2, 15);
insert into Friends (User_idUser, User_idFrend) values (2, 16);
insert into Friends (User_idUser, User_idFrend) values (1, 17);
insert into Friends (User_idUser, User_idFrend) values (1, 18);
insert into Friends (User_idUser, User_idFrend) values (2, 19);
insert into Friends (User_idUser, User_idFrend) values (2, 20);
insert into Friends (User_idUser, User_idFrend) values (2, 21);
insert into Friends (User_idUser, User_idFrend) values (2, 22);
insert into Friends (User_idUser, User_idFrend) values (1, 23);
insert into Friends (User_idUser, User_idFrend) values (1, 24);
insert into Friends (User_idUser, User_idFrend) values (2, 25);
insert into Friends (User_idUser, User_idFrend) values (2, 26);
insert into Friends (User_idUser, User_idFrend) values (1, 27);
insert into Friends (User_idUser, User_idFrend) values (1, 28);
insert into Friends (User_idUser, User_idFrend) values (1, 29);
insert into Friends (User_idUser, User_idFrend) values (1, 30);
insert into Friends (User_idUser, User_idFrend) values (2, 31);
insert into Friends (User_idUser, User_idFrend) values (1, 32);
insert into Friends (User_idUser, User_idFrend) values (1, 33);
insert into Friends (User_idUser, User_idFrend) values (1, 34);
insert into Friends (User_idUser, User_idFrend) values (1, 35);
insert into Friends (User_idUser, User_idFrend) values (2, 36);
insert into Friends (User_idUser, User_idFrend) values (1, 37);
insert into Friends (User_idUser, User_idFrend) values (1, 38);
insert into Friends (User_idUser, User_idFrend) values (2, 39);
insert into Friends (User_idUser, User_idFrend) values (2, 40);
insert into Friends (User_idUser, User_idFrend) values (2, 41);
insert into Friends (User_idUser, User_idFrend) values (1, 42);
insert into Friends (User_idUser, User_idFrend) values (2, 43);
insert into Friends (User_idUser, User_idFrend) values (2, 44);
insert into Friends (User_idUser, User_idFrend) values (1, 45);
insert into Friends (User_idUser, User_idFrend) values (1, 46);
insert into Friends (User_idUser, User_idFrend) values (1, 47);
insert into Friends (User_idUser, User_idFrend) values (2, 48);
insert into Friends (User_idUser, User_idFrend) values (1, 49);
insert into Friends (User_idUser, User_idFrend) values (1, 50);
insert into Friends (User_idUser, User_idFrend) values (1, 2);

insert into Friends (User_idUser, User_idFrend) values (3, 1);
insert into Friends (User_idUser, User_idFrend) values (4, 2);
insert into Friends (User_idUser, User_idFrend) values (5, 1);
insert into Friends (User_idUser, User_idFrend) values (6, 1);
insert into Friends (User_idUser, User_idFrend) values (7, 1);
insert into Friends (User_idUser, User_idFrend) values (8, 2);
insert into Friends (User_idUser, User_idFrend) values (9, 2);
insert into Friends (User_idUser, User_idFrend) values (10, 2);
insert into Friends (User_idUser, User_idFrend) values (11, 2);
insert into Friends (User_idUser, User_idFrend) values (12, 1);
insert into Friends (User_idUser, User_idFrend) values (13, 2);
insert into Friends (User_idUser, User_idFrend) values (14, 1);
insert into Friends (User_idUser, User_idFrend) values (15, 1);
insert into Friends (User_idUser, User_idFrend) values (16, 2);
insert into Friends (User_idUser, User_idFrend) values (17, 2);
insert into Friends (User_idUser, User_idFrend) values (18, 2);
insert into Friends (User_idUser, User_idFrend) values (19, 2);
insert into Friends (User_idUser, User_idFrend) values (20, 2);
insert into Friends (User_idUser, User_idFrend) values (21, 2);
insert into Friends (User_idUser, User_idFrend) values (22, 2);
insert into Friends (User_idUser, User_idFrend) values (23, 1);
insert into Friends (User_idUser, User_idFrend) values (24, 2);
insert into Friends (User_idUser, User_idFrend) values (25, 2);
insert into Friends (User_idUser, User_idFrend) values (26, 2);
insert into Friends (User_idUser, User_idFrend) values (27, 2);
insert into Friends (User_idUser, User_idFrend) values (28, 2);
insert into Friends (User_idUser, User_idFrend) values (29, 2);
insert into Friends (User_idUser, User_idFrend) values (30, 1);
insert into Friends (User_idUser, User_idFrend) values (31, 2);
insert into Friends (User_idUser, User_idFrend) values (32, 2);
insert into Friends (User_idUser, User_idFrend) values (33, 1);
insert into Friends (User_idUser, User_idFrend) values (34, 2);
insert into Friends (User_idUser, User_idFrend) values (35, 1);
insert into Friends (User_idUser, User_idFrend) values (36, 2);
insert into Friends (User_idUser, User_idFrend) values (37, 2);
insert into Friends (User_idUser, User_idFrend) values (38, 1);
insert into Friends (User_idUser, User_idFrend) values (39, 2);
insert into Friends (User_idUser, User_idFrend) values (40, 2);
insert into Friends (User_idUser, User_idFrend) values (41, 1);
insert into Friends (User_idUser, User_idFrend) values (42, 2);
insert into Friends (User_idUser, User_idFrend) values (43, 1);
insert into Friends (User_idUser, User_idFrend) values (44, 2);
insert into Friends (User_idUser, User_idFrend) values (45, 2);
insert into Friends (User_idUser, User_idFrend) values (46, 2);
insert into Friends (User_idUser, User_idFrend) values (47, 2);
insert into Friends (User_idUser, User_idFrend) values (48, 2);
insert into Friends (User_idUser, User_idFrend) values (49, 2);
insert into Friends (User_idUser, User_idFrend) values (50, 1);

insert into Post (idPost, createdDate, title, User_idUser) values (1, '2021-06-21 21:36:24', 'Nulla nisl. Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus.', 1);
insert into Post (idPost, createdDate, title, User_idUser) values (2, '2021-08-17 11:25:07', 'Proin at turpis a pede posuere nonummy. Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque. Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus. In sagittis dui vel nisl. Duis ac nibh.', 2);
insert into Post (idPost, createdDate, title, User_idUser) values (3, '2021-08-01 19:58:46', 'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst. Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst.', 3);
insert into Post (idPost, createdDate, title, User_idUser) values (4, '2021-07-08 21:37:41', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin risus. Praesent lectus. Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis. Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor.', 4);
insert into Post (idPost, createdDate, title, User_idUser) values (5, '2021-08-11 18:33:39', 'Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo.', 5);
insert into Post (idPost, createdDate, title, User_idUser) values (6, '2021-08-13 06:36:00', 'In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque. Duis bibendum.', 6);
insert into Post (idPost, createdDate, title, User_idUser) values (7, '2021-08-03 06:07:08', 'Cras in purus eu magna vulputate luctus.', 7);
insert into Post (idPost, createdDate, title, User_idUser) values (8, '2021-06-17 18:07:12', 'Vestibulum rutrum rutrum neque.', 8);
insert into Post (idPost, createdDate, title, User_idUser) values (9, '2021-06-25 16:19:01', 'In hac habitasse platea dictumst.', 9);
insert into Post (idPost, createdDate, title, User_idUser) values (10, '2021-07-17 12:40:35', 'Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris. Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis. Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem.', 10);
insert into Post (idPost, createdDate, title, User_idUser) values (11, '2021-08-12 13:41:19', 'Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est. Phasellus sit amet erat.', 11);
insert into Post (idPost, createdDate, title, User_idUser) values (12, '2021-07-06 10:43:08', 'Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam.', 12);
insert into Post (idPost, createdDate, title, User_idUser) values (13, '2021-07-11 14:06:07', 'Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis.', 13);
insert into Post (idPost, createdDate, title, User_idUser) values (14, '2021-08-04 01:30:47', 'Integer ac leo. Pellentesque ultrices mattis odio.', 14);
insert into Post (idPost, createdDate, title, User_idUser) values (15, '2021-08-06 22:35:30', 'Integer a nibh. In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam.', 15);
insert into Post (idPost, createdDate, title, User_idUser) values (16, '2021-07-21 10:56:46', 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', 16);
insert into Post (idPost, createdDate, title, User_idUser) values (17, '2021-08-17 11:32:57', 'Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat. Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula.', 17);
insert into Post (idPost, createdDate, title, User_idUser) values (18, '2021-08-05 07:25:56', 'Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti. Nullam porttitor lacus at turpis.', 18);
insert into Post (idPost, createdDate, title, User_idUser) values (19, '2021-07-28 14:33:21', 'Morbi vel lectus in quam fringilla rhoncus. Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero. Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh. In quis justo.', 19);
insert into Post (idPost, createdDate, title, User_idUser) values (20, '2021-06-26 20:40:33', 'Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.', 20);
insert into Post (idPost, createdDate, title, User_idUser) values (21, '2021-06-05 16:58:45', 'In sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti.', 21);
insert into Post (idPost, createdDate, title, User_idUser) values (22, '2021-06-01 17:34:17', 'Nulla suscipit ligula in lacus. Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla.', 22);
insert into Post (idPost, createdDate, title, User_idUser) values (23, '2021-06-19 09:31:30', 'Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst.', 23);
insert into Post (idPost, createdDate, title, User_idUser) values (24, '2021-07-04 19:07:56', 'Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst.', 24);
insert into Post (idPost, createdDate, title, User_idUser) values (25, '2021-06-19 19:38:10', 'Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis. Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl.', 25);
insert into Post (idPost, createdDate, title, User_idUser) values (26, '2021-06-25 15:28:17', 'Etiam faucibus cursus urna. Ut tellus. Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi. Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque. Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla.', 26);
insert into Post (idPost, createdDate, title, User_idUser) values (27, '2021-06-07 01:48:15', 'Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.', 27);
insert into Post (idPost, createdDate, title, User_idUser) values (28, '2021-07-05 10:28:52', 'Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo. Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis. Sed ante. Vivamus tortor.', 28);
insert into Post (idPost, createdDate, title, User_idUser) values (29, '2021-07-06 22:31:19', 'In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus. Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.', 29);
insert into Post (idPost, createdDate, title, User_idUser) values (30, '2021-07-23 08:17:03', 'Donec vitae nisi. Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus. Curabitur at ipsum ac tellus semper interdum.', 30);
insert into Post (idPost, createdDate, title, User_idUser) values (31, '2021-07-29 03:52:12', 'Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo. Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros.', 31);
insert into Post (idPost, createdDate, title, User_idUser) values (32, '2021-07-08 21:59:37', 'Etiam justo. Etiam pretium iaculis justo. In hac habitasse platea dictumst. Etiam faucibus cursus urna.', 32);
insert into Post (idPost, createdDate, title, User_idUser) values (33, '2021-08-16 15:47:27', 'Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh. Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros. Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat.', 33);
insert into Post (idPost, createdDate, title, User_idUser) values (34, '2021-06-28 23:02:28', 'Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus. Phasellus in felis.', 34);
insert into Post (idPost, createdDate, title, User_idUser) values (35, '2021-08-08 09:46:17', 'Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi. Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus. Curabitur at ipsum ac tellus semper interdum.', 35);
insert into Post (idPost, createdDate, title, User_idUser) values (36, '2021-06-07 23:46:41', 'Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo. Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis. Sed ante. Vivamus tortor. Duis mattis egestas metus. Aenean fermentum. Donec ut mauris eget massa tempor convallis.', 36);
insert into Post (idPost, createdDate, title, User_idUser) values (37, '2021-06-14 10:00:41', 'Etiam pretium iaculis justo. In hac habitasse platea dictumst.', 37);
insert into Post (idPost, createdDate, title, User_idUser) values (38, '2021-06-09 19:03:27', 'Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus. Phasellus in felis. Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis.', 38);
insert into Post (idPost, createdDate, title, User_idUser) values (39, '2021-06-15 11:38:00', 'Donec ut dolor.', 39);
insert into Post (idPost, createdDate, title, User_idUser) values (40, '2021-07-17 17:26:12', 'Vivamus tortor. Duis mattis egestas metus. Aenean fermentum.', 40);
insert into Post (idPost, createdDate, title, User_idUser) values (41, '2021-07-30 11:35:39', 'Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh. Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros. Vestibulum ac est lacinia nisi venenatis tristique.', 41);
insert into Post (idPost, createdDate, title, User_idUser) values (42, '2021-06-24 03:28:59', 'Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi. Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla.', 42);
insert into Post (idPost, createdDate, title, User_idUser) values (43, '2021-06-10 21:59:27', 'Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus. In sagittis dui vel nisl.', 43);
insert into Post (idPost, createdDate, title, User_idUser) values (44, '2021-07-14 10:23:34', 'Suspendisse potenti. Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris. Morbi non lectus.', 44);
insert into Post (idPost, createdDate, title, User_idUser) values (45, '2021-08-17 21:17:20', 'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst. Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst.', 45);
insert into Post (idPost, createdDate, title, User_idUser) values (46, '2021-07-10 11:21:10', 'Vivamus in felis eu sapien cursus vestibulum. Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit.', 46);
insert into Post (idPost, createdDate, title, User_idUser) values (47, '2021-06-12 09:00:22', 'Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est. Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum. Proin eu mi.', 47);
insert into Post (idPost, createdDate, title, User_idUser) values (48, '2021-08-18 11:59:39', 'Nulla tellus. In sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio.', 48);
insert into Post (idPost, createdDate, title, User_idUser) values (49, '2021-07-31 21:07:16', 'Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque.', 49);

insert into Public (publicID, name, description, number_subscribers) values (1, 'Livetube', 'habitasse platea dictumst aliquam augue quam sollicitudin vitae consectetuer eget rutrum at lorem integer tincidunt', 29);
insert into Public (publicID, name, description, number_subscribers) values (2, 'Blogspan', 'ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae nulla dapibus dolor vel est donec odio', 21);

insert into User_has_Public (User_idUser, Public_publicID) values (1, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (2, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (3, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (4, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (5, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (6, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (7, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (8, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (9, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (10, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (11, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (12, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (13, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (14, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (15, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (16, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (17, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (18, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (19, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (20, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (21, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (22, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (23, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (24, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (25, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (26, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (27, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (28, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (29, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (30, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (31, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (32, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (33, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (34, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (35, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (36, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (37, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (38, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (39, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (40, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (41, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (42, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (43, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (44, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (45, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (46, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (47, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (48, 1);
insert into User_has_Public (User_idUser, Public_publicID) values (49, 2);
insert into User_has_Public (User_idUser, Public_publicID) values (50, 1);

insert into Publication (idPublication, createTime, Information) values (1, '2021-07-11 20:04:01', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (2, '2021-08-05 07:05:47', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (3, '2021-07-08 16:24:15', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (4, '2021-06-06 09:53:48', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (5, '2021-07-05 08:04:18', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (6, '2021-06-29 00:24:24', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (7, '2021-07-14 19:17:46', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (8, '2021-06-03 08:00:37', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (9, '2021-06-05 16:52:17', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (10, '2021-07-11 20:12:09', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (11, '2021-07-16 09:04:58', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (12, '2021-08-13 08:23:49', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (13, '2021-07-05 14:54:31', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (14, '2021-06-19 18:02:56', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (15, '2021-06-15 06:49:01', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (16, '2021-07-01 22:35:55', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (17, '2021-08-02 06:13:11', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (18, '2021-08-01 03:30:53', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (19, '2021-06-21 02:19:32', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (20, '2021-08-16 10:09:35', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (21, '2021-06-05 19:14:41', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (22, '2021-08-18 00:46:28', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (23, '2021-08-04 13:01:28', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (24, '2021-07-04 06:25:47', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (25, '2021-06-02 18:20:41', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (26, '2021-08-10 22:00:09', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (27, '2021-06-09 08:44:19', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (28, '2021-06-29 14:58:34', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (29, '2021-08-18 10:37:01', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (30, '2021-07-20 02:28:05', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (31, '2021-07-18 03:27:33', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (32, '2021-06-12 00:28:53', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (33, '2021-07-15 16:04:49', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (34, '2021-08-12 15:36:11', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (35, '2021-08-07 03:18:38', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (36, '2021-07-28 18:59:35', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (37, '2021-07-24 11:51:39', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (38, '2021-06-21 01:28:25', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (39, '2021-08-15 06:21:47', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (40, '2021-07-14 12:49:54', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (41, '2021-06-05 20:51:50', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (42, '2021-06-28 15:26:43', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (43, '2021-07-27 05:37:23', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (44, '2021-06-29 22:41:15', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (45, '2021-08-03 16:16:23', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (46, '2021-07-12 04:06:55', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (47, '2021-07-17 00:44:16', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (48, '2021-08-01 11:18:43', '??????????-???? ??????????...');
insert into Publication (idPublication, createTime, Information) values (49, '2021-07-06 17:11:30', '???????????????????? ?? ????????????');
insert into Publication (idPublication, createTime, Information) values (50, '2021-06-20 11:19:00', '???????????????????? ?? ????????????');

insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 1);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 2);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 3);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 4);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 5);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 6);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 7);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 8);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 9);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 10);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 11);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 12);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 13);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 14);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 15);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 16);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 17);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 18);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 19);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 20);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 21);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 22);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 23);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 24);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 25);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 26);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 27);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 28);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 29);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 30);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 31);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 32);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 33);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 34);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 35);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 36);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 37);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 38);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 39);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 40);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 41);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 42);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 43);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 44);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 45);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 46);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 47);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 48);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (2, 49);
insert into Group_has_Publication (Group_idGroup, Publication_idPublication) values (1, 50);

insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (1, '?????????????????? 1', '2021-08-25 03:24:28', 1, 2);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (2, '?????????????????? 3', '2021-08-24 15:32:16', 2, 2);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (3, '?????????????????? 2', '2021-08-25 21:40:52', 3, 3);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (4, '?????????????????? 1', '2021-08-24 15:37:56', 4, 3);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (5, '?????????????????? 1', '2021-08-24 17:57:11', 5, 3);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (6, '?????????????????? 2', '2021-08-25 11:06:22', 6, 3);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (7, '?????????????????? 2', '2021-08-25 18:06:47', 7, 2);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (8, '?????????????????? 3', '2021-08-25 02:07:34', 8, 2);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (9, '?????????????????? 1', '2021-08-24 05:20:03', 9, 1);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (10, '?????????????????? 2', '2021-08-25 14:33:02', 10, 1);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (11, '?????????????????? 2', '2021-08-24 21:24:05', 11, 3);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (12, '?????????????????? 1', '2021-08-25 20:50:24', 12, 2);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (13, '?????????????????? 1', '2021-08-25 09:56:12', 13, 1);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (14, '?????????????????? 2', '2021-08-25 17:55:34', 14, 2);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (15, '?????????????????? 3', '2021-08-24 18:08:00', 15, 3);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (16, '?????????????????? 4', '2021-08-24 02:59:10', 16, 3);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (17, '?????????????????? 4', '2021-08-24 17:22:31', 17, 2);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (18, '?????????????????? 1', '2021-08-25 07:23:50', 18, 3);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (19, '?????????????????? 4', '2021-08-25 11:38:05', 19, 1);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (20, '?????????????????? 1', '2021-08-25 22:56:19', 20, 1);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (21, '?????????????????? 2', '2021-08-24 16:04:35', 21, 1);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (22, '?????????????????? 3', '2021-08-24 12:27:12', 22, 3);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (23, '?????????????????? 3', '2021-08-24 12:26:37', 23, 1);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (24, '?????????????????? 3', '2021-08-24 13:01:58', 24, 1);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (25, '?????????????????? 3', '2021-08-25 15:00:56', 25, 1);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (26, '?????????????????? 3', '2021-08-25 21:22:06', 26, 3);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (27, '?????????????????? 3', '2021-08-25 21:22:13', 27, 3);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (28, '?????????????????? 4', '2021-08-24 05:03:12', 28, 3);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (29, '?????????????????? 3', '2021-08-24 10:07:46', 29, 2);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (30, '?????????????????? 3', '2021-08-24 03:30:24', 30, 2);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (31, '?????????????????? 1', '2021-08-24 20:55:41', 31, 3);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (32, '?????????????????? 4', '2021-08-25 08:57:58', 32, 3);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (33, '?????????????????? 3', '2021-08-24 23:37:56', 33, 3);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (34, '?????????????????? 2', '2021-08-25 18:56:49', 34, 3);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (35, '?????????????????? 2', '2021-08-25 23:01:45', 35, 1);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (36, '?????????????????? 1', '2021-08-25 23:29:19', 36, 2);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (37, '?????????????????? 2', '2021-08-24 20:32:49', 37, 3);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (38, '?????????????????? 4', '2021-08-25 04:01:47', 38, 2);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (39, '?????????????????? 2', '2021-08-25 16:34:33', 39, 3);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (40, '?????????????????? 4', '2021-08-25 20:16:23', 40, 2);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (41, '?????????????????? 1', '2021-08-24 13:02:05', 41, 3);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (42, '?????????????????? 1', '2021-08-24 06:37:42', 42, 1);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (43, '?????????????????? 3', '2021-08-25 07:20:11', 43, 2);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (44, '?????????????????? 1', '2021-08-24 03:59:27', 44, 1);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (45, '?????????????????? 2', '2021-08-24 13:57:00', 45, 1);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (46, '?????????????????? 4', '2021-08-24 03:36:13', 46, 1);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (47, '?????????????????? 3', '2021-08-24 20:32:40', 47, 1);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (48, '?????????????????? 3', '2021-08-25 13:02:38', 48, 3);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (49, '?????????????????? 4', '2021-08-24 01:30:34', 49, 1);
insert into Comment (idComment, Message, createTime, User_idUser, Publication_idPublication) values (50, '?????????????????? 1', '2021-08-24 05:37:35', 50, 3);
