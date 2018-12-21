
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
import service.EMService;

public class InstitutionModelAdmin {
	
	private EntityManager em;
	private EntityTransaction transaction;

	public void setInstitutionName(String institutionName) {
		this.em = EMService.getEM();
		this.transaction = em.getTransaction();
		this.transaction.begin();
		try {			
			Query q = this.em.createNativeQuery("select * from institution where institutionid = 1",
					InstitutionEntity.class);
			InstitutionEntity entity = (InstitutionEntity) q.getSingleResult();
			entity.setInstitutionName(institutionName);
			this.em.persist(entity);
			this.closeConnection();
			
		} catch (NoResultException e) {
			EntityManager em = EMService.getEM();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			InstitutionEntity entity = new InstitutionEntity();
			entity.setInstitutionName(institutionName);
			em.persist(entity);
			this.closeConnection();
		}
	}

	public void setInstitutionAddress(Address address) {
		EntityManager em = EMService.getEM();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Query q = em.createNativeQuery("select * from institution where institutionid = 1", InstitutionEntity.class);
		InstitutionEntity entity = (InstitutionEntity) q.getSingleResult();
		entity.setAddress(address);
		em.persist(entity);
		em.flush();
		transaction.commit();
	}
	
	private void closeConnection() {		
		this.em.flush();
		this.transaction.commit();
	}
}
