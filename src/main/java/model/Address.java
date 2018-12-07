
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

@Table(name = "address")
@Entity
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

	public Address(String street, int streetNr, int zipCode, String city) {
		
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ch.bfh.btx8081.w2018.blue");
//		EntityManager entitymanager = emfactory.createEntityManager();
//		
//		
		this.setStreet(street);
		this.setStreetNr(streetNr);
		this.setZipCode(zipCode);
		this.setCity(city);
		
//		try {
//			entitymanager.getTransaction().begin();
//			entitymanager.merge(this);
//			entitymanager.getTransaction().commit();
//		} catch (Exception e) {
//			entitymanager.getTransaction().rollback();
//		}
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getStreetNr() {
		return streetNr;
	}

	public void setStreetNr(int streetNr) {
		this.streetNr = streetNr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return this.street + " " + this.streetNr + "\n" + this.zipCode + this.city;
	}
}

