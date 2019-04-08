package com.ifpb.Projeto.modelo;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Pedido implements Serializable {

    private String numeroPedido;
    private LocalDate dataPedido;
    private int mesa;
    private double valor;
    private String descricao;

    public Pedido(String numpedido, LocalDate dataPedido, int mesa, double valorpedido, String descricao) {
        this.numeroPedido = numpedido;
        this.dataPedido = dataPedido;
        this.mesa = mesa;
        this.valor = valorpedido;
        this.descricao = descricao;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "numeroPedido= '" + numeroPedido + '\'' +
                ", dataPedido= " + dataPedido +
                ", mesa= " + mesa +
                ", valor= " + valor +
                ", descricao= '" + descricao + '\'' +
                '}';
    }
}

