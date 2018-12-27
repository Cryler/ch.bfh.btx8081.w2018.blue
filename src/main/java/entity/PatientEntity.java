package entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="patient")
public class PatientEntity extends PersonEntity {

	private String insurance;
	private String ahvNr;


	public PatientEntity() {

		super();
	}


	public String getAhvNr() {
		return ahvNr;
	}


	public void setAhvNr(String ahvNr) {
		this.ahvNr = ahvNr;
	}


	public String getInsurance() {
		return insurance;
	}


	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	
	
	
}
