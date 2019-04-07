package com.ifpb.Projeto.Vision;

import Dao.DaoArquivoFuncionario;
import com.ifpb.Projeto.modelo.Funcionario;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class TelaBusca extends JFrame{
    private JPanel buscar;
    private JTextField nomeBuscar;
    private JButton buttonBuscar;
    private JButton voltarButton;
    private JLabel imgSearch;

    private DaoArquivoFuncionario daoArquivoFuncionario;

    public TelaBusca(){
        super("Busca de funcionários");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(500,500);
        setContentPane(buscar);
        ImageIcon icone = new ImageIcon("imagens/Search.png");
        setIconImage(icone.getImage());
        try {
            this.daoArquivoFuncionario = new DaoArquivoFuncionario();
        } catch (IOException e) {
            e.printStackTrace();
        }

        voltarButton.addActionListener(e->{
            this.dispose();
        });

        buttonBuscar.addActionListener(e->{
            //imprime um funcionário ou lsta de funcionários cadastrado na tela, caso n exista uma janela sera aberta
            //informando que o funcionário não está cadastrado ou o nome está errado;
            List<Funcionario> matches;
            try {

                matches = daoArquivoFuncionario.getAllStartsWithName(nomeBuscar.getText());

                StringBuilder builder = new StringBuilder();
                for (Funcionario f : matches) {
                    builder.append("\n")
                            .append("NOME: ")
                            .append(f.getNome())
                            .append("\n")
                            .append("Função: ")
                            .append(f.getFuncao())
                            .append("\n");
                }

                if (matches == null || matches.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nao foi encontrado nenhum funcionario!");
                } else {
                    JOptionPane.showMessageDialog(null, "Funcionarios Encontrados: \n" + builder.toString());
                }

            } catch (IOException | ClassNotFoundException e1) {
                e1.printStackTrace();
            }

        });


    }

    private void createUIComponents() {
        ImageIcon iconSearch = new ImageIcon("imagens/searchEmploye.png");
        imgSearch = new JLabel(iconSearch);

        ImageIcon btBusaca = new ImageIcon("imagens/lupinha.png");
        buttonBuscar = new JButton(btBusaca);

        ImageIcon btVoltar = new ImageIcon("imagens/return.png");
        voltarButton = new JButton(btVoltar);

    }
}
