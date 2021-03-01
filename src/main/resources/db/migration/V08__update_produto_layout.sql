ALTER TABLE produto
    ADD COLUMN layout_id BIGINT DEFAULT NULL;

ALTER TABLE produto ADD CONSTRAINT fk_produto_layout
    FOREIGN KEY (layout_id) REFERENCES layouts(id);