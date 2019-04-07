package com.ifpb.Projeto.Vision;

import Dao.DaoArquivoBebida;
import Dao.DaoArquivoPrato;
import com.ifpb.Projeto.modelo.Bebida;
import com.ifpb.Projeto.modelo.Prato;
import com.ifpb.Projeto.modelo.Produto;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.io.IOException;
import java.text.ParseException;

public class CadastroProduto extends JFrame{

    private JPanel cadastroProdutos;
    private JTextField campoNome;
    private JComboBox comboBoxTipo;
    private JFormattedTextField campoCod;
    private JComboBox comboBoxClass;
    private JFormattedTextField campoValor;
    private JButton salvarButton;
    private JButton limparButton;
    private JLabel imgProd;
    private JSpinner spinnerQuantidade;
    private JSpinner spinnerIdade;

    private DaoArquivoBebida daoArquivoBebida;
    private DaoArquivoPrato daoArquivoPrato;

    public CadastroProduto(){
        super("Cadastro de Produtos");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(500,700);
        setContentPane(cadastroProdutos);
        ImageIcon icone = new ImageIcon("imagens/addProdutos.png");
        setIconImage(icone.getImage());
        spinnerIdade.setModel(new SpinnerNumberModel(0, 0, 100, 1));
        spinnerQuantidade.setModel(new SpinnerNumberModel(0, 0, 100, 1));

        limparButton.addActionListener(e -> {
            campoNome.setText("");
            comboBoxTipo.setSelectedIndex(0);
            campoCod.setText("");
            comboBoxClass.setSelectedIndex(0);
            campoValor.setText("");
            spinnerIdade.setValue(0);
            spinnerQuantidade.setValue(0);
        });

        salvarButton.addActionListener(e->{
            try {
                this.daoArquivoBebida = new DaoArquivoBebida();
                this.daoArquivoPrato = new DaoArquivoPrato();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                if (exists() || campoVazio()){
                    JOptionPane.showMessageDialog(null, "Código já existe no sistema \n\n Ou Campos estão vazios");
                }
                else {

                    if (comboBoxTipo.getSelectedItem() == Tipo.PRATO) {
                        String nome = campoNome.getText();
                        String codigoProduto = campoCod.getText();
                        double valor = Double.parseDouble(campoValor.getText());
                        int quantidade = (Integer) spinnerQuantidade.getValue();
                        Tipo tipo = (Tipo) comboBoxTipo.getSelectedItem();

                        Prato prato = new Prato(nome, codigoProduto, valor, quantidade, tipo);
                        try {
                            daoArquivoPrato.add(prato);
                        } catch (IOException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        System.out.println(nome + " " + codigoProduto + " " + valor + " " + quantidade + "\n" + tipo);
                        JOptionPane.showMessageDialog(null, "Prato Salvo Com Sucesso!");

                    } else {
                        String nome = campoNome.getText();
                        String codigoProduto = campoCod.getText();
                        double valor = Double.parseDouble(campoValor.getText());
                        int quantidade = (Integer) spinnerQuantidade.getValue();
                        Tipo tipo = (Tipo) comboBoxTipo.getSelectedItem();
                        int idade = (Integer) spinnerIdade.getValue();
                        Classe classe = (Classe) comboBoxClass.getSelectedItem();
                        System.out.println(nome + " " + codigoProduto + " " + valor + " " + quantidade + "\n" + tipo + " " + idade + " " + classe);

                        Bebida bebida = new Bebida(nome, codigoProduto, valor, quantidade, classe, idade);

                        try {
                            daoArquivoBebida.add(bebida);
                        } catch (IOException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        //método pra salvar bebida
                        JOptionPane.showMessageDialog(null, "Bebida Salvo Com Sucesso!");
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void createUIComponents() {
       ImageIcon iconeImg = new ImageIcon("imagens/produtos.png");
       imgProd = new JLabel(iconeImg);

       ImageIcon btSave = new ImageIcon("imagens/salvar.png");
       salvarButton = new JButton(btSave);

       ImageIcon btClear = new ImageIcon("imagens/limpar.png");
       limparButton = new JButton(btClear);

        MaskFormatter mascara = null;

        try {
           mascara = new MaskFormatter("########");
        } catch (ParseException e) {
            e.printStackTrace();
        } campoCod = new JFormattedTextField();
        mascara.install(campoCod);

        try {
            mascara = new MaskFormatter("#####.##");
        } catch (ParseException e) {
            e.printStackTrace();
        } campoValor = new JFormattedTextField();
        mascara.install(campoValor);

        comboBoxClass = new JComboBox(Classe.values());
        comboBoxTipo = new JComboBox(Tipo.values());


    }
    private boolean exists () throws IOException, ClassNotFoundException {
            for (Produto p : daoArquivoPrato.getAll()) {
                if (p.getCodProduto().equals(campoCod.getText())) {
                    return true;
                }
            }
            for (Produto p : daoArquivoBebida.getAll()) {
                if (p.getCodProduto().equals(campoCod.getText())) {
                    return true;
                }
            }
        return false;

    }
    private boolean campoVazio (){
        if ((campoCod.getText()=="")||(campoNome.getText()=="")||(campoValor.getText()=="")||(spinnerQuantidade.getValue()=="0")){
            return true;
        }
        return false;
    }
}
