package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable // annotatie voor een value object Class
public class Adres implements Serializable{

	private static final long serialVersionUID = 1L;
	private String streetAndNumber;
	private String city;
	private String state;
	private String postalCode;
	
	public Adres(String streetAndNumber, String city, String state, String postalCode) {
	   this.streetAndNumber = streetAndNumber;
	   this.city = city;
	   this.state = state;
	   this.postalCode = postalCode;
	}
	
	public Adres() {
		  // default constructor voor JPA
	}

	public String getStreetAndNumber() {
		return streetAndNumber;
	}

	public String getCity() {
		return city;
	}

	public String getState(){
		return state;
	}

	public String getPostalCode() {
		return postalCode;
	}
	
	
	@Override
	public String toString() {
		return streetAndNumber + ' '+ city + ' ' + postalCode;  
	}

	@Override
	public int hashCode() {
		return streetAndNumber.hashCode();
		
	}

	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Adres)) {
			return false;
		}
		Adres anderAdres = (Adres) obj;
		if (streetAndNumber == null) {
			if (anderAdres.streetAndNumber != null) {
				return false;
			}
		} else if (!streetAndNumber.equals(anderAdres.streetAndNumber)) {
			return false;
		}
		return true;
	}
	  

}
