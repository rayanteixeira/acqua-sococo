package br.com.acqua.entity.enuns;

public enum Categoria {
    CAIXA("Caixa"), EMBALAGEM("Embalagem"), OUTROS("Outros");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
