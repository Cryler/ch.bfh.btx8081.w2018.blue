package model;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


import entity.PatientEntity;
import entity.PersonEntity;
import entity.SessionEntity;
import presenter.PatientPresenter;
import service.EMService;
import service.PatientService;

public class PatientModel {

	private EntityManager em;
	private EntityTransaction transaction;

	
	
	public void setPatient(PatientEntity patient) {
		this.em = EMService.getEM();
		this.transaction = EMService.getTransaction();
		this.transaction.begin();
		this.em.persist(patient);
		PatientService.setPatient(patient);
		this.closeConnection();
	}

	public Collection<PatientEntity> getPatient() {
		this.em = EMService.getEM();
		this.transaction = EMService.getTransaction();
		this.transaction.begin();
		Query q = this.em.createNativeQuery("select * from person", PatientEntity.class);
		Collection<PatientEntity> patient = q.getResultList();
		this.closeConnection();
		return patient;
	}


	private void closeConnection() {
		this.em.flush();
		this.transaction.commit();
	}

}
