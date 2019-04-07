package com.ifpb.Projeto.Vision;

import Dao.DaoArquivoBebida;
import Dao.DaoArquivoPrato;
import com.ifpb.Projeto.modelo.Bebida;
import com.ifpb.Projeto.modelo.Prato;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class BuscaProduto extends JFrame{
    private JTextField busacProduto;
    private JButton buscarButton;
    private JButton voltarButton;
    private JPanel buscarProduto;
    private JLabel busProduto;
    private DaoArquivoPrato daoArquivoPrato;
    private DaoArquivoBebida daoArquivoBebida;

    public BuscaProduto(){
        super("Busca de funcionários");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(500,500);
        setContentPane(buscarProduto);
        ImageIcon icone = new ImageIcon("imagens/buscaProdutoCart.png");
        setIconImage(icone.getImage());

        try {
            this.daoArquivoPrato = new DaoArquivoPrato();
            this.daoArquivoBebida = new DaoArquivoBebida();
        } catch (IOException e) {
            e.printStackTrace();
        }

        voltarButton.addActionListener(e->{
            this.dispose();
        });

        buscarButton.addActionListener(e->{
            //imprime um funcionário ou lsta de funcionários cadastrado na tela, caso n exista uma janela sera aberta
            //informando que o funcionário não está cadastrado ou o nome está errado;
            List<Bebida> matches;
            List<Prato> matches2;
            try {

                matches = daoArquivoBebida.getAllStartsWithName(busacProduto.getText());
                matches2 = daoArquivoPrato.getAllStartsWithName(busacProduto.getText());

                if (matches == null || matches.isEmpty()) {

                    if (matches2 != null || !matches2.isEmpty()) {
                        StringBuilder builder = new StringBuilder();
                        for (Prato p : matches2) {
                            builder.append("\n")
                                    .append("NOME: ")
                                    .append(p.getNome())
                                    .append("\n")
                                    .append("Preço: ")
                                    .append(p.getPreco())
                                    .append("\n")
                                    .append("Quantidade:")
                                    .append(p.getQuantidade())
                                    .append("\n");
                        }
                        JOptionPane.showMessageDialog(null, "Pratos Encontrados: \n" + builder.toString());
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Nao foi encontrado nenhum Produto!");
                    }
                } else {
                    StringBuilder builder = new StringBuilder();
                    for (Bebida b : matches) {
                        builder.append("\n")
                                .append("NOME: ")
                                .append(b.getNome())
                                .append("\n")
                                .append("Bebida: ")
                                .append(b.getPreco())
                                .append("\n")
                                .append("Quantidade:")
                                .append(b.getQuantidade())
                                .append("\n");
                    }
                    JOptionPane.showMessageDialog(null, "Bebidas Encontradas: \n" + builder.toString());
                }

            } catch (IOException | ClassNotFoundException e1) {
                e1.printStackTrace();
            }

        });
    }

    private void createUIComponents() {
        ImageIcon procuraProduto = new ImageIcon("imagens/buscaProdutoCart.png");
        busProduto = new JLabel(procuraProduto);
        ImageIcon lupa = new ImageIcon("imagens/lupinha.png");
        buscarButton = new JButton(lupa);
        ImageIcon voltar = new ImageIcon("imagens/return.png");
        voltarButton = new JButton(voltar);
    }
}
