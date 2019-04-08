package com.ifpb.Projeto.modelo;
import com.ifpb.Projeto.Vision.Tipo;

/**
 * Classe que herda de 'Produto' e armazena informações sobre os pratos do restaurante
 */
public class Prato extends Produto{
    Tipo tipo;

    public Prato(String nome, String codProduto, double preco, int quantidade, Tipo tipo) {
        super(nome, codProduto, preco, quantidade);
        this.tipo = tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
