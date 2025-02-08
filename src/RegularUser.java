public class RegularUser extends User {
    public RegularUser(String username, String password, String email) {
        super(username, password, email);
    }

    @Override
    public boolean isAdmin() {
        return false;
    }
}
