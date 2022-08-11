package co.grandcircus.cartapi;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Document annotation requires the name of the collection which holds the object you're modeling. 
@Document("cartItems")
public class CartItems {
	
	@Id
	public String id;
	
	private String product;
	private Double price;
	private Integer quantity;
	
	public CartItems() {}
	
	public CartItems(String product, Double price, Integer quantity) {
		this.product = product;
		this.price = price;
		this.quantity = quantity;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
