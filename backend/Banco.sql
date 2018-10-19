CREATE DATABASE IF NOT EXISTS `aula_ws_presentes` DEFAULT CHARACTER SET latin1;
USE `aula_ws_presentes`;

CREATE TABLE IF NOT EXISTS `presentes` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `valor` decimal(10,3) NOT NULL,
  `mensagem` text,
  `convidado` char(5) NOT NULL,
  `data` date NOT NULL,
  `dataCadastro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87654366 DEFAULT CHARSET=latin1;
