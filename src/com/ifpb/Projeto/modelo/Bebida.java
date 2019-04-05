package com.ifpb.Projeto.modelo;


import com.ifpb.Projeto.Vision.Classe;

import java.io.Serializable;

public class Bebida extends Produto implements Serializable {
    Classe classe;
    int idade;
    public Bebida(String nome, String codProduto, double preco, int quantidade, Classe classe, int idade) {
        super(nome, codProduto, preco, quantidade);
        this.classe = classe;
        this.idade = idade;
    }

    public Classe getClasse() {
        return classe;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

}

