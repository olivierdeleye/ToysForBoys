package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.entities.Product;

@Embeddable
public class OrderDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne // mapping met Entity Product
	@JoinColumn(name = "productID")
	private Product product; 
	
	private int quantityOrdered;
	private BigDecimal priceEach;
	
	protected OrderDetail(){
	
	}

	public OrderDetail(int quantity, BigDecimal price){
		quantityOrdered = quantity;
		priceEach = price;
	}

	public Product getProduct() {
		return product;
	}

    public int getQuantityOrdered() {
		return quantityOrdered;
	}


	public BigDecimal getPriceEach() {
		return priceEach;
	}

	

	public BigDecimal getAmount(){
		return priceEach.multiply(new BigDecimal(quantityOrdered));
	} 

	@Override
	public int hashCode() {
		return (product.getName().hashCode());
	}

    
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof OrderDetail)) {
			return false;
		}
		OrderDetail anderOrderDetail = (OrderDetail) obj;
		if (product.getName().hashCode() != anderOrderDetail.getProduct().getName().hashCode()) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return  product.getName() +  ":"  +  quantityOrdered  +  ":"  + priceEach ;
	}
	
}
