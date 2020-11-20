package business.control;

import infra.IUserPersistence;
import infra.UserPersistence;
import util.InfraException;
import util.UserLoginValidationException;
import util.UserPasswordValidationException;
import business.model.IUser;

import java.io.Serializable;
import java.util.TreeSet;

public class UserController implements Serializable {

    private TreeSet<IUser> users;
    private IUserPersistence userPersistence;

    private int USER_LOGIN_MAX_LENGTH = 20;
    private int USER_PASSWORD_MIN_LENGTH = 8;
    private int USER_PASSWORD_MAX_LENGTH = 12;

    public UserController(){
        users = new TreeSet<>();
        this.userPersistence = new UserPersistence();
    }

    private void validateUserLogin(IUser user) throws UserLoginValidationException {
        if(user.getLogin().isEmpty()) {
            throw new UserLoginValidationException("A login must me provided.");
        }

        if(user.getLogin().length() > this.USER_LOGIN_MAX_LENGTH) {
            throw new UserLoginValidationException("Login cannot be longer than " + this.USER_LOGIN_MAX_LENGTH);
        }

        if(user.getLogin().matches(".*\\d.*")) {
            throw new UserLoginValidationException("Login cannot contain a number");
        }
    }

    private void validateUserPassword(IUser user) throws UserPasswordValidationException {
        if(user.getPassword().isEmpty()) {
            throw new UserPasswordValidationException("A password must be provided");
        }

        if(user.getPassword().length() > this.USER_PASSWORD_MAX_LENGTH) {
            throw new UserPasswordValidationException("Password cannot be longer than "
                    + this.USER_PASSWORD_MAX_LENGTH);
        }

        if(user.getPassword().length() < this.USER_PASSWORD_MIN_LENGTH) {
            throw new UserPasswordValidationException("Password cannot be smaller than "
                    + this.USER_PASSWORD_MIN_LENGTH);
        }

        if(!(user.getPassword().matches(".*\\d.*\\d.*"))) {
            throw new UserPasswordValidationException("Password must have at least 2 numbers");
        }

        if(!(user.getPassword().matches(".*[A-Za-z]+.*"))) {
            throw new UserPasswordValidationException("Password must have letters");
        }
    }

    private void validateUser(IUser user) throws UserLoginValidationException, UserPasswordValidationException {
        this.validateUserLogin(user);
        this.validateUserPassword(user);
    }

    public void add(IUser toCreateUser) throws UserLoginValidationException, UserPasswordValidationException, InfraException {
        this.validateUser(toCreateUser);
        this.users.add(toCreateUser);
        this.userPersistence.save(users);
    }

    public void delete(String login) throws InfraException {
        for(IUser user : users) {
            if (user.getLogin().equals(login)) {
                users.remove(user);
                this.userPersistence.save(users);
                System.out.println("O usuário foi removido com sucesso!");
            } else {
                System.out.println("Login informado não existe");
            }
        }
    }

    public void listAll() throws InfraException {
        this.users = this.userPersistence.load();
        for(IUser user : users){
            System.out.println(user.getLogin());
        }
    }

    public void listByDate() throws InfraException {
        this.users = this.userPersistence.load();
        TreeSet<IUser> usersDate = new TreeSet<>(new DateComparator());
        for(IUser user : users){
            usersDate.add(user);
        }
        for(IUser user : usersDate){
            System.out.println(user.getLogin() + " - " + user.getBirthdate().toString());
        }
    }
}
