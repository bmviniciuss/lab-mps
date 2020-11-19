package business.control;

import util.UserLoginValidationException;
import util.UserPasswordValidationException;
import business.model.IUser;

import java.io.Serializable;
import java.util.TreeMap;

public class UserController implements Serializable {

    private TreeMap<String, IUser> users;

    private int USER_LOGIN_MAX_LENGTH = 20;
    private int USER_PASSWORD_MIN_LENGTH = 8;
    private int USER_PASSWORD_MAX_LENGTH = 12;

    public UserController(){
        users = new TreeMap<>();
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

    public void add(IUser toCreateUser) throws UserLoginValidationException, UserPasswordValidationException {
        this.validateUser(toCreateUser);

        users.put(toCreateUser.getLogin(), toCreateUser);
    }

    public void delete(String login){
        if (users.containsKey(login)) {
            users.remove(login);
            System.out.println("O usuário foi removido com sucesso!");
        }else{
            System.out.println("Login informado não existe");
        }
    }

    public void listAll(){
        for(String login : users.keySet()){
            System.out.println(login);
        }
    }
}
