package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "productlines")
public class ProductLine implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long productlineID;
	private String name;
	private String description;
	

	protected ProductLine(){
	}
	
	public ProductLine(String name, String description){
		this.name = name;
		this.description = description;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getProductlineID() {
		return productlineID;
	}
    
	@Override
	public int hashCode(){
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		if(! (obj instanceof ProductLine)){
			return false;
		}
		return ((ProductLine)obj).name.equals(this.name);
	}
	@Override
	public String toString() {
		return productlineID + ":" + name + ":" + description;
				
	}


	
	
	

}
