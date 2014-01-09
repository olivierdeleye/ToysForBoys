package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import be.vdab.valueobjects.Adres;

@Entity
@Table(name = "customers")
public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
    long customerID;
	String name;
	
	@ManyToOne(fetch = FetchType.LAZY) // mapping met Entity Country
	@JoinColumn(name = "countryID")  
    private Country country;
	
	@Embedded // variable met als type een value object
	private Adres adres;
	
	protected Customer() {
		
	}
	
	public Customer(String name, Country country, Adres adres){
		this.name = name;
		this.country = country;
		this.adres = adres;
		
	}

    public long getCustomerID() {
		return customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	
    @Override
	public String toString() {
		return customerID + ":" + name + ":" + country + ":" + adres.toString();
	}

	@Override
	public int hashCode() {
		return adres.hashCode();		
	}

	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Customer)) {
			return false;
		}
		Customer andereCustomer = (Customer) obj;
		if (adres == null) {
			if (andereCustomer.adres != null) {
				return false;
			}
		} else if (!adres.equals(andereCustomer.adres)) {
			return false;
		}
		return true;
	}
}
