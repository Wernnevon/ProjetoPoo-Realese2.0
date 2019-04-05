package Dao;

import com.ifpb.Projeto.modelo.Prato;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DaoArquivoPrato {
    private File archive;
    List<Prato> cardapio;

    public DaoArquivoPrato() throws IOException {
        this.archive = new File("Prato.txt");
        if(!this.archive.exists()) this.archive.createNewFile();

    }

    public boolean add(Prato t) throws IOException, ClassNotFoundException {
        cardapio = this.getAll();
        if(!objExist(t)){
            cardapio.add(t);
            writeArchive();
            return true;
        }
        return false;
    }

    public boolean edit(Prato t) throws IOException, ClassNotFoundException {
        cardapio = this.getAll();
        if (objExist(t)){
            cardapio.remove(t);
            cardapio.add(t);
            return true;
        }
        return false;
    }

    public boolean remove(Prato t) throws IOException {
        if(objExist(t)){
            cardapio.remove(t);
            writeArchive();
            return true;
        }
        return false;
    }

    public List<Prato> getAllStartsWithName(String nameSearch) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        if(this.archive.length()> 0){
            in = new ObjectInputStream(new FileInputStream(archive));
            cardapio = (ArrayList<Prato>) in.readObject();
            in.close();
            return cardapio.stream()
                    .filter((prato) -> prato.getNome() .toLowerCase().startsWith(nameSearch.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public List<Prato> getAll() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        if(this.archive.length()> 0){
            in = new ObjectInputStream(new FileInputStream(archive));
            cardapio = (ArrayList<Prato>) in.readObject();
            in.close();
            return cardapio;
        }
        return new ArrayList<>();
    }

    public boolean objExist(Prato t){
        for(Prato i : cardapio){
            if (!(i.getCodProduto().equals(t.getCodProduto()))) {
                continue;
            }
            return true;
        }
        return false;
    }

    public void writeArchive() throws IOException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archive));
        out.writeObject(cardapio);
        out.close();
    }
}
