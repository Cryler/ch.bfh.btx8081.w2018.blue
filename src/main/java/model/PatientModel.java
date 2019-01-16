package model;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


import entity.PatientEntity;
import entity.PersonEntity;
import entity.SessionEntity;
import presenter.PatientPresenter;
import service.EMService;
import service.PatientService;


/**
 * The Class PatientModel.
 * 
 * @author gehry1
 * 
 */
public class PatientModel {

	/** The em. */
	private EntityManager em;
	
	/** The transaction. */
	private EntityTransaction transaction;

	
	
	/**
	 * Set a new patient.
	 *
	 * @param patient the new patient
	 */
	public void setPatient(PatientEntity patient) {
		this.em = EMService.getEM();
		this.transaction = EMService.getTransaction();
		this.transaction.begin();
		this.em.persist(patient);
		PatientService.setPatient(patient);
		this.closeConnection();
	}

	/**
	 * Gets the patient sorted by lastname ascending.
	 *
	 * @return the patient
	 */
	public Collection<PatientEntity> getPatient() {
		this.em = EMService.getEM();
		this.transaction = EMService.getTransaction();
		this.transaction.begin();
		Query q = this.em.createNativeQuery("select * from person order by lastname ASC", PatientEntity.class);
		Collection<PatientEntity> patient = q.getResultList();
		this.closeConnection();
		return patient;
	}
	
	/**
	 * Delete the patient.
	 *
	 * @param patient the patient
	 */
	public void deletePatient(PatientEntity patient) {
		this.em = EMService.getEM();
		this.transaction = EMService.getTransaction();
		this.transaction.begin();
		this.em.remove(patient);
		this.closeConnection();
	}


	/**
	 * Close connection.
	 */
	private void closeConnection() {
		this.em.flush();
		this.transaction.commit();
	}

}
