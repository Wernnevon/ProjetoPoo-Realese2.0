package com.ifpb.Projeto.Vision;

import Dao.DaoArquivoFuncionario;
import com.ifpb.Projeto.modelo.Funcionario;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.time.format.ResolverStyle.LENIENT;

/**
 * Classe que cadastra um funcionário no restaurante
 */
public class TelaCadastro extends JFrame {
    private JTextField campoNome;
    private JComboBox comboBoxFunc;
    private JFormattedTextField dataNasc;
    private JFormattedTextField campoCpf;
    private JButton salvarButton;
    private JButton limparButton;
    private JPanel painel;
    private JLabel img;
    private JButton menuButton;

    private DaoArquivoFuncionario daoArquivoFuncionario;

    public TelaCadastro() {
        super("Cadastro de Funcionários");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(500, 700);
        setContentPane(painel);
        ImageIcon icon = new ImageIcon("imagens/iconAdd.png");
        setIconImage(icon.getImage());

        try {
            this.daoArquivoFuncionario = new DaoArquivoFuncionario();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Botão que seta os campos preenchidos
         */
        limparButton.addActionListener(e -> {

            dataNasc.setText("");
            campoCpf.setText("");
            campoNome.setText("");
            comboBoxFunc.setSelectedIndex(0);

        });
        salvarButton.addActionListener(e -> {
            try {
                if (exists()){
                    JOptionPane.showMessageDialog(null, "CPF ja pertence a um funcionario!");
                }else {
                    if ((campoCpf.getText().equals("")) || (campoNome.getText().equals("")) || (dataNasc.getText().equals(""))) {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                    }else {

                        String cpf = campoCpf.getText();
                        String nome = campoNome.getText();
                        Funcao funcao = (Funcao) comboBoxFunc.getSelectedItem();

                        LocalDate nascimento = LocalDate.MIN;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                        try{
                            nascimento = LocalDate.parse(dataNasc.getText(),formatter);
                            LocalDate nascimentoComp = LocalDate.parse(dataNasc.getText(),formatter.withResolverStyle(LENIENT));
                            LocalDate dataAtual = LocalDate.now();
                            if (nascimentoComp.compareTo(dataAtual) < -17) {
                                System.out.println(nascimentoComp.compareTo(dataAtual));
                                if (nascimentoComp.getMonthValue() > nascimento.getMonthValue()) {
                                    JOptionPane.showMessageDialog(null, "O dia excede o limite de dias do mês digitado, que são "
                                                    + nascimento.lengthOfMonth() + " dias.",
                                            "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    Funcionario funcionario = new Funcionario(nome, cpf, nascimento, funcao);
                                    try {
                                        daoArquivoFuncionario.add(funcionario);
                                        JOptionPane.showMessageDialog(null, "Funcionário Cadastrado!");
                                    } catch (IOException | ClassNotFoundException e1) {
                                        JOptionPane.showMessageDialog(null, "Funcionario não cadastrado!  Tente novamente.");
                                    }
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Não é permitido funcionários menores de 18 anos!");
                            }
                        }catch (DateTimeParseException ex){
                            JOptionPane.showMessageDialog(null, "Data inválida!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

//            new TelaBusca().setVisible(true);
//            this.dispose();
        });
        /**
         * Botão que volta para o menu principal
         */
        menuButton.addActionListener(e-> {
            this.dispose();
        });
    }

    private void createUIComponents() {
        //criando combobox
        comboBoxFunc = new JComboBox(Funcao.values());

        ImageIcon btSave = new ImageIcon("imagens/salvar.png");
        salvarButton = new JButton(btSave);

        ImageIcon btClear = new ImageIcon("imagens/limpar.png");
        limparButton = new JButton(btClear);

        ImageIcon btMenu = new ImageIcon("imagens/menuIcon.png");
        menuButton = new JButton(btMenu);

        /**
         * Aplicação das mascaras em alguns campos de texto
         */
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

    /**
     * metodo para verificar se existe um cpf
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private boolean exists() throws IOException, ClassNotFoundException {
        for (Funcionario f : daoArquivoFuncionario.getAll()) {
            if (f.getCpf().equals(campoCpf.getText())) {
                return true;
            }
        }
        return false;

    }
}