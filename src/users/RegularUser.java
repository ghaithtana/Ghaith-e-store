package users;

public class RegularUser extends User {

	public RegularUser(String username, String password) {
		super(username, password, User.TYPE_REGULAR);
	}

	@Override
	public String getUserInfo() {
		return getUsername();
	}

}
