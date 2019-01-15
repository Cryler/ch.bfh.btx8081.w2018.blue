
/**
 *date: 14.12.2018   -  time: 14:39:12
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The Class InstitutionEntity that stores the address of the Institution. More functionality would be added later.
 * 
 * @author gundy1
 * 
 */
@Entity
@Table(name = "institution")
public class InstitutionEntity {

	
	@Id
	@GeneratedValue
	private int institutionID;	
	
	private String institutionName;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Address address;

	/**
	 * Gets the institution name.
	 *
	 * @return the institution name
	 */
	public String getInstitutionName() {
		return this.institutionName;
	}

	/**
	 * Sets the institution name.
	 *
	 * @param institutionName the new institution name
	 */
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	/**
	 * Gets the institution address.
	 *
	 * @return the institution address
	 */
	public Address getInstitutionAddress() {
		return this.address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
}
