package model;

import javax.persistence.Entity;

@Entity

public class PatientModel extends Person {

	private String insurance;
	private String ahvNr;

	public PatientModel(int id, String aLastName, String aFirstName, String aBirthdate, String aGender,
			String anAddress, String aCity, String aNationality, String aLanguage, String aPhoneNumber, String anEmail,
			String anInsurance, String anAhvNr) {
		super(id, aLastName, aFirstName, aBirthdate, aGender, anAddress, aCity, aNationality, aLanguage, aPhoneNumber,
				anEmail);
		this.insurance = anInsurance;
		this.ahvNr = anAhvNr;

	}

	public PatientModel() {

		super();
	}
//	
//	public String getFirstName() {
//		return super.getFirstName();
//	}
//	
//	public String getLastName() {
//		return super.getLastName();
//	}
//	
//	public String getBirthdate() {
//		return super.getBirthdate();
//	}
//	
//	public String getEmail() {
//		return super.getEmail();
//	}
//	
//	public String getAddress() {
//		return super.getAddress();
//	}
//	
//	public void setFirstName(String aFirstName) {
//		super.setFirstName(aFirstName);
//	}
//	
//	public void setLastName(String aLastName) {
//		super.setLastName(aLastName);
//	}
//	
//	public void setBirthdate(String aBirthdate) {
//		super.setBirthdate(aBirthdate);
//	}
//	
//	public void setAddress(String anAddress) {
//		super.setAddress(anAddress);
//	}
}
