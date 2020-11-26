package infra;

import business.model.IUser;
import util.InfraException;

import java.io.*;
import java.util.TreeSet;


public class UserPersistence implements IUserPersistence  {
    private String fileName;
    private File file;

    public UserPersistence() {
        this.fileName = "users.ser";
        this.file = new File(this.fileName);
    }

    @Override
    public TreeSet<IUser> load() throws InfraException {
        try {
            if(!this.file.exists()) {
                this.file.createNewFile();
            }

            FileInputStream fis = new FileInputStream(this.file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            TreeSet<IUser> users = (TreeSet) ois.readObject();

            ois.close();
            fis.close();

            return users;
        } catch (EOFException e) {
            return new TreeSet<IUser>();
        } catch (IOException | ClassNotFoundException e) {
//            System.err.println(e);
            throw new InfraException("An unexpected error has occurred.");
        }
    }

    @Override
    public void save(TreeSet<IUser> users) throws InfraException {
        try {
            this.file.createNewFile();
            FileOutputStream fos = new FileOutputStream(this.file, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(users);
            oos.close();
            fos.close();

        } catch (IOException e) {
//            System.err.println(e);
            throw new InfraException("An unexpected error has occurred.");
        }
    }
}
