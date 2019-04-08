package Dao;

import com.ifpb.Projeto.modelo.Pedido;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Arquivo que armazena os pedidos
 */
public class DaoArquivoPedido {
    private File archive;
    List<Pedido> pedidos;

    public DaoArquivoPedido() throws IOException {
        this.archive = new File("Lista de Pedidos.txt");
        if(!this.archive.exists()) this.archive.createNewFile();

    }

    /**
     * Metodo que adiciona um pedido
     * @param t
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean add(Pedido t) throws IOException, ClassNotFoundException {
        pedidos = this.getAll();
        if(!objExist(t)){
            pedidos.add(t);
            writeArchive();
            return true;
        }
        return false;
    }

    /**
     * Metodo que edita um pedido
     * @param t
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean edit(Pedido t) throws IOException, ClassNotFoundException {
        pedidos = this.getAll();
        if (objExist(t)){
            pedidos.remove(t);
            pedidos.add(t);
            return true;
        }
        return false;
    }

    /**
     * Metodo que remove um pedido
     * @param t
     * @return
     * @throws IOException
     */
    public boolean remove(Pedido t) throws IOException {
        if(objExist(t)){
            pedidos.remove(t);
            writeArchive();
            return true;
        }
        return false;
    }

    /**
     * Metodo que retorna um pedido procurado
     * @param nameSearch
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<Pedido> getAllStartsWithName(String nameSearch) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        if(this.archive.length()> 0){
            in = new ObjectInputStream(new FileInputStream(archive));
            pedidos = (ArrayList<Pedido>) in.readObject();
            in.close();
            return pedidos.stream()
                    .filter((pedido) -> pedido.getNumeroPedido().toLowerCase().startsWith(nameSearch.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * Metodo que retorna todos os pedidos
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<Pedido> getAll() throws IOException, ClassNotFoundException {
        ObjectInputStream in;
        if(this.archive.length()> 0){
            in = new ObjectInputStream(new FileInputStream(archive));
            pedidos = (ArrayList<Pedido>) in.readObject();
            in.close();
            return pedidos;
        }
        return new ArrayList<>();
    }

    /**
     * Metodo que verifica se um pedido exsite
     * @param t
     * @return
     */
    public boolean objExist(Pedido t){
        for(Pedido i : pedidos){
            if(i.getNumeroPedido().equals(t.getNumeroPedido()))
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
        out.writeObject(pedidos);
        out.close();
    }
}
