package business.model;

public class User implements IUser, Comparable<User>{
    private String login;
    private String password;
    private Date birth_date;

    public User(String login, String password, Date birth_date) {
        this.login = login;
        this.password = password;
        this.birth_date = birth_date;
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
    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    @Override
    public int compareTo(User u) {
        int cmp = this.getLogin().compareTo(u.getLogin());
        return cmp;
    }
}
