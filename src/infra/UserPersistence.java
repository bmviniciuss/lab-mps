package infra;

import business.control.UserController;
import util.InfraException;

import java.io.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;


public abstract class UserPersistence {

    public static void save(UserController uc) throws InfraException {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("users.ser", false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(uc);
            oos.close();
        } catch (FileNotFoundException ex) {
            throw new InfraException("Erro na escrita do arquivo.");
            //Logger.getLogger(Serializer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            throw new InfraException("Erro no fluxo de saída do arquivo.");
            //Logger.getLogger(Serializer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static UserController load() throws InfraException{
        FileInputStream fis;
        try {
            fis = new FileInputStream("users.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            UserController uc = (UserController) ois.readObject();
            ois.close();
            return uc;
        } catch (FileNotFoundException ex) {
            throw new InfraException("Erro na leitura do arquivo.");
            //Logger.getLogger(Serializer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            throw new InfraException("Erro no fluxo de entrada do arquivo.");
            //Logger.getLogger(Serializer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            throw new InfraException("Erro na construção do modelo de dados.");
            //Logger.getLogger(Serializer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
