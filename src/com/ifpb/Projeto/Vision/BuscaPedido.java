package com.ifpb.Projeto.Vision;

import Dao.DaoArquivoPedido;
import com.ifpb.Projeto.modelo.Pedido;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class BuscaPedido extends JFrame{
    private JPanel painelBusca;
    private JFormattedTextField busca;
    private JButton voltarButton;
    private JButton buscarButton;
    private JLabel busImg;
    private DaoArquivoPedido daoArquivoPedido;

    public BuscaPedido(){
        super("Buscar Pedidos");
        setContentPane(painelBusca);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(600, 600);
        ImageIcon imageIcon = new ImageIcon("imagens/cartIcon.png");
        setIconImage(imageIcon.getImage());
        try {
            daoArquivoPedido = new DaoArquivoPedido();
        } catch (IOException e) {
            e.printStackTrace();
        }

        voltarButton.addActionListener(e -> {
            dispose();
        });


        buscarButton.addActionListener(e-> {

            List<Pedido> matches;
            try {
                matches = daoArquivoPedido.getAllStartsWithName(busca.getText());


                    if (matches == null || matches.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nao foi encontrado nenhum \npedido com esse número!");
                    } else {

                        StringBuilder builder = new StringBuilder();
                        for (Pedido p : matches) {
                            builder.append("\n")
                                    .append("Mesa: ")
                                    .append(p.getMesa())
                                    .append("\n")
                                    .append("Valor: ")
                                    .append(p.getValor())
                                    .append("\n")
                                    .append("Produtos:")
                                    .append(p.getDescricao())
                                    .append("\n")
                                    .append("Data: ")
                                    .append(p.getDataPedido())
                                    .append("\n")
                                    .append("Numero: ")
                                    .append(p.getNumeroPedido())
                                    .append("\n");
                    }
                        JOptionPane.showMessageDialog(null, "Pedido Encontrados: \n" + builder.toString());
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }
    private void createUIComponents() {



        ImageIcon imageIcon = new ImageIcon("imagens/basket.png");
        busImg = new JLabel(imageIcon);
        ImageIcon imageIcon1 = new ImageIcon("imagens/lupinha.png");
        buscarButton = new JButton(imageIcon1);
        ImageIcon imageIcon2 = new ImageIcon("imagens/return.png");
        voltarButton = new JButton(imageIcon2);

    }
}
