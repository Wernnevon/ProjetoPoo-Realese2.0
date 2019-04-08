package com.ifpb.Projeto.Vision;

import Dao.DaoArquivoPedido;
import com.ifpb.Projeto.modelo.Pedido;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

public class CadastroPedido extends JFrame{
    private JPanel painelPedido;
    private JFormattedTextField numPedido;
    private JFormattedTextField valorPedido;
    private JTextArea descricaoPedido;
    private JSpinner spinnerMesa;
    private JButton salvarButton;
    private JButton limparButton;
    private JLabel imagePedido;
    private Pedido pedido;

    private DaoArquivoPedido daoArquivoPedido;

    public CadastroPedido() throws IOException {
        super("Controle de Pedidos");
        setContentPane(painelPedido);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(500, 700);
        ImageIcon imageIcon = new ImageIcon("imagens/notepadFundo.png");
        setIconImage(imageIcon.getImage());
        spinnerMesa.setModel(new SpinnerNumberModel(0, 0, 10, 1));
        installFormatters();

        daoArquivoPedido = new DaoArquivoPedido();

        limparButton.addActionListener(e->{
            numPedido.setText("");
            valorPedido.setText("");
            descricaoPedido.setText("");
            spinnerMesa.setValue(0);

        });


        salvarButton.addActionListener(e-> {
            if (isEmpity()){
                JOptionPane.showMessageDialog(null, "Campos Vazios! \n Por favor preencha todos os campos!");
            }else {
                    if (exite()){
                        JOptionPane.showMessageDialog(null, "Numero do pedido ja Existe!");
                    }else {
                        String numero = numPedido.getText();
                        double valor = Double.parseDouble(valorPedido.getText());
                        String descricao = descricaoPedido.getText();
                        int mesa = (Integer) spinnerMesa.getValue();
                        LocalDate dataPedido = LocalDate.now();

                        pedido = new Pedido(numero, dataPedido, mesa, valor, descricao);
                        try {
                            daoArquivoPedido.add(pedido);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        System.out.println(pedido.toString());
                        JOptionPane.showMessageDialog(null, "Pedido Cadastrado!");
                    }
            }
        });
    }

    private void createUIComponents() {

        ImageIcon imageIcon = new ImageIcon("imagens/notepad.png");
        imagePedido = new JLabel(imageIcon);

        ImageIcon imageIcon1 = new ImageIcon("imagens/salvar.png");
        salvarButton = new JButton(imageIcon1);

        ImageIcon imageIcon2 = new ImageIcon("imagens/limpar.png");
        limparButton = new JButton(imageIcon2);


    }

    private void installFormatters (){
        MaskFormatter formatter = null;
        MaskFormatter formatter1 = null;

        try {
            formatter = new MaskFormatter("########");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatter.install(numPedido);

        try {
            formatter1 = new MaskFormatter("#####.##");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatter1.install(valorPedido);
    }

    private boolean exite () {
        try {
            if(daoArquivoPedido.getAll() != null) {
                for (Pedido p : daoArquivoPedido.getAll()) {
                    if ((p.getNumeroPedido().equals(numPedido.getText()))) {
                        return true;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    private boolean isEmpity(){
        if ((numPedido.getText().equals(""))&&(valorPedido.getText().equals(""))
                &&((Integer)spinnerMesa.getValue()==0)&&(descricaoPedido.getText().equals(""))){
            return true;
        }
        return false;
    }
}
