
/**
 *date: 22.11.2018   -  time: 15:25:44
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A Class that represents a simple {@code Address} Function.
 * The Data is stored in the table "address" in the DB.
 *   
 * @author gundy1
 *
 */

@Entity
@Table(name = "address")
public class Address{


	/** The address ID that works as the PK in the DB-table. */
	@Id
	@GeneratedValue
	private int addressID;
	private String street;
	private int streetNr;
	private int zipCode;	
	private String city;

	
	public Address() {
	}

	/**
	 * Sets the street of the Address.
	 *
	 * @param street the new street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Gets the street of the Address.
	 *
	 * @return the street
	 */
	public String getStreet() {
		return this.street;
	}

	/**
	 * Sets the street number of the Address.
	 *
	 * @param streetNr the new street number.
	 */
	public void setStreetNr(int streetNr) {
		this.streetNr = streetNr;
	}

	/**
	 * Gets the street number of the Address.
	 *
	 * @return the street number of the Address.
	 */
	public int getStreetNr() {
		return this.streetNr;
	}

	/**
	 * Sets the city of the Address.
	 *
	 * @param city the new city of the Address
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the city of the Address.
	 *
	 * @return the city of the Address
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * Sets the zip code of the Address.
	 *
	 * @param zipCode the new zip code of the Address
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * Gets the zip code of the Address.
	 *
	 * @return the zip code of the Address
	 */
	public int getZipCode() {
		return this.zipCode;
	}

	
	@Override
	public String toString() {
		return this.street + " " + this.streetNr + "\n" + this.zipCode + " "+this.city;
	}
}
