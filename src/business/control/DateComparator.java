package business.control;

import business.model.IUser;

import java.util.Comparator;

public class DateComparator implements Comparator<IUser> {
    public int compare(IUser user1, IUser user2){
        if (user1.getBirthdate().getYear() < user2.getBirthdate().getYear())
            return -1;
        if (user1.getBirthdate().getYear() > user2.getBirthdate().getYear())
            return 1;
        if (user1.getBirthdate().getYear() == user2.getBirthdate().getYear())
            if (user1.getBirthdate().getMonth() < user2.getBirthdate().getYear())
                return -1;
        if (user1.getBirthdate().getMonth() > user2.getBirthdate().getMonth())
            return 1;
        if (user1.getBirthdate().getMonth() == user2.getBirthdate().getMonth())
            if(user1.getBirthdate().getDay() < user2.getBirthdate().getDay())
                return -1;
        if(user1.getBirthdate().getDay() > user2.getBirthdate().getDay())
            return 1;
        else
            return 0;
    }
}
