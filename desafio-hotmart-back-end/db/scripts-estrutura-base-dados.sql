CREATE SCHEMA `desafio-hotmart` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `desafio-hotmart`;

CREATE TABLE `usuario` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(150) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `nick_name` varchar(50) NOT NULL,
  `status` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_Usuario_Id` (`id`),
  UNIQUE KEY `UNIQUE_Usuario_login` (`login`),
  UNIQUE KEY `UNIQUE_Usuario_nick_name` (`nick_name`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
