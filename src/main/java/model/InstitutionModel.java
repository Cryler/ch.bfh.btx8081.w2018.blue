
/**
 *date: 22.11.2018   -  time: 15:15:37
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
import service.EMService;

/**
 * This class stores and represents the {@code Institution} in the DB. It
 * contains a name and a {@code Address}.
 * 
 * 
 * @author yanng
 *
 */

public class InstitutionModel {
	
	private EntityManager em;
	private EntityTransaction transaction;

	/**
	 * The {@value institutionID} is set to 1 because there should only be one
	 * institution at a given time.
	 * 
	 */

	public String getInstitutionName() {
		this.em = EMService.getEM();
		this.transaction = em.getTransaction();
		this.transaction.begin();
		try {
			Query q = this.em.createNativeQuery("select * from institution where institutionid = 1",
					InstitutionEntity.class);
			InstitutionEntity entity = (InstitutionEntity) q.getSingleResult();
			return entity.getInstitutionName();
		} catch (NoResultException e) {
			return "Default_Name";
		} finally {
			this.closeConnection();
		}
	}

	public Address getInstitutionAddress() {
		this.em = EMService.getEM();
		this.transaction = em.getTransaction();
		this.transaction.begin();
		try {
			Query q = this.em.createNativeQuery("select * from institution where institutionid = 1",
					InstitutionEntity.class);
			InstitutionEntity entity = (InstitutionEntity) q.getSingleResult();

			return entity.getInstitutionAddress();

		} catch (NoResultException e) {
			return this.createDefaultAddress();
		} finally {
			this.closeConnection();
		}
	}
	private void closeConnection() {
		this.em.flush();
		this.transaction.commit();
		this.em.close();
	}

	private Address createDefaultAddress() {
		Address defaultAddress = new Address();
		defaultAddress.setStreet("Default_Street");
		defaultAddress.setStreetNr(1);
		defaultAddress.setCity("Default_City");
		defaultAddress.setZipCode(1234);
		return defaultAddress;
	}

}
