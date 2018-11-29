package model;

import java.util.Observable;

public abstract class Person extends Observable{
	
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
	
	public Person(String aLastName, String aFirstName, String aBirthdate, String aGender, String anAddress, String aCity, String aNationality, String aLanguage, String aPhoneNumber, String anEmail) {
		
		
		
		
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
}
