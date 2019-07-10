import java.util.ArrayList;
import java.util.Scanner;

import products.BookProduct;
import products.Cart;
import products.ElectronicProduct;
import products.Product;
import products.ProductsManager;
import users.RegularUser;
import users.TakenUsernameException;
import users.User;
import users.UsersManager;

public class Mainclass {
	private static User currentUser;
	private static Cart cart;
	private static Scanner scanner = new Scanner(System.in);
	private static UsersManager usersManager = new UsersManager();
	private static ProductsManager productsManager = new ProductsManager();

	public static void showAdminMenu() {
		int selection = 0;
		do {
			System.out.println("------------------------------------------");
			System.out.println("Hello " + currentUser.getUsername());
			System.out.println("Welcome to \"Ghaith Tana\" e-store");
			System.out.println("Enter (1) to add a product");
			System.out.println("Enter (2) to delete a product ");
			System.out.println("Enter (3) to list products");
			System.out.println("Enter (4) to list Registered Users");
			System.out.println("Enter (0) to Log out");
			System.out.println("------------------------------------------");

			selection = scanner.nextInt();

			switch (selection) {
			case 0: {
				currentUser = null;

				System.out.println("You had been logged out successfully..");

				return;
			}

			case 1: {
				System.out
						.print("Choose the product type: (1) for book, (2) for electronics");
				int type = scanner.nextInt();

				System.out.print("Enter product name: ");
				scanner.nextLine();
				String name = scanner.nextLine();

				System.out.print("Enter product description: ");
				String description = scanner.nextLine();

				System.out.print("Enter product quantity: ");
				int quantity = scanner.nextInt();

				System.out.print("Enter product price: ");
				int price = scanner.nextInt();

				if (type == 1) {
					System.out.print("Enter book ISBN: ");
					scanner.nextLine();
					String isbn = scanner.nextLine();

					BookProduct book = new BookProduct(0, name, description,
							type, quantity, price, isbn);

					productsManager.addProduct(book);
				} else {
					System.out.print("Enter manufacturing year: ");
					int year = scanner.nextInt();

					System.out.print("Enter company: ");
					scanner.nextLine();
					String company = scanner.nextLine();

					ElectronicProduct elec = new ElectronicProduct(0, name,
							description, type, quantity, price, year, company);

					productsManager.addProduct(elec);
				}

				System.out.println("The product added succesfully!");

				break;
			}

			case 2: {
				System.out.print("Enter product id: ");
				int id = scanner.nextInt();
				productsManager.deleteProduct(id);
				System.out.println("The product deleted succesfully!");

				break;
			}

			case 3: {
				ArrayList<Product> productslist = productsManager
						.getAllProducts();

				for (int i = 0; i < productslist.size(); i++) {
					Product pr = productslist.get(i);
					if (pr.getQuantity() > 0)
						System.out.println(pr.getIdNameQuantity());
				}

				break;
			}

			case 4: {

				ArrayList<User> usersList = usersManager.getAllUsers();

				for (int i = 0; i < usersList.size(); i++) {
					User user = usersList.get(i);
					System.out.println(i + 1 + "- " + user.getUserInfo());
				}

				break;
			}
			}

		} while (selection != 0);

	}

	public static void showRegularUserMenu() {
		cart = new Cart();

		int selection = 0;
		do {
			System.out.println("------------------------------------------");
			System.out.println("Hello " + currentUser.getUsername());
			System.out.println("Welcome to \"Ghaith Tana\" e-store");
			System.out.println("Enter (1) to show list of all products");
			System.out.println("Enter (2) to show list of products by type");
			System.out.println("Enter (3) to get description for a product");
			System.out.println("Enter (4) to show my cart");
			System.out.println("Enter (5) to add product to my cart");
			System.out.println("Enter (6) to cancel an item from cart");
			System.out.println("Enter (7) to buy items");
			System.out.println("Enter (8) to show my history");
			System.out.println("Enter (0) to Log out");
			System.out.println("------------------------------------------");

			selection = scanner.nextInt();

			switch (selection) {
			case 0: {
				currentUser = null;

				System.out.println("You had been logged out successfully..");

				return;
			}

			case 1: {
				ArrayList<Product> productslist = productsManager
						.getAllProducts();

				for (int i = 0; i < productslist.size(); i++) {
					Product pr = productslist.get(i);
					System.out.println(pr.getIdName());
				}

				break;
			}

			case 2: {
				System.out
						.print("Choose the product type: (1) for book, (2) for electronics");
				int type = scanner.nextInt();

				ArrayList<Product> productslist = productsManager
						.getProuductsByType(type);

				for (int i = 0; i < productslist.size(); i++) {
					Product pr = productslist.get(i);
					System.out.println(pr.getIdName());
				}

				break;
			}

			case 3: {
				System.out.print("Enter product id: ");
				int id = scanner.nextInt();

				Product p = productsManager.getProductById(id);

				if (p != null)
					System.out.println(p.getDescription());
				else
					System.out.println("Wrong id..");

				break;
			}

			case 4: {
				ArrayList<Product> productslist = cart.getAllProducts();

				if (productslist.size() == 0) {
					System.out.println("Your cart is empty");
				} else {

					for (int i = 0; i < productslist.size(); i++) {
						Product pr = productslist.get(i);
						System.out.println(pr.getNameQuantityPrice());
					}
				}

				break;
			}

			case 5: {
				System.out.print("Enter product id: ");
				int id = scanner.nextInt();

				Product p = productsManager.getProductById(id);

				if (p != null) {
					cart.addToCart(p);
				} else
					System.out.println("Wrong id..");

				break;
			}

			case 6: {
				System.out.print("Enter product id: ");
				int id = scanner.nextInt();

				cart.cancelItem(id);

				break;
			}

			case 7: {
				String summery = cart.buyItems();
				System.out.println(summery);

				break;
			}

			case 8: {

				break;
			}
			}
		} while (selection != 0);

	}

	public static void main(String[] args) {
		int choice = 0;

		do {
			System.out.println("Welcome to \"Ghaith Tana\" e-store");
			System.out.println("Enter (1) to Register");
			System.out.println("Enter (2) to Login");
			System.out.println("Enter (0) to Exit");

			choice = scanner.nextInt();

			switch (choice) {
			case 1: {
				System.out.println("Enter Username:");
				String username = scanner.next();

				System.out.println("Enter Password:");
				String password = scanner.next();

				System.out.println("Confirm Password:");
				String cpassword = scanner.next();

				if (!password.equals(cpassword)) {
					System.out.println("Passwords are not matched");
					continue;
				}

				RegularUser ru = new RegularUser(username, password);
				try {
					usersManager.addUser(ru);
				} catch (TakenUsernameException e) {
					System.out.println(e.getMessage());
					continue;
				}

				currentUser = ru;
				System.out
						.println("Congratulation! Your account has been created successfully..");

				showRegularUserMenu();
				break;
			}

			case 2: {
				System.out.println("Enter Username:");
				String username = scanner.next();

				System.out.println("Enter Password:");
				String password = scanner.next();

				User user = usersManager.login(username, password);
				if (user != null) {
					currentUser = user;

					if (user.getType().equals(User.TYPE_ADMIN)) {
						showAdminMenu();
					} else {
						showRegularUserMenu();
					}
				} else {
					System.out.println("Wrong username or password.");
				}

				break;
			}

			default:
				choice = 0;
				break;
			}
		} while (choice != 0);
	}
}
