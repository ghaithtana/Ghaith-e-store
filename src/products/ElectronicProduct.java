package products;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ElectronicProduct extends Product {
	private int year;
	private String company;

	public ElectronicProduct() {

	}

	public ElectronicProduct(int id, String name, String description, int type,
			int quantity, int price, int year, String company) {
		super(id, name, description, type, quantity, price);

		setYear(year);
		setCompany(company);
	}

	@Override
	public Product clone() {
		return new ElectronicProduct(getId(), getName(), getDescription(),
				getType(), getQuantity(), getPrice(), year, company);
	}

	@Override
	public void storeToFile(DataOutputStream ds) throws IOException {
		byte[] name = new byte[50];
		System.arraycopy(getName().getBytes(), 0, name,
				50 - getName().length(), getName().length());

		byte[] description = new byte[100];
		System.arraycopy(getDescription().getBytes(), 0, description,
				100 - getDescription().length(), getDescription().length());

		byte[] company = new byte[50];
		System.arraycopy(getCompany().getBytes(), 0, company, 50 - getCompany()
				.length(), getCompany().length());

		ds.writeInt(getType());
		ds.writeInt(getId());
		ds.write(name);
		ds.write(description);
		ds.writeInt(getQuantity());
		ds.writeInt(getPrice());
		ds.writeInt(year);
		ds.write(company);
	}

	@Override
	public void loadFromFile(DataInputStream ds) throws IOException {
		setType(2);
		setId(ds.readInt());

		byte[] name = new byte[50];
		ds.read(name);
		setName(new String(name).trim());

		byte[] description = new byte[100];
		ds.read(description);
		setDescription(new String(description).trim());

		setQuantity(ds.readInt());
		setPrice(ds.readInt());
		setYear(ds.readInt());

		byte[] company = new byte[50];
		ds.read(company);
		setCompany(new String(company).trim());
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
