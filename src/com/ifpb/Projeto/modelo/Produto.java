package com.ifpb.Projeto.modelo;

import java.io.Serializable;

public abstract class Produto implements Serializable {
    String nome;
    String codProduto;
    double preco;
    int quantidade;

    public Produto(String nome, String codProduto, double preco, int quantidade) {
        this.nome = nome;
        this.codProduto = codProduto;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodProduto() {
        return codProduto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
