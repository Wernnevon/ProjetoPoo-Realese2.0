package Dao;
import com.ifpb.Projeto.modelo.Funcionario;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Arquivo que aramazenas os funcionários
 */
public class DaoArquivoFuncionario {
    private File archive;
    List<Funcionario> corpoFunc;

    public DaoArquivoFuncionario() throws IOException {
        this.archive = new File("Funcionarios.txt");
        if(!this.archive.exists()) this.archive.createNewFile();

    }

    /**
     * Metodo que adiciona um funcionario
     * @param t
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean add(Funcionario t) throws IOException, ClassNotFoundException {
        corpoFunc = this.getAll();
        if(!objExist(t)){
            corpoFunc.add(t);
            writeArchive();
            return true;
        }
        return false;
    }

    /**
     * Metodo que edita um funcionario
     * @param t
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean edit(Funcionario t) throws IOException, ClassNotFoundException {
        corpoFunc = this.getAll();
        if (objExist(t)){
            corpoFunc.remove(t);
            corpoFunc.add(t);
            return true;
        }
        return false;
    }

    /**
     * Metodo que remove um funcionário
     * @param t
     * @return
     * @throws IOException
     */
    public boolean remove(Funcionario t) throws IOException {
        if(objExist(t)){
            corpoFunc.remove(t);
            writeArchive();
            return true;
        }
        return false;
    }

    /**
     * Metodo que retorna um funcionário pesquisado
     * @param nameSearch
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<Funcionario> getAllStartsWithName(String nameSearch) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        if(this.archive.length()> 0){
            in = new ObjectInputStream(new FileInputStream(archive));
            corpoFunc = (ArrayList<Funcionario>) in.readObject();
            in.close();
            return corpoFunc.stream()
                    .filter((funcionario) -> funcionario.getNome().toLowerCase().startsWith(nameSearch.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * Metodo que retorna todos funcionários
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<Funcionario> getAll() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        if(this.archive.length()> 0){
            in = new ObjectInputStream(new FileInputStream(archive));
            corpoFunc = (ArrayList<Funcionario>) in.readObject();
            in.close();
            return corpoFunc;
        }
        return new ArrayList<>();
    }

    /**
     * Metodo que verifica se um funcionario existe
     * @param t
     * @return
     */
    public boolean objExist(Funcionario t){
        for(Funcionario i : corpoFunc){
            if(i.getCpf().equals(t.getCpf()))
                return true;
        }
        return false;
    }

    /**
     * Metodo que escreve em um arquivo
     * @throws IOException
     */
    public void writeArchive() throws IOException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archive));
        out.writeObject(corpoFunc);
        out.close();
    }
}
