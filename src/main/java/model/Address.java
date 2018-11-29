
/**
 *date: 22.11.2018   -  time: 15:25:44
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package model;

/**
 * A Class that represents a simple Address Function.
 * 
 * 
 * @author yanng
 *
 */

public class Address {

	private String street;
	private int streetNr;
	private int zipCode;
	private String city;

	public Address(String street, int streetNr, int zipCode, String city) {
		this.setStreet(street);
		this.setStreetNr(streetNr);
		this.setZipCode(zipCode);
		this.setCity(city);
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
		return this.street + " "+ this.streetNr + "\n"+ this.zipCode + this.city;
	}

}
