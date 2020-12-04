package infra;

import business.model.Checkout;
import business.model.OrderInterface;
import util.InfraException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CheckoutPersistence {
    private static CheckoutPersistence persistence;
    private String fileName;
    private File file;

    public CheckoutPersistence() {
        this.fileName = "checkouts.ser";
        this.file = new File(this.fileName);
    }

    public static CheckoutPersistence getInstance() {
        if(persistence == null) {
            persistence = new CheckoutPersistence();
        }
        return persistence;
    }

    public List<Checkout> load() throws InfraException {
        try {
            if(!this.file.exists()) {
                this.file.createNewFile();
            }

            FileInputStream fis = new FileInputStream(this.file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Checkout> checkouts = (List) ois.readObject();

            ois.close();
            fis.close();

            return checkouts;
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e);
            throw new InfraException("An unexpected error has occurred on load.");
        }
    }

    public void save(List<Checkout> checkouts) throws InfraException{
        try{
            this.file.createNewFile();
            FileOutputStream fos = new FileOutputStream(this.file, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(checkouts);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.err.println(e);
            throw new InfraException("An unexpected error has occurred on save.");
        }
    }
}
