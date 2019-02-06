USE rest;
CREATE TABLE `rest`.`patients` (
  `patient_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `national_id_number` VARCHAR(20) NULL,
  `real_name` VARCHAR(60) NOT NULL,
  `date_of_birth` DATE NULL,
  `gender` TINYINT(4) NULL,
  `mother_name` VARCHAR(60) NULL,
  `father_name` VARCHAR(60) NULL,
  `responsible_person` VARCHAR(60) NULL,
  PRIMARY KEY (`patient_id`));
CREATE TABLE `rest`.`medical_records` (
  `medical_record_number` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `patient_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`medical_record_number`),
  UNIQUE INDEX `patient_id_UNIQUE` (`patient_id` ASC));
CREATE TABLE `rest`.`medical_records_items` (
  `medical_record_item_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `medical_record_number` INT UNSIGNED NOT NULL,
  `doctor` VARCHAR(60) NOT NULL,
  `visit_date` DATE NOT NULL,
  `symptoms` TEXT NOT NULL,
  `diagnoses` TEXT NOT NULL,
  `disease` TEXT NULL,
  `treatment` TEXT NULL,
  `prescriptions` TEXT NULL,
  `healed` TINYINT NULL,
  PRIMARY KEY (`medical_record_item_id`));