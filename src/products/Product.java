package products;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Product {
	public static final String TYPE_BOOK = "Book";
	public static final String TYPE_ELECTRONICS = "Electronics";

	private int id;
	private String name;
	private String description;
	private int type;
	private int quantity;
	private int price;

	public Product() {
	}

	public Product(int id, String name, String description, int type,
			int quantity, int price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.quantity = quantity;
		this.price = price;
	}

	public abstract Product clone();

	public abstract void storeToFile(DataOutputStream ds) throws IOException;

	public abstract void loadFromFile(DataInputStream ds) throws IOException;

	public String getIdName() {
		String line = "id: " + id + "\n";
		line += "Name: " + name + "\n";
		return line;
	}

	public String getIdNameQuantity() {
		String line = "id: " + id + "\n";
		line += "Name: " + name + "\n";
		line += "Quantity: " + quantity + "\n";
		return line;
	}

	public String getNameQuantityPrice() {
		String line = "Name: " + name + "\n";
		line += "Quantity: " + quantity + "\n";
		line += "Price: " + price + "$\n";
		return line;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void increaseQuantity() {
		quantity++;
	}

	public void decreaseQuantity() {
		quantity--;
	}
}
