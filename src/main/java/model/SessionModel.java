package model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import entity.PatientEntity;
import entity.SessionEntity;
import service.EMService;

public class SessionModel {

	private EntityManager em;
	private EntityTransaction transaction;
	
	public Collection<SessionEntity> getAllSessionsFromPatient(PatientEntity patient){
		try {
			this.em = EMService.getEM();
			this.transaction = EMService.getTransaction();
			this.transaction.begin();
			Query q = em.createNativeQuery("select * from session where session.patient_id = "+patient.getPK(), SessionEntity.class);
			Collection<SessionEntity> result = q.getResultList();
			return result;
		} catch (NoResultException e) {
			return null;
		} finally {
			this.closeConnection();
		}
	}
	
	public void setSession(SessionEntity entity) {
		this.em = EMService.getEM();
		this.transaction = EMService.getTransaction();
		this.transaction.begin();
		this.em.persist(entity);
		this.closeConnection();
	}
	
	private void closeConnection() {
		this.em.flush();
		this.transaction.commit();
	
	}

}


//
//public String getDescription() {
//	try {
//		this.em = EMService.getEM();
//		this.transaction = em.getTransaction();
//		transaction.begin();
//		Query q = em.createNativeQuery("select * from session where ", 
//				SessionEntity.class);
//		SessionEntity entity = (SessionEntity) q.getSingleResult();
//		return entity.getDescription();
//	} catch (NoResultException e) {
//		return "Error loading description";
//	} finally {
//		this.closeConnection();
//	}
//
//}
//
//public Date getDate() {
//	try {
//		this.em = EMService.getEM();
//		this.transaction = em.getTransaction();
//		transaction.begin();
//		Query q = em.createNativeQuery("select date from session where ", SessionEntity.class); // richtiges Query einfügen. Kann man irgendwie die richtig SessionId einbinden?
//		SessionEntity entity = (SessionEntity) q.getSingleResult();
//		return entity.getDate();
//	} catch (NoResultException e) {
//		return null;
//	} finally {
//		this.closeConnection();
//	}
//
//}
//
//public int getCraving() {
//	try {
//		this.em = EMService.getEM();
//		this.transaction = em.getTransaction();
//		transaction.begin();
//		Query q = em.createNativeQuery("select craving from session where ", // richtiges Query einfügen. Kann man irgendwie die richtig SessionId einbinden?
//				SessionEntity.class);
//		SessionEntity entity = (SessionEntity) q.getSingleResult();
//		return entity.getCraving();
//	} catch (NoResultException e) {
//		return 0;
//	}  finally {
//		this.closeConnection();
//	}
//
//}
