package business.model;

import java.util.ArrayList;

public class AdminUser implements IUser {
    private String login;
    private String password;
    private Date birthdate;

    public AdminUser(String login, String password, Date birthdate) {
        this.login = login;
        this.password = password;
        this.birthdate = birthdate;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Date getBirthdate() {
        return this.birthdate;
    }

    @Override
    public int compareTo(IUser user) {
        return this.getLogin().compareTo(user.getLogin());
    }
}
