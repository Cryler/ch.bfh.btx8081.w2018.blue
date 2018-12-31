
/**
 *date: 21.12.2018   -  time: 13:16:16
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usertable")
public class UserEntity {
	
	@Id
	@GeneratedValue
	private int userid;
	
	private String username;		
	private String	email;
	private String password;

	@ManyToOne
	private InstitutionEntity institution;



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public InstitutionEntity getInstitution() {
		return institution;
	}

	public void setInstitution(InstitutionEntity institution) {
		this.institution = institution;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
