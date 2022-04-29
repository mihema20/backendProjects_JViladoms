package com.example.furnitureStore.model;



import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
@Document("product")
public class Product {
	@Id
	private String productId;
	private String location;
	private String title;
	private String description;
	private double price;
	private Binary image;
	
	public Product() {
	}

	public Product(String productId, String location, String title, String description, double price, Binary image) {
		super();
		this.productId = productId;
		this.location = location;
		this.title = title;
		this.description = description;
		this.price = price;
		this.image = image;
	}
	
	public Product(String location, String title, String description, double price, Binary image) {
		super();
		this.location = location;
		this.title = title;
		this.description = description;
		this.price = price;
		this.image = image;
	}
	
	@Id
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Binary getImage() {
		return image;
	}

	public void setImage(Binary image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", location=" + location + ", title=" + title + ", description="
				+ description + ", price=" + price + ", image=" + image + "]";
	}

	
}
