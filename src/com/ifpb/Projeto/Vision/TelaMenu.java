package com.ifpb.Projeto.Vision;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMenu extends JFrame{
    private JPanel panel;
    private JButton cadastroDeFuncionárioButton;
    private JButton buscaDeeFuncionárioButton;
    private JButton buscaDeProdutosButton;
    private JButton buscaDePedidosButton;
    private JButton cadastroDeProdutoButton;
    private JButton cadastroDePedidosButton;
    private JLabel imagem;
    private JLabel imagem2;

    public TelaMenu (){
        super("Recanto da Paquera");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(panel);
        this.setUndecorated(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setSize(1388,768);


        cadastroDeFuncionárioButton.addActionListener(e-> {
            TelaCadastro funcionario = new TelaCadastro();
            funcionario.setVisible(true);
        });
        buscaDeeFuncionárioButton.addActionListener(e-> {
            TelaBusca busFuncionario = new TelaBusca();
            busFuncionario.setVisible(true);
        });
        cadastroDeProdutoButton.addActionListener(e-> {
            CadastroProduto produto = new CadastroProduto();
            produto.setVisible(true);
        });
        buscaDeProdutosButton.addActionListener(e-> {
            BuscaProduto busProduto = new BuscaProduto();
            busProduto.setVisible(true);
        });
//        cadastroDePedidosButton.addActionListener(e-> {
//
//
//        });
//        buscaDePedidosButton.addActionListener(e-> {
//
//        });
    }

    private void createUIComponents() {
        ImageIcon imageIcon = new ImageIcon("imagens/restaurante.png");
        imagem = new JLabel(imageIcon);
        ImageIcon imageIcon1 = new ImageIcon("imagens/Recanto.png");
        imagem2 = new JLabel(imageIcon1);
    }
}
