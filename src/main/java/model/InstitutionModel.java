
/**
 *date: 22.11.2018   -  time: 15:15:37
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package model;

import java.util.Observable;

import javax.persistence.Entity;
import javax.persistence.Id;


public class InstitutionModel extends Observable{

	@Id
	private String institutionName;
	private Address address;

	
	public InstitutionModel() {
		super();
		this.setInstitutionName("Default Name");
		this.setAddress(new Address("Musterweg", 50, 3600, "Musterhausen"));
	}

	public String getInstitutionName() {
		return this.institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
		this.setChanged();
		this.notifyObservers();
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
		this.setChanged();
		this.notifyObservers();
	}

}
