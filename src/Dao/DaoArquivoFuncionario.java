package Dao;
import com.ifpb.Projeto.modelo.Funcionario;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class DaoArquivoFuncionario {
    private File archive;
    List<Funcionario> corpoFunc;

    public DaoArquivoFuncionario() throws IOException {
        this.archive = new File("Funcionarios.txt");
        if(!this.archive.exists()) this.archive.createNewFile();

    }

    public boolean add(Funcionario t) throws IOException, ClassNotFoundException {
        corpoFunc = this.getAll();
        if(!objExist(t)){
            corpoFunc.add(t);
            writeArchive();
            return true;
        }
        return false;
    }

    public boolean edit(Funcionario t) throws IOException, ClassNotFoundException {
        corpoFunc = this.getAll();
        if (objExist(t)){
            corpoFunc.remove(t);
            corpoFunc.add(t);
            return true;
        }
        return false;
    }

    public boolean remove(Funcionario t) throws IOException {
        if(objExist(t)){
            corpoFunc.remove(t);
            writeArchive();
            return true;
        }
        return false;
    }

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

    public boolean objExist(Funcionario t){
        for(Funcionario i : corpoFunc){
            if(i.getCpf().equals(t.getCpf()))
                return true;
        }
        return false;
    }

    public void writeArchive() throws IOException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archive));
        out.writeObject(corpoFunc);
        out.close();
    }
}
