package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "products")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Version
	private Timestamp versie;
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "productlineID") // mapping met Entity ProductLine 
	private ProductLine productline;

	@Id
	@GeneratedValue
	private long productID;
	private String name;
	private String scale;
	private String description;
	private int quantityInStock;
	private int quantityInOrder;
	private BigDecimal buyPrice;
	
	
	protected Product(){
		
	}
	
	public Product(String name, String description, int quantityInStock, int quantityInOrder, BigDecimal buyPrice){
		this.name = name;
		this.description = description;
		this.quantityInStock = quantityInStock;
		this.quantityInOrder = quantityInOrder;
		this.buyPrice = buyPrice;
	}
	
	
	public ProductLine getProductline() {
		return productline;
	}

	public void setProductline(ProductLine productline) {
		this.productline = productline;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public int getQuantityInOrder() {
		return quantityInOrder;
	}

	public void setQuantityInOrder(int quantityInOrder) {
		this.quantityInOrder = quantityInOrder;
	}

	public void verminderQuantityInStock(int quantity){
	    quantityInStock -= quantity;	
	}
	
	public void verminderQuantityInOrder(int quantity){
		quantityInOrder -= quantity;
	}
	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public long getProductID() {
		return productID;
	}

	@Override
	public String toString() {
		return productID + ":" + name + ":" + scale +":" + description + ":" + quantityInStock +
			":" + quantityInOrder + ":" + buyPrice;
			
	}

	@Override
	public int hashCode() {
	  return description.hashCode() + name.hashCode();
		
	}

	@Override
	public boolean equals(Object obj) {
	  if(!(obj instanceof Product)){
			return false;
		}
	  return ((Product)obj).name.equals(this.name);
		
	}

	
}
