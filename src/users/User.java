package users;

public abstract class User {
	public static final String TYPE_ADMIN = "admin";
	public static final String TYPE_REGULAR = "regular";

	private String username;
	private String password;
	private String type;

	public User(String username, String password, String type) {
		this.username = username.toLowerCase();
		this.password = password;
		this.type = type;
	}

	public abstract String getUserInfo();

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getType() {
		return type;
	}
}
