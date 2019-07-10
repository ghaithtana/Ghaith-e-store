package products;

import java.util.ArrayList;

public class Cart {
	private ArrayList<Product> cart = new ArrayList<Product>();

	public ArrayList<Product> getAllProducts() {
		return cart;
	}

	public void addToCart(Product product) {
		product.decreaseQuantity();

		for (Product p : cart) {
			if (p.getId() == product.getId()) {
				p.increaseQuantity();

				return;
			}
		}

		Product newProduct = product.clone();
		newProduct.setQuantity(1);

		cart.add(newProduct);
	}

	public void cancelItem(int id) {
		for (Product p : cart) {
			if (p.getId() == id) {
				cart.remove(p);
				return;
			}
		}
	}

	public String buyItems() {
		String summery = "";
		int total = 0;

		for (int i = 0; i < cart.size(); i++) {
			Product pr = cart.get(i);
			total += pr.getPrice() * pr.getQuantity();
			summery += pr.getNameQuantityPrice() + "\n";
		}

		summery += "The totle price is: " + total + "$";

		return summery;
	}
}
