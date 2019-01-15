
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

/**
 * The Class UserEntity that stores the data of a user.
 * 
 * @author gundy1
 */
@Entity
@Table(name = "usertable")
public class UserEntity {
	
	/** The unique userId, is the PK in the DB. */
	@Id
	@GeneratedValue
	private int userid;
	

	private String username;		
	

	private String	email; //Email could be used for authorization but this functionality isn't implemented yet.
	
	
	private String password;

	@ManyToOne
	private InstitutionEntity institution;



	/**
	 * Gets the email of the user.
	 *
	 * @return the email of the user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the user.
	 *
	 * @param email the new email of the user
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the password of the user.
	 *
	 * @return the password of the user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the user.
	 *
	 * @param password the new password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the institution of the user
	 * 
	 * @return the institution of the user
	 */
	public InstitutionEntity getInstitution() {
		return institution;
	}

	/**
	 * Sets the institution of the user.
	 *
	 * @param institution the new institution of the user
	 */
	public void setInstitution(InstitutionEntity institution) {
		this.institution = institution;
	}

	/**
	 * Gets the username of the user.
	 *
	 * @return the username of the user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username of the user.
	 *
	 * @param username the new username of the user
	 */
	public void setUsername(String username) {
		this.username = username;
	}

}
