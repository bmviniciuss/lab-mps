package business.model;

import java.io.Serializable;

public interface IUser extends Serializable {
    public String getLogin();
    public String getPassword();
    public Date getBirthdate();
}