package infra;

public class AdminUserPersistenceFactory {
    public static AdminUserPersistence getPersistence() {
        return AdminUserPersistence.getInstance();
    }
}
