package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import be.vdab.enums.Status;
import be.vdab.valueobjects.OrderDetail;

@Entity
@Table(name = "orders")
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Version
	private Timestamp versie;
    
	@Id
	@GeneratedValue
	private long orderID;
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name = "customerID") //gerichte mapping met Entity Customer
	private Customer customer;
	
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	@Temporal(TemporalType.DATE)
	private Date requiredDate;
	@Temporal(TemporalType.DATE)
	private Date shippedDate;
	
	private String comments;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ElementCollection 
	@CollectionTable(name = "orderdetails", joinColumns = @JoinColumn(name = "orderID"))
	private Set<OrderDetail> orderdetails;
	 
	protected Order(){
		
	}

	public Order(Date orderDate, Date requiredDate, Date shippedDate, String comments, Status status){
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.comments = comments;
		this.status = status;
    }
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public long getOrderID() {
		return orderID;
	}

    public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public BigDecimal getTotalValue() {
	  BigDecimal total = BigDecimal.ZERO;
	  for(OrderDetail orderdetail : orderdetails) {
		total = total.add(orderdetail.getAmount());
	  }
	  return total;
	}
	
	public Set<OrderDetail> getOrderdetails() {
		return Collections.unmodifiableSet(orderdetails);
	}
	
	public void addOrderdetail(OrderDetail orderdetail) {
		orderdetails.add(orderdetail);
	}
	
	public void removeOrderdetail(OrderDetail orderdetail) {
		orderdetails.remove(orderdetail);
	}
	
	@Override
	public int hashCode(){
		return getCustomer().getAdres().getStreetAndNumber().hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		if(! (obj instanceof Order)){
			return false;
		}
		return ((Order)obj).customer.getAdres().getStreetAndNumber().equals(this.customer.getAdres().getStreetAndNumber());
	}
	
    @Override
	public String toString() {
		return customer.toString() + ":" + orderID + ":" + orderDate  + ":" 
				+  requiredDate + ":" + shippedDate + ":" + comments + ":" + status;
				
	}
}
