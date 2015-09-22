SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `xyzdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `xyzdb` ;

-- -----------------------------------------------------
-- Table `xyzdb`.`empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `xyzdb`.`empleado` (
  `idEmpleado` INT NOT NULL,
  `nombre` VARCHAR(25) NULL,
  `apellidos` VARCHAR(30) NULL,
  `fechaIngreso` DATE NULL,
  `curp` VARCHAR(18) NULL,
  `puesto` VARCHAR(30) NULL,
  `activo` BIT NULL,
  PRIMARY KEY (`idEmpleado`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `xyzdb`.`movimiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `xyzdb`.`movimiento` (
  `idMovimiento` INT NOT NULL AUTO_INCREMENT,
  `tipoMov` VARCHAR(10) NULL,
  `puestoAnterior` VARCHAR(30) NULL,
  `puestoNuevo` VARCHAR(30) NULL,
  `fechaMov` DATE NULL,
  `idEmpleado` INT NOT NULL,
  PRIMARY KEY (`idMovimiento`),
  INDEX `fk_movimiento_empleado_idx` (`idEmpleado` ASC),
  CONSTRAINT `fk_movimiento_empleado`
    FOREIGN KEY (`idEmpleado`)
    REFERENCES `xyzdb`.`empleado` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
