package model;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;



import entity.PatientEntity;

import exception.InvalidEmailException;

import exception.InvalidUsernameException;
import service.EMService;



public class PatientModel {

	private EntityManager em;
	private EntityTransaction transaction;

	public void setPatient(PatientEntity patient) throws InvalidUsernameException, InvalidEmailException {
		this.em = EMService.getEM();
		this.transaction = EMService.getTransaction();
		this.transaction.begin();
		
			em.persist(patient);
		
			this.closeConnection();
		}
	

	private void closeConnection() {
		this.em.flush();
		this.transaction.commit();
	}
}
