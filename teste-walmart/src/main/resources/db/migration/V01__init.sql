CREATE TABLE mapa (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE rota (
  `id` INT NOT NULL AUTO_INCREMENT,
  `origem` VARCHAR(255) NOT NULL,
  `destino` VARCHAR(255) NOT NULL,
  `distancia` INT NOT NULL,
  `mapa_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UN_ORIGEM_DESTINO` (`origem` ASC, `destino` ASC),
  INDEX `FK_ROTA__MAPA_idx` (`mapa_id` ASC),
  CONSTRAINT `FK_ROTA__MAPA`
    FOREIGN KEY (`mapa_id`)
    REFERENCES mapa (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);