package business.control;

import business.model.IUser;
import infra.AdminUserPersistence;
import util.InfraException;
import util.UserLoginValidationException;
import util.UserNotFoundException;
import util.UserPasswordValidationException;
import java.io.Serializable;
import java.util.Comparator;
import java.util.TreeSet;

public class AdminUserController implements Serializable {
    private static AdminUserController adminUserController;
    private TreeSet<IUser> adminUsers;
    private AdminUserPersistence adminUserPersistence;

    private int USER_LOGIN_MAX_LENGTH = 20;
    private int USER_PASSWORD_MIN_LENGTH = 8;
    private int USER_PASSWORD_MAX_LENGTH = 12;

    private AdminUserController(AdminUserPersistence adminUserPersistence){
        adminUsers = new TreeSet<>();
        this.adminUserPersistence = adminUserPersistence;
    }

    public static AdminUserController getInstance() {
        if(adminUserController == null) {
            adminUserController = new AdminUserController();
        }
        return adminUserController;
    }

    private void validateUserLogin(IUser adminUser) throws UserLoginValidationException {
        if(adminUser.getLogin().isEmpty()) {
            throw new UserLoginValidationException("A login must me provided.");
        }

        if(adminUser.getLogin().length() > this.USER_LOGIN_MAX_LENGTH) {
            throw new UserLoginValidationException("Login cannot be longer than " + this.USER_LOGIN_MAX_LENGTH);
        }

        if(adminUser.getLogin().matches(".*\\d.*")) {
            throw new UserLoginValidationException("Login cannot contain a number");
        }
    }

    private void validateUserPassword(IUser adminUser) throws UserPasswordValidationException {
        if(adminUser.getPassword().isEmpty()) {
            throw new UserPasswordValidationException("A password must be provided");
        }

        if(adminUser.getPassword().length() > this.USER_PASSWORD_MAX_LENGTH) {
            throw new UserPasswordValidationException("Password cannot be longer than "
                    + this.USER_PASSWORD_MAX_LENGTH);
        }

        if(adminUser.getPassword().length() < this.USER_PASSWORD_MIN_LENGTH) {
            throw new UserPasswordValidationException("Password cannot be smaller than "
                    + this.USER_PASSWORD_MIN_LENGTH);
        }

        if(!(adminUser.getPassword().matches(".*\\d.*\\d.*"))) {
            throw new UserPasswordValidationException("Password must have at least 2 numbers");
        }

        if(!(adminUser.getPassword().matches(".*[A-Za-z]+.*"))) {
            throw new UserPasswordValidationException("Password must have letters");
        }
    }

    private void validateUser(IUser adminUser) throws UserLoginValidationException, UserPasswordValidationException {
        this.validateUserLogin(adminUser);
        this.validateUserPassword(adminUser);
    }

    public void add(IUser toCreateUser) throws UserLoginValidationException, UserPasswordValidationException, InfraException {
        this.validateUser(toCreateUser);
        this.adminUsers.add(toCreateUser);
        this.adminUserPersistence.save(adminUsers);
    }

    private IUser getUserByLogin(String login) throws UserNotFoundException, InfraException {
        this.adminUsers = this.adminUserPersistence.load();
        for(IUser user:this.adminUsers){
            if(user.getLogin().equals(login)) {
                return user;
            }
        }
        throw new UserNotFoundException();
    }

    public void delete(String login) throws InfraException, UserNotFoundException {
        IUser toRemoveUser = this.getUserByLogin(login);
        adminUsers.remove(toRemoveUser);
        this.adminUserPersistence.save(adminUsers);
    }

    public void listSingleUser(String login) throws InfraException, UserNotFoundException {
        IUser adminUser = this.getUserByLogin(login);
        System.out.println(this.printUser(adminUser));
    }

    public void list(Comparator<IUser> comparator) throws  InfraException {
        this.adminUsers = this.adminUserPersistence.load();
        TreeSet<IUser> sortedAdminUsers = new TreeSet<IUser>(comparator);
        sortedAdminUsers.addAll(this.adminUsers);

        for(IUser user : sortedAdminUsers){
            System.out.println(this.printUser(user));
        }
    }

    private String printUser(IUser adminUser) {
        String s = "";
        s += adminUser.getLogin() + " - " + adminUser.getBirthdate();
        return s;
    }

}
