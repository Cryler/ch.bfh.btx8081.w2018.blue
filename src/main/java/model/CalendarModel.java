
/**
 *date: 11.12.2018   -  time: 20:44:35
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

public class CalendarModel {
	
	private EntityManager em;
	private EntityTransaction transaction;

	public CalendarModel() {

	}

	public String getInstitutionData() {
		this.em = EMService.getEM();
		this.transaction = em.getTransaction();
		this.transaction.begin();
		try {		
			Query q = this.em.createNativeQuery("select * from institution where institutionid = 1", InstitutionEntity.class);
			InstitutionEntity model = (InstitutionEntity) q.getSingleResult();
			return model.getInstitutionName() + "\n" + model.getInstitutionAddress().toString();
		} catch (NoResultException e) {
			return "Default_Institution\n" + this.createDefaultAddress().toString();
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
