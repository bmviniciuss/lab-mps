package infra;

import business.model.IUser;
import util.InfraException;
import java.io.*;
import java.util.TreeSet;

public class AdminUserPersistence implements IUserPersistence {
    private static AdminUserPersistence adminUserPersistence;
    private String fileName;
    private File file;

    private AdminUserPersistence() {
        this.fileName = "admins.ser";
        this.file = new File(this.fileName);
    }

    public static AdminUserPersistence getInstance() {
        if(adminUserPersistence == null) {
            adminUserPersistence = new AdminUserPersistence();
        }
        return adminUserPersistence;
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
            throw new InfraException("An unexpected error has occurred.");
        }
    }
}
