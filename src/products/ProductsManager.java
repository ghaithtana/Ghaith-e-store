package products;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ProductsManager {
	private final String fileName = "products.bin";
	private File productsFile;

	public ProductsManager() {
		try {
			productsFile = new File(fileName);

			if (!productsFile.exists()) {
				productsFile.createNewFile();
			}

		} catch (Exception e) {
			System.out.println("there is an error");
		}
	}

	public void addProduct(Product product) {
		ArrayList<Product> list = getAllProducts();

		int id = 1;
		if (list.size() > 0) {
			Product lastProduct = list.get(list.size() - 1);
			id = lastProduct.getId() + 1;
		}
		product.setId(id);
		list.add(product);

		insertProductsToFile(list);
	}

	public void deleteProduct(int id) {
		ArrayList<Product> list = getAllProducts();

		// delete product
		for (int i = 0; i < list.size(); i++) {
			Product p = list.get(i);
			if (id == p.getId()) {
				list.remove(p);
				break;
			}
		}

		insertProductsToFile(list);
	}

	public ArrayList<Product> getAllProducts() {
		ArrayList<Product> productsArray = new ArrayList<Product>();

		try {
			DataInputStream ds = new DataInputStream(new FileInputStream(
					productsFile));

			Product p = nextProductFromFile(ds);
			while (p != null) {
				productsArray.add(p);
				p = nextProductFromFile(ds);
			}

			ds.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return productsArray;
	}

	public ArrayList<Product> getProuductsByType(int type) {
		ArrayList<Product> productsArray = new ArrayList<Product>();

		try {
			DataInputStream ds = new DataInputStream(new FileInputStream(
					productsFile));

			Product p = nextProductFromFile(ds);
			while (p != null) {
				if (p.getType() == type) {
					productsArray.add(p);
				}
				p = nextProductFromFile(ds);
			}

			ds.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return productsArray;
	}

	public Product getProductById(int id) {

		try {
			DataInputStream ds = new DataInputStream(new FileInputStream(
					productsFile));

			Product p = nextProductFromFile(ds);
			while (p != null) {
				if (p.getId() == id) {
					return p;
				}
				p = nextProductFromFile(ds);
			}

			ds.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	// /////////////////////////////////////////////////////////////////////

	private void insertProductsToFile(ArrayList<Product> list) {
		try {
			DataOutputStream ds = new DataOutputStream(new FileOutputStream(
					productsFile));

			for (Product product : list) {
				product.storeToFile(ds);
				ds.flush();
			}

			ds.close();

		} catch (IOException e) {
		}
	}
	
	private Product nextProductFromFile(DataInputStream ds) throws IOException {
		Product product = null;
		int type = 0;

		try {
			type = ds.readInt();
		} catch (IOException e) {
			return product;
		}

		if (type == 1) {
			product = new BookProduct();
			product.loadFromFile(ds);
		} else {
			product = new ElectronicProduct();
			product.loadFromFile(ds);
		}

		return product;
	}

}
