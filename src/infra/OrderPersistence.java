package infra;

import business.model.Order;
import util.InfraException;

import java.io.*;
import java.util.ArrayList;

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

    public ArrayList<Order> load() throws InfraException {
        try {
            if(!this.file.exists()) {
                this.file.createNewFile();
            }

            FileInputStream fis = new FileInputStream(this.file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Order> orders = (ArrayList) ois.readObject();

            ois.close();
            fis.close();

            return orders;
        } catch (EOFException e) {
            return new ArrayList<Order>();
        } catch (IOException | ClassNotFoundException e) {
//            System.err.println(e);
            throw new InfraException("An unexpected error has occurred on load.");
        }
    }

    public void save(ArrayList<Order> orders) throws InfraException{
        try{
            this.file.createNewFile();
            FileOutputStream fos = new FileOutputStream(this.file, false);
            System.out.println("chamou1");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            System.out.println("chamou 2");
            oos.writeObject(orders); //aqui
            oos.close();
            fos.close();
            System.out.println("chamou 3");
        } catch (IOException e) {
//            System.err.println(e);
            throw new InfraException("An unexpected error has occurred on save.");
        }
    }
}
