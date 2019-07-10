package users;

public class AdminUser extends User {

	public AdminUser(String username, String password) {
		super(username, password, User.TYPE_ADMIN);
	}

	@Override
	public String getUserInfo() {
		return "Admin: " + getUsername();
	}

}
