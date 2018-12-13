
/**
 *date: 22.11.2018   -  time: 15:15:37
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * This class stores and represents the {@code Institution} in the DB.
 * It contains a name and a {@code Address}.
 * 
 * 
 * @author yanng
 *
 */
@Entity
@Table(name = "institution")
public class InstitutionModel {

	
	/**
	 * The {@value institutionID} is set to 1 because there should only be one institution at a given time. 
	 * 
	 */
	@Id
	private int institutionID = 1;
	private String institutionName;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Address address;

	
	public InstitutionModel() {
		
	}

	public String getInstitutionName() {
		return this.institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
