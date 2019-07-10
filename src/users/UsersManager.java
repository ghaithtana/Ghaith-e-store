package users;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import products.Product;

public class UsersManager {
	private final String fileName = "login.txt";
	private final String adminUsername = "admin";
	private final String adminPassword = "rm777";

	private File loginFile;

	public UsersManager() {
		try {
			loginFile = new File(fileName);

			if (!loginFile.exists()) {
				loginFile.createNewFile();

				addUser(new AdminUser(adminUsername, adminPassword));
			}

		} catch (Exception e) {
			System.out.println("there is an error");

		}
	}

	public void addUser(User user) throws TakenUsernameException {
		User u = getUser(user.getUsername());
		if (u != null) {
			throw new TakenUsernameException();
		}

		String line = user.getUsername() + ":" + user.getPassword() + ":"
				+ user.getType() + "\r\n";

		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(
					loginFile, true));
			output.write(line);
			output.close();

		} catch (IOException e) {
		}

	}

	public User getUser(String username) {
		username = username.toLowerCase();

		User user = null;

		try {
			BufferedReader reader = new BufferedReader(
					new FileReader(loginFile));

			User u = null;
			do {
				u = nextUserFromFile(reader);
				if (u != null && u.getUsername().equals(username)) {
					user = u;
					break;
				}

			} while (u != null);

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return user;
	}

	public User login(String username, String password) {
		User user = getUser(username);

		if (user != null) {
			if (password.equals(user.getPassword()))
				return user;

		}

		return null;
	}

	public ArrayList<User> getAllUsers() {
		ArrayList<User> usersArray = new ArrayList<User>();

		try {
			BufferedReader reader = new BufferedReader(
					new FileReader(loginFile));

			User user = null;
			do {
				user = nextUserFromFile(reader);
				if (user != null)
					usersArray.add(user);

			} while (user != null);

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return usersArray;
	}

	// /////////////////////////////////////////////////////////////////////

	private User nextUserFromFile(BufferedReader reader) throws IOException {
		String line = reader.readLine();

		if (line != null) {

			String str[] = line.split(":");

			String username = str[0];
			String password = str[1];
			String type = str[2];

			User user = null;
			if (type.equals(User.TYPE_ADMIN))
				user = new AdminUser(username, password);
			else
				user = new RegularUser(username, password);

			return user;
		}

		return null;
	}

}
