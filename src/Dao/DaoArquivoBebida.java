package Dao;

import com.ifpb.Projeto.modelo.Bebida;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DaoArquivoBebida {
    private File file;
    List<Bebida> adega;

    public DaoArquivoBebida() throws IOException {
        this.file = new File("Bebidas.txt");
        if(!this.file.exists()) this.file.createNewFile();

    }

    public boolean add(Bebida t) throws IOException, ClassNotFoundException {
        adega = this.getAll();
        if(!objExist(t)){
            adega.add(t);
            writeArchive();
            return true;
        }
        return false;
    }

    public boolean edit(Bebida t) throws IOException, ClassNotFoundException {
        adega = this.getAll();
        if (objExist(t)){
            adega.remove(t);
            adega.add(t);
            return true;
        }
        return false;
    }

    public boolean remove(Bebida t) throws IOException {
        if(objExist(t)){
            adega.remove(t);
            writeArchive();
            return true;
        }
        return false;
    }

    public List<Bebida> getAllStartsWithName(String nameSearch) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        if(this.file.length()> 0){
            in = new ObjectInputStream(new FileInputStream(file));
            adega = (ArrayList<Bebida>) in.readObject();
            in.close();
            return adega.stream()
                    .filter((bebida) -> bebida.getNome() .toLowerCase().startsWith(nameSearch.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public List<Bebida> getAll() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        if(this.file.length()> 0){
            in = new ObjectInputStream(new FileInputStream(file));
            adega = (ArrayList<Bebida>) in.readObject();
            in.close();
            return adega;
        }
        return new ArrayList<>();
    }

    public boolean objExist(Bebida t){
        for(Bebida i : adega){
            if (!(i.getCodProduto().equals(t.getCodProduto()))) {
                continue;
            }
            return true;
        }
        return false;
    }

    public void writeArchive() throws IOException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(adega);
        out.close();
    }
}
