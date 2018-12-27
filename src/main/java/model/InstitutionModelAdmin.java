
/**
 *date: 14.12.2018   -  time: 15:34:40
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import entity.Address;
import entity.InstitutionEntity;
import entity.UserEntity;
import service.EMService;
import service.UserService;

public class InstitutionModelAdmin {

	private EntityManager em;
	private EntityTransaction transaction;

	public void setInstitutionName(String institutionName) {
		UserEntity currentUser = UserService.getUser();
		this.em = EMService.getEM();
		this.transaction = em.getTransaction();
		this.transaction.begin();
		try {
			Query q = em.createNativeQuery(
					"select * from institution where institution.institutionid = (select institution_institutionid from usertable where username = '"
							+ currentUser.getUsername() + "')",
					InstitutionEntity.class);
			InstitutionEntity institution = (InstitutionEntity) q.getSingleResult();
			institution.setInstitutionName(institutionName);
			this.em.persist(institution);
			currentUser.setInstitution(institution);
			this.em.persist(currentUser);
		} catch (NoResultException e) {
			InstitutionEntity institution = new InstitutionEntity();
			institution.setInstitutionName(institutionName);
			this.em.persist(institution);
			currentUser.setInstitution(institution);
			this.em.persist(currentUser);
		} finally {			
			this.closeConnection();
		}
	}

	public void setInstitutionAddress(Address address) {
		UserEntity currentUser = UserService.getUser();
		this.em = EMService.getEM();
		this.transaction = this.em.getTransaction();
		this.transaction.begin();
		InstitutionEntity institution = currentUser.getInstitution();
		institution.setAddress(address);
		this.em.persist(institution);
		this.closeConnection();
	}

	private void closeConnection() {
		this.em.flush();
		this.transaction.commit();
	}
}
