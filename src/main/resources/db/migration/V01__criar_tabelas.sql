CREATE TABLE IF NOT EXISTS avatares
(
    id     SERIAL       NOT NULL,
    avatar bytea        DEFAULT NULL,
    tipo   VARCHAR(255) NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS produto
(
    id                  SERIAL       NOT NULL,
    codigo_de_barras    VARCHAR(255) NOT NULL,
    data_cadastro       TIMESTAMP    DEFAULT NULL,
    descricao           VARCHAR(255) DEFAULT NULL,
    imagem_content_type VARCHAR(255) DEFAULT NULL,
    nome                VARCHAR(255) NOT NULL,
    avatar_id           BIGINT       DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE (codigo_de_barras)
);
ALTER TABLE produto
    ADD CONSTRAINT FK_PRODUTO_AVATAR
        FOREIGN KEY (avatar_id)
            REFERENCES avatares (id);

CREATE TABLE IF NOT EXISTS usuario
(
    id            SERIAL       NOT NULL,
    codigo        VARCHAR(255) NOT NULL,
    data_cadastro TIMESTAMP    NOT NULL,
    enabled       boolean      DEFAULT NULL,
    nome          VARCHAR(255) NOT NULL,
    password      VARCHAR(255) NOT NULL,
    sobrenome     VARCHAR(255) NULL DEFAULT NULL,
    username      VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS movimentacao
(
    id          SERIAL    NOT NULL,
    data_hora   TIMESTAMP NOT NULL,
    lote        VARCHAR(255) DEFAULT NULL,
    observacao  VARCHAR(255) DEFAULT NULL,
    situacao    VARCHAR(255) DEFAULT NULL,
    nota_fiscal VARCHAR(255) DEFAULT NULL,
    produto_id  BIGINT       DEFAULT NULL,
    usuario_id  BIGINT       DEFAULT NULL,
    PRIMARY KEY (id)

);

ALTER TABLE movimentacao
    ADD CONSTRAINT FK_MOVIMENTACAO_PRODUTO
        FOREIGN KEY (produto_id)
            REFERENCES produto (id);

ALTER TABLE movimentacao
    ADD CONSTRAINT FK_MOVIMENTACAO_USUARIO
        FOREIGN KEY (usuario_id)
            REFERENCES usuario (id);

CREATE TABLE IF NOT EXISTS permissao
(
    id   SERIAL       NOT NULL,
    nome VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS usuario_permissoes
(
    usuario_id   BIGINT NOT NULL,
    permissao_id BIGINT NOT NULL
);

ALTER TABLE usuario_permissoes
    ADD CONSTRAINT fk_usuario_permissoes_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuario (id);

ALTER TABLE usuario_permissoes
    ADD CONSTRAINT fk_usuario_permissoes_permissao
        FOREIGN KEY (permissao_id) REFERENCES permissao (id);

