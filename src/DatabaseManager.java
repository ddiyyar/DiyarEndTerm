public class DatabaseManager {
    private static DatabaseManager instance;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    private DatabaseManager(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public static synchronized DatabaseManager getInstance(String dbUrl, String dbUser, String dbPassword) {
        if (instance == null) {
            instance = new DatabaseManager(dbUrl, dbUser, dbPassword);
        }
        return instance;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
