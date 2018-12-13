package model;

import java.util.Observable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Person")

public abstract class Person extends Observable{
	@Id
	@GeneratedValue
	private int id;
	
	private String lastName;
	private String firstName;
	private String birthdate;
	private String gender;
	private String address;
	private String city;
	private String nationality;
	private String language;
	private String phoneNumber;
	private String email;
	
//	@Embedded
//	private Address address;
	
	
	public Person() {
		super();
		
	}
	public Person(int id, String aLastName, String aFirstName, String aBirthdate, String aGender, String anAddress, String aCity, String aNationality, String aLanguage, String aPhoneNumber, String anEmail) {
	
		super();
		
		this.lastName = aLastName;
		this.firstName = aFirstName;
		this.birthdate = aBirthdate;
		this.gender = aGender;
		this.address = anAddress;
		this.city = aCity;
		this.nationality = aNationality;
		this.language = aLanguage;
		this.phoneNumber = aPhoneNumber;
		this.email = anEmail;
	
	
}
	
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getBirthdate() {
		return this.birthdate;
	}
	
	public String getEmail() {
		return this.email;
		
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setFirstName(String aFirstName) {
		this.firstName = aFirstName;
	}
	
	public void setLastName(String aLastName) {
		this.lastName = aLastName;
	}
	
	public void setBirthdate(String aBirthdate) {
		this.birthdate = aBirthdate;
	}
	
//	public void setAddress(String anAddress) {
//		this.address = anAddress;
//	}
	
	
	
	
	
	
	
	
	
//	public Person(String aLastName, String aFirstName, String aBirthdate, String aGender, String anAddress, String aCity, String aNationality, String aLanguage, String aPhoneNumber, String anEmail) {
//		
//		
//		
//		
//	}
//	
//	public String getFirstName() {
//		return this.firstName;
//	}
//	
//	public String getLastName() {
//		return this.lastName;
//	}
//	
//	public String getBirthdate() {
//		return this.birthdate;
//	}
//	
//	public String getEmail() {
//		return this.email;
//		
//	}
//	
//	public String getAddress() {
//		return this.address;
//	}
//	
//	public void setFirstName(String aFirstName) {
//		this.firstName = aFirstName;
//	}
//	
//	public void setLastName(String aLastName) {
//		this.lastName = aLastName;
//	}
//	
//	public void setBirthdate(String aBirthdate) {
//		this.birthdate = aBirthdate;
//	}
//	
//	public void setAddress(String anAddress) {
//		this.address = anAddress;
//	}
}
