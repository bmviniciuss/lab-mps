package infra;

public class UserPersistenceFactory {
    public static IUserPersistence getPersistence() {
        return UserPersistence.getInstance();
    }
}
