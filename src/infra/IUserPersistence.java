package infra;

import business.model.IUser;
import util.InfraException;

import java.util.TreeSet;

public interface IUserPersistence {
    public TreeSet<IUser> load() throws InfraException;
    public void save(TreeSet<IUser> users) throws InfraException;
}
