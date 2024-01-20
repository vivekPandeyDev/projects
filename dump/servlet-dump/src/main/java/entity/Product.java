package entity;

import java.util.Objects;

public class Product {
	private String productName;
	private Double productPrice;

	public Product() {
		super();
	}



	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	
	
	public Double getProductPrice() {
		return productPrice;
	}



	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}



	@Override
	public int hashCode() {
		return Objects.hash(productName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(productName, other.productName);
	}



	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productPrice=" + productPrice + "]";
	}


	
	
	

}
