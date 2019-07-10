package users;

public class TakenUsernameException extends Exception {
	public TakenUsernameException() {
		super("The username is taken");
	}
}
