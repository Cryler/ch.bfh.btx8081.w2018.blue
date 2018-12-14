
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

import entity.InstitutionEntity;
import service.EMService;

public class InstitutionModelAdmin {

	public void setInstitutionName(String institutionName) {
		try {
			EntityManager em = EMService.getEM();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			Query q = em.createNativeQuery("select * from institution where institutionid = 1",
					InstitutionEntity.class);
			InstitutionEntity entity = (InstitutionEntity) q.getSingleResult();
			entity.setInstitutionName(institutionName);
			em.persist(entity);
			em.flush();
			transaction.commit();
		} catch (NoResultException e) {
			EntityManager em = EMService.getEM();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			InstitutionEntity entity = new InstitutionEntity();
			entity.setInstitutionName(institutionName);
			em.persist(entity);
			em.flush();
			transaction.commit();
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
}
