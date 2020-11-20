package business.control;

import business.model.IUser;
import java.util.Comparator;

public class LoginComparator implements Comparator<IUser> {
    @Override
    public int compare(IUser user1, IUser user2) {
        return user1.compareTo(user2);
    }
}
