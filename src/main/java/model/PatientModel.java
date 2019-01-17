package model;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entity.PatientEntity;
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
		@SuppressWarnings("unchecked")
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
