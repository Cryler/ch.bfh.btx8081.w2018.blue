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

/**
 * Model class for the sessions. 
 * 
 * @author leuel3
 * @author gundy1
 *
 */
public class SessionModel {

	private EntityManager em;
	private EntityTransaction transaction;
	
	/**
	 * Gets all sessions of a specific patient that are stored in the db. Always closes the db connection in the end.
	 * 
	 * @param patient the patient you want to see all sessions of.
	 * @return the sessions.
	 */
	public Collection<SessionEntity> getAllSessionsFromPatient(PatientEntity patient){
		try {
			this.em = EMService.getEM();
			this.transaction = EMService.getTransaction();
			this.transaction.begin();
			Query q = em.createNativeQuery("select * from session where session.patient_id = "+patient.getPK(), SessionEntity.class);
			Collection<SessionEntity> result = q.getResultList();
			if(result.size() == 0) {
				throw new NoResultException();
			}
			return result;
		} finally {
			this.closeConnection();
		}
	}
	
	/**
	 * Sets the session. Always closes the db connection in the end.
	 * 
	 * @param entity the session.
	 */
	public void setSession(SessionEntity entity) {
		this.em = EMService.getEM();
		this.transaction = EMService.getTransaction();
		this.transaction.begin();
		this.em.persist(entity);
		this.closeConnection();
	}
	
	/**
	 * Closes the current connection to the db.
	 */
	private void closeConnection() {
		this.em.flush();
		this.transaction.commit();
	
	}

}
