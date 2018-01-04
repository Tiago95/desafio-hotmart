DROP SCHEMA IF EXISTS `desafio-hotmart`;
CREATE SCHEMA `desafio-hotmart` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `desafio-hotmart`;

CREATE TABLE `usuario` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `login` varchar(150) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `nick_name` varchar(50) NOT NULL,
  `status` tinyint(3) unsigned NOT NULL,
  `conectado` BIT NOT NULL DEFAULT 0,
  `permitir_conversas_anonimas` BIT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_Usuario_Id` (`id`),
  UNIQUE KEY `UNIQUE_Usuario_login` (`login`),
  UNIQUE KEY `UNIQUE_Usuario_nick_name` (`nick_name`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `desafio-hotmart`.`chat_message` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `usuario_origem_id` BIGINT(20) UNSIGNED NOT NULL,
  `usuario_destino_id` BIGINT(20) UNSIGNED NOT NULL,
  `message` VARCHAR(255) CHARACTER SET 'utf8mb4' NOT NULL,
  `send_date` DATETIME NOT NULL,
  `lida` BIT NOT NULL DEFAULT 0,
  `recebida` BIT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `FK_INDEX_ChatMessage_Usuario_Origem` (`usuario_origem_id` ASC),
  INDEX `FK_INDEX_ChatMessage_Usuario_Destino` (`usuario_destino_id` ASC),
  CONSTRAINT `FK_Chat_Message_Usuario_Origem`
    FOREIGN KEY (`usuario_origem_id`)
    REFERENCES `desafio-hotmart`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Chat_Message_Usuario_Destino`
    FOREIGN KEY (`usuario_destino_id`)
    REFERENCES `desafio-hotmart`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `desafio-hotmart`.`usuario_bloqueado` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `usuario_principal_id` BIGINT(20) UNSIGNED NOT NULL,
  `usuario_bloqueado_id` BIGINT(20) UNSIGNED NOT NULL,
  `data_bloqueio` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `FK_INDEX_UsuarioBloqueado_Usuario_Principal` (`usuario_principal_id` ASC),
  INDEX `FK_INDEX_UsuarioBloqueado_Usuario_Bloqueado` (`usuario_bloqueado_id` ASC),
  CONSTRAINT `FK_Usuario_Bloqueado_Usuario_Principal`
    FOREIGN KEY (`usuario_principal_id`)
    REFERENCES `desafio-hotmart`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Usuario_Bloqueado_Usuario_Bloqueado`
    FOREIGN KEY (`usuario_bloqueado_id`)
    REFERENCES `desafio-hotmart`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)ENGINE = InnoDB DEFAULT CHARACTER SET = UTF8;

CREATE TABLE `desafio-hotmart`.`pedido_amizade` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `usuario_principal_id` BIGINT(20) UNSIGNED NOT NULL,
  `usuario_amigo_id` BIGINT(20) UNSIGNED NOT NULL,
  `data_solicitacao` DATETIME NOT NULL,
  `status_solicitacao` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `FK_INDEX_PedidoAmizade_Usuario_Principal` (`usuario_principal_id` ASC),
  INDEX `FK_INDEX_PedidoAmizade_Usuario_Amigo` (`usuario_amigo_id` ASC),
  CONSTRAINT `FK_Pedido_Amizade_Usuario_Principal`
    FOREIGN KEY (`usuario_principal_id`)
    REFERENCES `desafio-hotmart`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_INDEX_Pedido_Amizade_Usuario_Amigo`
    FOREIGN KEY (`usuario_amigo_id`)
    REFERENCES `desafio-hotmart`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)ENGINE = InnoDB DEFAULT CHARACTER SET = UTF8;