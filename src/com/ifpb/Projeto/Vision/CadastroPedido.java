package com.ifpb.Projeto.Vision;

import Dao.DaoArquivoPedido;
import com.ifpb.Projeto.modelo.Pedido;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CadastroPedido extends JFrame{
    private JFormattedTextField numField;
    private JFormattedTextField valField;
    private JSpinner spinnerMesa;
    private JFormattedTextField dataField;
    private JButton salvarButton;
    private JButton limparButton;
    private JPanel controlePed;
    private JLabel labelImage;
    private JLabel labelControla;
    private DaoArquivoPedido daoArquivoPedido;

    public CadastroPedido() throws IOException {
        super("Controle de Pedidos: ");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500,700);
        setContentPane(controlePed);
        ImageIcon icone = new ImageIcon("imagens/notepadFundo.png");
        setIconImage(icone.getImage());
        daoArquivoPedido = new DaoArquivoPedido();

        limparButton.addActionListener(e -> {

            numField.setText("");
            valField.setText("");
            spinnerMesa.setValue(0);
            dataField.setText("");
        });
        salvarButton.addActionListener (e->{
            try {
                if (exists()){
                    JOptionPane.showMessageDialog(null, "Pedido ja existe");
                }else {
                    String numeropedido = dataField.getText();
                    double valorpedido = Double.parseDouble(valField.getText());
                    int mesa = (Integer) spinnerMesa.getValue();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dataPedido = LocalDate.parse(dataField.getText(), formatter);

                    Pedido pedido = new Pedido(numeropedido, dataPedido, mesa, valorpedido);
                    daoArquivoPedido.add(pedido);
                    System.out.println(numeropedido + valorpedido + mesa + dataPedido);
                    JOptionPane.showMessageDialog(null, "Funcionário Cadastrado!");
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void createUIComponents() {
        ImageIcon controlaPedido = new ImageIcon("imagens/notepad.png");
        labelImage = new JLabel(controlaPedido);
        ImageIcon save = new ImageIcon("imagens/salvar.png");
        salvarButton = new JButton(save);
        ImageIcon limpar = new ImageIcon("imagens/limpar.png");
        limparButton = new JButton(limpar);


        //criando mascara para nascimento

        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("##/##/####");
        } catch (ParseException e) {
            e.printStackTrace();
        }dataField = new JFormattedTextField();
        formatter.install(dataField);

        try {
            formatter= new MaskFormatter("########");
        } catch (ParseException e) {
            e.printStackTrace();
        }numField = new JFormattedTextField();
        formatter.install(numField);

        try {
            formatter= new MaskFormatter("####.##");
        } catch (ParseException e) {
            e.printStackTrace();
        }valField = new JFormattedTextField();
        formatter.install(valField);


    }
    private boolean exists() throws IOException, ClassNotFoundException {
        for (Pedido p : daoArquivoPedido.getAll()) {
            if (p.getNumeroPedido().equals(numField.getText())) {
                return true;
            }
        }
        return false;

    }

}


