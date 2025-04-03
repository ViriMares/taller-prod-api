-- MySQL Script generated by MySQL Workbench
-- Thu Mar 13 01:11:41 2025
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema proyectocertificado
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema proyectocertificado
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `proyectocertificado` DEFAULT CHARACTER SET utf8 ;
USE `proyectocertificado` ;

-- -----------------------------------------------------
-- Table `proyectocertificado`.`productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectocertificado`.`productos` (
  `id_producto` INT NOT NULL AUTO_INCREMENT,
  `nombre_producto` VARCHAR(50) NOT NULL,
  `precio` DOUBLE NOT NULL,
  `stock` INT NOT NULL,
  `fecha_actualizacion` DATETIME NOT NULL,
  PRIMARY KEY (`id_producto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectocertificado`.`catalogo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectocertificado`.`catalogo` (
  `id_catalogo` INT NOT NULL AUTO_INCREMENT,
  `id_producto` INT NOT NULL,
  `descripcion` VARCHAR(250) NOT NULL,
  `imagen_url` VARCHAR(250) NOT NULL,
  `categoria` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_catalogo`),
  INDEX `id_producto_idx` (`id_producto` ASC) VISIBLE,
  CONSTRAINT `id_producto`
    FOREIGN KEY (`id_producto`)
    REFERENCES `proyectocertificado`.`productos` (`id_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectocertificado`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectocertificado`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nombre_usuario` VARCHAR(45) NOT NULL,
  `correo_electronico` VARCHAR(45) NOT NULL,
  `numero_telefonico` VARCHAR(45) NOT NULL,
  `numero_secundario` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `pais` VARCHAR(45) NOT NULL,
  `tipo_usuario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectocertificado`.`consulta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectocertificado`.`consulta` (
  `id_consulta` INT NOT NULL AUTO_INCREMENT,
  `nombre_tipo_consulta` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_consulta`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectocertificado`.`prioridad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectocertificado`.`prioridad` (
  `id_prioridad` INT NOT NULL AUTO_INCREMENT,
  `nivel_prioridad` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_prioridad`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectocertificado`.`estado_ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectocertificado`.`estado_ticket` (
  `id_estado_ticket` INT NOT NULL AUTO_INCREMENT,
  `estado_ticket` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_estado_ticket`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectocertificado`.`sistema_tickets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectocertificado`.`sistema_tickets` (
  `id_ticket` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  `id_consulta` INT NOT NULL,
  `id_estado_ticket` INT NOT NULL,
  `id_prioridad` INT NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `fecha_creacion` DATETIME NOT NULL,
  `fecha_finalización` DATETIME NULL,
  PRIMARY KEY (`id_ticket`, `id_usuario`, `id_consulta`, `id_estado_ticket`, `id_prioridad`),
  INDEX `id_usuario_idx` (`id_usuario` ASC) VISIBLE,
  INDEX `id_consulta_idx` (`id_consulta` ASC) VISIBLE,
  INDEX `id_prioridad_idx` (`id_prioridad` ASC) VISIBLE,
  INDEX `id_estado_ticket_idx` (`id_estado_ticket` ASC) VISIBLE,
  CONSTRAINT `id_usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `proyectocertificado`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_consulta`
    FOREIGN KEY (`id_consulta`)
    REFERENCES `proyectocertificado`.`consulta` (`id_consulta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_prioridad`
    FOREIGN KEY (`id_prioridad`)
    REFERENCES `proyectocertificado`.`prioridad` (`id_prioridad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_estado_ticket`
    FOREIGN KEY (`id_estado_ticket`)
    REFERENCES `proyectocertificado`.`estado_ticket` (`id_estado_ticket`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
