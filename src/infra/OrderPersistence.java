package infra;

import business.model.OrderInterface;
import util.InfraException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderPersistence {
    private static OrderPersistence orderPersistence;
    private String fileName;
    private File file;

    private OrderPersistence() {
        this.fileName = "orders.ser";
        this.file = new File(this.fileName);
    }

    public static OrderPersistence getInstance() {
        if(orderPersistence == null) {
            orderPersistence = new OrderPersistence();
        }
        return orderPersistence;
    }

    public List<OrderInterface> load() throws InfraException {
        try {
            if(!this.file.exists()) {
                this.file.createNewFile();
            }

            FileInputStream fis = new FileInputStream(this.file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<OrderInterface> orders = (List) ois.readObject();

            ois.close();
            fis.close();

            return orders;
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
//            System.err.println(e);
            throw new InfraException("An unexpected error has occurred on load.");
        }
    }

    public void save(List<OrderInterface> orders) throws InfraException{
        try{
            this.file.createNewFile();
            FileOutputStream fos = new FileOutputStream(this.file, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(orders); //aqui
            oos.close();
            fos.close();
        } catch (IOException e) {
//            System.err.println(e);
            throw new InfraException("An unexpected error has occurred on save.");
        }
    }
}
