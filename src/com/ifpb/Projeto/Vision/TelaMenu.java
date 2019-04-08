package com.ifpb.Projeto.Vision;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Tela principal (menu) que redireciona às demais telas
 */
public class TelaMenu extends JFrame{
    private JPanel panel;
    private JButton cadastroDeFuncionarioButton;
    private JButton buscaDeFuncionarioButton;
    private JButton buscaDeProdutosButton;
    private JButton buscaDePedidosButton;
    private JButton cadastroDeProdutoButton;
    private JButton cadastroDePedidosButton;
    private JLabel imagem;
    private JLabel imagem2;
    private JButton encerrarButton;

    public TelaMenu (){
        super("Recanto da Paquera");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(panel);
        this.setUndecorated(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setSize(1388,768);

        /**
         * Chamando tela de cadastro funcionário
         */
        cadastroDeFuncionarioButton.addActionListener(e-> {
            TelaCadastro funcionario = new TelaCadastro();
            funcionario.setVisible(true);
        });
        /**
         * Buscando um funcionário
         */
        buscaDeFuncionarioButton.addActionListener(e-> {
            TelaBusca busFuncionario = new TelaBusca();
            busFuncionario.setVisible(true);
        });
        /**
         * Cadastrando um produto
         */
        cadastroDeProdutoButton.addActionListener(e-> {
            CadastroProduto produto = new CadastroProduto();
            produto.setVisible(true);
        });
        /**
         * Buscando um Produto
         */
        buscaDeProdutosButton.addActionListener(e-> {
            BuscaProduto busProduto = new BuscaProduto();
            busProduto.setVisible(true);
        });
<<<<<<< HEAD
        /**
         * Cadastrando os pedidos
         */
=======
>>>>>>> afb34d1b085104ff3728379b3de8deb5d14a7822
        cadastroDePedidosButton.addActionListener(e-> {
            try {
                CadastroPedido pedido =new CadastroPedido();
                pedido.setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
<<<<<<< HEAD
        /**
         * Buscando os pedidos
         */
=======
>>>>>>> afb34d1b085104ff3728379b3de8deb5d14a7822
        buscaDePedidosButton.addActionListener(e-> {
            BuscaPedido buscaPedido = new BuscaPedido();
            buscaPedido.setVisible(true);
        });
<<<<<<< HEAD
        /**
         * Encerrrando a aplicação
         */
        encerrarButton.addActionListener(e-> {
           System.exit(0);
        });
=======
>>>>>>> afb34d1b085104ff3728379b3de8deb5d14a7822
    }

    private void createUIComponents() {
        ImageIcon imageIcon = new ImageIcon("imagens/taco.png");
        imagem = new JLabel(imageIcon);
        ImageIcon imageIcon1 = new ImageIcon("imagens/Recanto.png");
        imagem2 = new JLabel(imageIcon1);

        ImageIcon btEncerra = new ImageIcon("imagens/death.png");
        encerrarButton = new JButton(btEncerra);
    }
}
