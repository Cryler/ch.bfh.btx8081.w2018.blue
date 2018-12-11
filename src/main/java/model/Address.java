
/**
 *date: 22.11.2018   -  time: 15:25:44
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package model;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Table;

/**
 * A Class that represents a simple Address Function.
 * 
 * 
 * @author yanng
 *
 */

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue
	private int addressID;
	private String street;
	private int streetNr;
	private int zipCode;
	private String city;

	public Address() {

	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreetNr(int streetNr) {
		this.streetNr = streetNr;
	}

	public int getStreetNr() {
		return this.streetNr;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return this.city;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public int getZipCode() {
		return this.zipCode;
	}

	@Override
	public String toString() {
		return this.street + " " + this.streetNr + "\n" + this.zipCode + this.city;
	}
}
