package model;

public class PatientModel extends Person {
	
	
	private String insurance;
	private String ahvNr;
	
	

	public PatientModel(String aLastName, String aFirstName, String aBirthdate, String aGender, String anAddress, String aCity, String aNationality, String aLanguage, String aPhoneNumber, String anEmail, String anInsurance, String anAhvNr){
		super(aLastName, aFirstName, aBirthdate, aGender, anAddress, aCity, aNationality, aLanguage, aPhoneNumber, anEmail);
		insurance = anInsurance;
		ahvNr = anAhvNr;
		
	}
	
	public String getFirstName() {
		return super.getFirstName();
	}
	
	public String getLastName() {
		return super.getLastName();
	}
	
	public String getBirthdate() {
		return super.getBirthdate();
	}
	
	public String getEmail() {
		return super.getEmail();
	}
}
