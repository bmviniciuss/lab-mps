package business.control;

import business.model.IUser;

import java.util.Comparator;

public class DateComparator implements Comparator<IUser> {
    public int compare(IUser user1, IUser user2){
        if (user1.getBirth_date().getYear() < user2.getBirth_date().getYear())
            return -1;
        if (user1.getBirth_date().getYear() > user2.getBirth_date().getYear())
            return 1;
        if (user1.getBirth_date().getYear() == user2.getBirth_date().getYear())
            if (user1.getBirth_date().getMonth() < user2.getBirth_date().getYear())
                return -1;
        if (user1.getBirth_date().getMonth() > user2.getBirth_date().getMonth())
            return 1;
        if (user1.getBirth_date().getMonth() == user2.getBirth_date().getMonth())
            if(user1.getBirth_date().getDay() < user2.getBirth_date().getDay())
                return -1;
        if(user1.getBirth_date().getDay() > user2.getBirth_date().getDay())
            return 1;
        else
            return 0;
    }
}
