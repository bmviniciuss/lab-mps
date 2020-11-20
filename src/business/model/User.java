package business.model;

public class User implements IUser, Comparable<User>{
    private String login;
    private String password;
    private Date birthdate;

    public User(String login, String password, Date birthdate) {
        this.login = login;
        this.password = password;
        this.birthdate = birthdate;
    }

    @Override
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Date getBirthdate() {
        return this.birthdate;
    }

    @Override
    public int compareTo(User u) {
        return this.getLogin().compareTo(u.getLogin());
    }
}
