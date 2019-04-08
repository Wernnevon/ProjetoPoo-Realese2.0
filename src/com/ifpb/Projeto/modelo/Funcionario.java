package com.ifpb.Projeto.modelo;

import com.ifpb.Projeto.Vision.Funcao;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe que armazena as informações sobre os Funcionários do restaurante
 */
public class Funcionario implements Serializable {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    protected Funcao funcao;


    public Funcionario(String nome, String cpf, LocalDate dataNascimento, Funcao funcao) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.funcao = funcao;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", funcao='" + funcao + '\'' +
                '}';
    }
}
