package com.ifpb.Projeto.modelo;


import java.io.Serializable;
import java.time.LocalDate;

public class Pedido implements Serializable {

    private String numeroPedido;
    private LocalDate dataPedido;
    private int mesa;
    private double valor;

    public Pedido(String numeropedido, LocalDate dataPedido, int mesa, double valorpedido) {
        this.numeroPedido = numeroPedido;
        this.dataPedido = dataPedido;
        this.mesa = mesa;
        this.valor = valor;
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
}

