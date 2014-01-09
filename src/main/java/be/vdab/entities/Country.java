package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "countries")
public class Country implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
	private long countryID;
	private String name;

	protected Country() {
		
	}
	
	public Country(String name){
		this.name = name;
	}

	public long getCountryID() {
		return countryID;
	}

	public void setCountryID(long countryID) {
		this.countryID = countryID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Country)) {
			return false;
		}
		return ((Country)obj).name.equals(name);
	}
}
	
	
	

