package business.control;

import business.model.Order;
import business.model.User;
import infra.IUserPersistence;
import infra.UserPersistenceFactory;
import util.InfraException;
import util.UserLoginValidationException;
import util.UserNotFoundException;
import util.UserPasswordValidationException;
import business.model.IUser;

import java.io.Serializable;
import java.util.Comparator;
import java.util.TreeSet;

public class UserController implements Serializable {
    private static UserController userController;
    private TreeSet<IUser> users;
    private IUserPersistence userPersistence;

    private int USER_LOGIN_MAX_LENGTH = 20;
    private int USER_PASSWORD_MIN_LENGTH = 8;
    private int USER_PASSWORD_MAX_LENGTH = 12;

    private UserController(IUserPersistence userPersistence){
        users = new TreeSet<>();
        this.userPersistence = userPersistence;
    }

    static public UserController getInstance() {
        if(userController == null) {
            userController = new UserController(UserPersistenceFactory.getPersistence());
        }
        return userController;
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

    private IUser getUserByLogin(String login) throws UserNotFoundException, InfraException {
        this.users = this.userPersistence.load();
        for(IUser user:this.users){
            if(user.getLogin().equals(login)) {
                return user;
            }
        }
        throw new UserNotFoundException();
    }

    public void delete(String login) throws InfraException, UserNotFoundException {
        IUser toRemoveUser = this.getUserByLogin(login);
        users.remove(toRemoveUser);
        this.userPersistence.save(users);
    }

    public IUser listSingleUser(String login) throws InfraException, UserNotFoundException {
        IUser user = this.getUserByLogin(login);
        System.out.println(this.printUser(user));
        return user;
    }

    public void list(Comparator<IUser> comparator) throws  InfraException {
        this.users = this.userPersistence.load();
        TreeSet<IUser> sortedUsers = new TreeSet<IUser>(comparator);
        sortedUsers.addAll(this.users);

        for(IUser user : sortedUsers){
            System.out.println(this.printUser(user));
        }
    }

    private String printUser(IUser user) {
        String s = "";
        s += user.getLogin() + " - " + user.getBirthdate();
        return s;
    }

    private void addOrder(Order order, User user){
        user.addOrder(order);
    }
}
