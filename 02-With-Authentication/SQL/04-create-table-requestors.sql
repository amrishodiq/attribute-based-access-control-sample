CREATE TABLE `rest`.`requestor` (
  `requestor_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(40) NOT NULL,
  `password` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`requestor_id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC));