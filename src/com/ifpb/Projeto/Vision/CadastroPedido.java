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

/**
 * Classe que cadastra os pedidos realizados
 */
public class CadastroPedido extends JFrame{
    private JPanel painelPedido;
    private JFormattedTextField numPedido;
    private JFormattedTextField valorPedido;
    private JTextArea descricaoPedido;
    private JSpinner spinnerMesa;
    private JButton salvarButton;
    private JButton limparButton;
    private JLabel imagePedido;
    private JButton menuButton;
    private Pedido pedido;

    private DaoArquivoPedido daoArquivoPedido;

    public CadastroPedido() throws IOException {
        super("Controle de Pedidos");
        setContentPane(painelPedido);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(600, 700);
        ImageIcon imageIcon = new ImageIcon("imagens/notepadFundo.png");
        setIconImage(imageIcon.getImage());
        spinnerMesa.setModel(new SpinnerNumberModel(0, 0, 10, 1));
        installFormatters();

        daoArquivoPedido = new DaoArquivoPedido();
        /**
         * Botão que seta os campos preenchidos
         */
        limparButton.addActionListener(e->{
            numPedido.setText("");
            valorPedido.setText("");
            descricaoPedido.setText("");
            spinnerMesa.setValue(0);

        });

        /**
         * Botão que salva os pedidos realizados
         */
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
        menuButton.addActionListener(e->{
           this.dispose();
        });
    }

    private void createUIComponents() {

        ImageIcon imageIcon = new ImageIcon("imagens/notepad.png");
        imagePedido = new JLabel(imageIcon);

        ImageIcon imageIcon1 = new ImageIcon("imagens/salvar.png");
        salvarButton = new JButton(imageIcon1);

        ImageIcon imageIcon2 = new ImageIcon("imagens/limpar.png");
        limparButton = new JButton(imageIcon2);

        ImageIcon btMenu = new ImageIcon("imagens/menuIcon.png");
        menuButton = new JButton(btMenu);


    }

    /**
     * Função que aplica mascara em alguns campos de texto
     */
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

    /**
     * Metodo que retorna dos os pedidos
     * @return true
     */
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

    /**
     * Metodo que verifica se os campos estão vazios
     * @return
     */
    private boolean isEmpity(){
        if ((numPedido.getText().equals(""))&&(valorPedido.getText().equals(""))
                &&((Integer)spinnerMesa.getValue()==0)&&(descricaoPedido.getText().equals(""))){
            return true;
        }
        return false;
    }
}
