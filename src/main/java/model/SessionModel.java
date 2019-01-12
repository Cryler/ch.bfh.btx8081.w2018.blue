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
