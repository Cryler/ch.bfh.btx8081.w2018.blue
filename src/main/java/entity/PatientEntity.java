package entity;

import javax.persistence.Entity;


/**
 * The Class PatientEntity. the class PatientEntity inherits from the class PersonEntity. New are AHVNr. and insurance.
 * The data are stored in the table "person" in the DB.
 * 
 * @author gehry1
 * 
 * 
 */
@Entity

public class PatientEntity extends PersonEntity {

	/** The insurance. */
	private String insurance;
	
	/** The ahv nr. */
	private String ahvNr;


	/**
	 * Instantiates a new patient entity.
	 */
	public PatientEntity() {

		super();
	}


	/**
	 * Gets the ahv nr.
	 *
	 * @return the ahv nr
	 */
	public String getAhvNr() {
		return ahvNr;
	}


	/**
	 * Sets the ahv nr.
	 *
	 * @param ahvNr the new ahv nr
	 */
	public void setAhvNr(String ahvNr) {
		this.ahvNr = ahvNr;
	}


	/**
	 * Gets the insurance.
	 *
	 * @return the insurance
	 */
	public String getInsurance() {
		return insurance;
	}


	/**
	 * Sets the insurance.
	 *
	 * @param insurance the new insurance
	 */
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}	
}
