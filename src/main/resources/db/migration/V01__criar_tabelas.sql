CREATE TABLE IF NOT EXISTS avatares (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  avatar LONGBLOB NULL DEFAULT NULL,
  tipo VARCHAR(255) NOT NULL,
  titulo VARCHAR(255) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS produto (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  codigo_de_barras VARCHAR(255) NOT NULL,
  data_cadastro TIMESTAMP NULL DEFAULT NULL,
  descricao VARCHAR(255) NOT NULL,
  imagem_content_type VARCHAR(255) NULL DEFAULT NULL,
  nome VARCHAR(255) NOT NULL,
  avatar_id BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX UK_s8dbelljfeexcayiqfg1wihb2 (codigo_de_barras ASC),
  INDEX FKi59bk14twpl93xlccaoq2rbbv (avatar_id ASC),
  CONSTRAINT FKi59bk14twpl93xlccaoq2rbbv
    FOREIGN KEY (avatar_id)
    REFERENCES avatares (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS usuario (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  codigo VARCHAR(255) NOT NULL,
  data_cadastro TIMESTAMP NOT NULL,
  enabled BIT(1) NULL DEFAULT NULL,
  nome VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  sobrenome VARCHAR(255) NULL DEFAULT NULL,
  username VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX UK_nswqpd1o31h81htx40ewmb41r (codigo ASC),
  UNIQUE INDEX UK_863n1y3x0jalatoir4325ehal (username ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS movimentacao (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  data_hora TIMESTAMP NOT NULL,
  lote VARCHAR(255) NULL DEFAULT NULL,
  observacao VARCHAR(255) NULL DEFAULT NULL,
  situacao VARCHAR(255) NULL DEFAULT NULL,
  nota_fiscal VARCHAR(255) NULL DEFAULT NULL,
  produto_id BIGINT(20) NULL DEFAULT NULL,
  usuario_id BIGINT(20) NULL DEFAULT NULL,
  
  PRIMARY KEY (id),
  INDEX `FK2goctwoi39n4dbtug0re74y75` (produto_id ASC),
  INDEX `FKm285fyfcychfcyeunh0vcr3i6` (usuario_id ASC),
  CONSTRAINT FK2goctwoi39n4dbtug0re74y75
    FOREIGN KEY (produto_id)
    REFERENCES produto (id),
  CONSTRAINT FKm285fyfcychfcyeunh0vcr3i6
    FOREIGN KEY (usuario_id)
    REFERENCES usuario (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS permissao (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS usuario_permissoes (
  usuario_id BIGINT(20) NOT NULL,
  permissao_id BIGINT(20) NOT NULL,
  INDEX FKtcuagcmypmug2ddh2d5uol8s5 (permissao_id ASC),
  INDEX FK5wc2vh351r26qbqt1tc52sh4m (usuario_id ASC),
  CONSTRAINT FK5wc2vh351r26qbqt1tc52sh4m
    FOREIGN KEY (usuario_id)
    REFERENCES usuario (id),
  CONSTRAINT FKtcuagcmypmug2ddh2d5uol8s5
    FOREIGN KEY (permissao_id)
    REFERENCES permissao (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

