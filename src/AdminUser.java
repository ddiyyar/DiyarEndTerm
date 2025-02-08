public class AdminUser extends User {
    public AdminUser(String username, String password, String email) {
        super(username, password, email);
    }

    @Override
    public boolean isAdmin() {
        return true;
    }
}
