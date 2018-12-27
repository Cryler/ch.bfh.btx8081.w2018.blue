
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

@Entity
@Table(name = "institution")
public class InstitutionEntity {

	@Id
	@GeneratedValue
	private int institutionID;
	private String institutionName;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Address address;

	public String getInstitutionName() {
		return this.institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public Address getInstitutionAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
