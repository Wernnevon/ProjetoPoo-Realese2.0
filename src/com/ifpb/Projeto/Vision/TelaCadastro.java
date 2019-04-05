package com.ifpb.Projeto.Vision;

import Dao.DaoArquivoFuncionario;
import com.ifpb.Projeto.modelo.Funcionario;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TelaCadastro extends JFrame {
    private JTextField campoNome;
    private JComboBox comboBoxFunc;
    private JFormattedTextField dataNasc;
    private JFormattedTextField campoCpf;
    private JButton salvarButton;
    private JButton limparButton;
    private JPanel painel;
    private JLabel img;

    private DaoArquivoFuncionario daoArquivoFuncionario;

    public TelaCadastro() {
        super("Cadastro de Funcionários");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 700);
        setContentPane(painel);
        ImageIcon icon = new ImageIcon("imagens/iconAdd.png");
        setIconImage(icon.getImage());

        try {
            this.daoArquivoFuncionario = new DaoArquivoFuncionario();
        } catch (IOException e) {
            e.printStackTrace();
        }

        limparButton.addActionListener(e -> {

            dataNasc.setText("");
            campoCpf.setText("");
            campoNome.setText("");
            comboBoxFunc.setSelectedIndex(0);

        });
        salvarButton.addActionListener(e -> {
            try {
                if (exists()){

                }else {
                    String cpf = campoCpf.getText();
                    String nome = campoNome.getText();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate nascimento = LocalDate.parse(dataNasc.getText(), formatter);
                    Funcao funcao = (Funcao) comboBoxFunc.getSelectedItem();
                    if ((cpf == "") && (nome == "") && (nascimento == null)) {
                        JOptionPane.showMessageDialog(null, "Por favor preencha todos os campos!");
                    }
                    Funcionario funcionario = new Funcionario(nome, cpf, nascimento, funcao);
                    try {
                        daoArquivoFuncionario.add(funcionario);
                        JOptionPane.showMessageDialog(null, "Pedigo Armazenado!");
                    } catch (IOException | ClassNotFoundException e1) {
                        JOptionPane.showMessageDialog(null, "O pedido não foi armazenado!  Tente novamente.");
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

//            new TelaBusca().setVisible(true);
//            this.dispose();
        });
    }

    private void createUIComponents() {
        //criando combobox
        comboBoxFunc = new JComboBox(Funcao.values());

        ImageIcon btSave = new ImageIcon("imagens/salvar.png");
        salvarButton = new JButton(btSave);

        ImageIcon btClear = new ImageIcon("imagens/limpar.png");
        limparButton = new JButton(btClear);

        //criando mascara para cpf
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("###.###.###-##");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        campoCpf = new JFormattedTextField();
        formatter.install(campoCpf);

        //criando mascara para nascimento

        MaskFormatter formatter1 = null;
        try {
            formatter1 = new MaskFormatter("##/##/####");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dataNasc = new JFormattedTextField();
        formatter1.install(dataNasc);

        ImageIcon img2 = new ImageIcon("imagens/moreOne.png");
        img = new JLabel(img2);

    }

    private boolean exists() throws IOException, ClassNotFoundException {
        for (Funcionario f : daoArquivoFuncionario.getAll()) {
            if (f.getCpf().equals(campoCpf.getText())) {
                return true;
            }
        }
        return false;

    }
}