ALTER TABLE produto
    ADD COLUMN layout_id BIGINT(20) DEFAULT NULL;

ALTER TABLE produto ADD CONSTRAINT fk_produto_layout
    FOREIGN KEY (layout_id) REFERENCES layouts(id);