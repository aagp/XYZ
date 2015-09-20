SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `xyzdb`.`empleados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `xyzdb`.`empleados` (
  `idEmpleado` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(25) NULL,
  `apellidos` VARCHAR(30) NULL,
  `fechaIngreso` DATE NULL,
  `curp` VARCHAR(18) NULL,
  `puesto` VARCHAR(20) NULL,
  `activo` BIT NULL,
  PRIMARY KEY (`idEmpleado`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `xyzdb`.`movimientos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `xyzdb`.`movimientos` (
  `idMovimiento` INT NOT NULL AUTO_INCREMENT,
  `tipoMov` VARCHAR(10) NULL,
  `puestoAnterior` VARCHAR(20) NULL,
  `puestoNuevo` VARCHAR(20) NULL,
  `fechaMov` DATE NULL,
  `idEmpleado` INT NOT NULL,
  PRIMARY KEY (`idMovimiento`),
  INDEX `idEmpleado_idx` (`idEmpleado` ASC),
  CONSTRAINT `idEmpleado`
    FOREIGN KEY (`idEmpleado`)
    REFERENCES `xyzdb`.`empleados` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
