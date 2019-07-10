package products;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BookProduct extends Product {
	private String isbn;

	public BookProduct() {

	}

	public BookProduct(int id, String name, String description, int type,
			int quantity, int price, String isbn) {
		super(id, name, description, type, quantity, price);

		setIsbn(isbn);
	}

	@Override
	public Product clone() {
		return new BookProduct(getId(), getName(), getDescription(), getType(),
				getQuantity(), getPrice(), isbn);
	}

	@Override
	public void storeToFile(DataOutputStream ds) throws IOException {
		byte[] name = new byte[50];
		System.arraycopy(getName().getBytes(), 0, name,
				50 - getName().length(), getName().length());

		byte[] description = new byte[100];
		System.arraycopy(getDescription().getBytes(), 0, description,
				100 - getDescription().length(), getDescription().length());

		byte[] isbn = new byte[10];
		System.arraycopy(getIsbn().getBytes(), 0, isbn,
				10 - getIsbn().length(), getIsbn().length());

		ds.writeInt(getType());
		ds.writeInt(getId());
		ds.write(name);
		ds.write(description);
		ds.writeInt(getQuantity());
		ds.writeInt(getPrice());
		ds.write(isbn);
	}

	@Override
	public void loadFromFile(DataInputStream ds) throws IOException {
		setType(1);
		setId(ds.readInt());

		byte[] name = new byte[50];
		ds.read(name);
		setName(new String(name).trim());

		byte[] description = new byte[100];
		ds.read(description);
		setDescription(new String(description).trim());

		setQuantity(ds.readInt());
		setPrice(ds.readInt());

		byte[] isbn = new byte[10];
		ds.read(isbn);
		setIsbn(new String(isbn).trim());
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}
