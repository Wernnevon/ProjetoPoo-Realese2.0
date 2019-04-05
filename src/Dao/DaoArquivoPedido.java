package Dao;

import com.ifpb.Projeto.modelo.Pedido;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DaoArquivoPedido {
    private File archive;
    List<Pedido> comanda;

    public DaoArquivoPedido() throws IOException {
        this.archive = new File("Pedidos.txt");
        if(!this.archive.exists()) this.archive.createNewFile();

    }

    public boolean add(Pedido t) throws IOException, ClassNotFoundException {
        comanda = this.getAll();
        if(!objExist(t)){
            comanda.add(t);
            writeArchive();
            return true;
        }
        return false;
    }

    public boolean edit(Pedido t) throws IOException, ClassNotFoundException {
        comanda = this.getAll();
        if (objExist(t)){
            comanda.remove(t);
            comanda.add(t);
            return true;
        }
        return false;
    }

    public boolean remove(Pedido t) throws IOException {
        if(objExist(t)){
            comanda.remove(t);
            writeArchive();
            return true;
        }
        return false;
    }

    public List<Pedido> getAllStartsWithName(String nameSearch) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        if(this.archive.length()> 0){
            in = new ObjectInputStream(new FileInputStream(archive));
            comanda = (ArrayList<Pedido>) in.readObject();
            in.close();
            return comanda.stream()
                    .filter((pedido) -> pedido.getNumeroPedido().toLowerCase().startsWith(nameSearch.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public List<Pedido> getAll() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        if(this.archive.length()> 0){
            in = new ObjectInputStream(new FileInputStream(archive));
            comanda = (ArrayList<Pedido>) in.readObject();
            in.close();
            return comanda;
        }
        return new ArrayList<>();
    }

    public boolean objExist(Pedido t){
        for(Pedido i : comanda){
            if(i.getNumeroPedido().equals(t.getNumeroPedido()))
                return true;
        }
        return false;
    }

    public void writeArchive() throws IOException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archive));
        out.writeObject(comanda);
        out.close();
    }
}
