
/**
 *date: 11.12.2018   -  time: 20:44:35
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import entity.Address;
import entity.CalendarTileEntity;
import entity.InstitutionEntity;
import entity.PatientEntity;
import entity.PersonEntity;
import service.EMService;

public class CalendarModel {

	private SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");
	private EntityTransaction transaction;
	private EntityManager em;

	public CalendarModel() {

	}

	public String getInstitutionData() {
		this.em = EMService.getEM();
		this.transaction = EMService.getTransaction();
		this.transaction.begin();
		try {
			Query q = this.em.createNativeQuery("select * from institution where institutionid = 1",
					InstitutionEntity.class);
			InstitutionEntity model = (InstitutionEntity) q.getSingleResult();
			return model.getInstitutionName() + "\n" + model.getInstitutionAddress().toString();
		} catch (NoResultException e) {
			return "Default_Institution\n" + this.createDefaultAddress().toString();
		} finally {
			this.closeConnection();
		}
	}

	public CalendarTileEntity getDataOfEntry(Date date) {
		this.em = EMService.getEM();
		this.transaction = em.getTransaction();
		this.transaction.begin();
		try {
			Query q = em.createNativeQuery(
					"select * from calendartile where date = '" + this.dateformatter.format(date) + "'",
					CalendarTileEntity.class);
			CalendarTileEntity entity = (CalendarTileEntity) q.getSingleResult();
			return entity;
		} catch (NoResultException e) {
			return this.createDefaultEntity(date);
		} finally {
			this.closeConnection();
		}
	}
	public void setDataOfEntry(String patient, String kommentar, Date date) {
		this.em = EMService.getEM();
		this.transaction = em.getTransaction();
		this.transaction.begin();
		try {
			Query q = em.createNativeQuery(
					"select * from calendartile where date = '" + this.dateformatter.format(date) + "'",
					CalendarTileEntity.class);
			CalendarTileEntity entity = (CalendarTileEntity) q.getSingleResult();
			entity.setPatient(patient);
			entity.setKommentar(kommentar);
			this.em.persist(entity);
		} catch (NoResultException e) {
			CalendarTileEntity entity = new CalendarTileEntity();
			entity.setDate(date);
			entity.setPatient(patient);
			entity.setKommentar(kommentar);
			this.em.persist(entity);
		} finally {
			this.closeConnection();
		}
	}


	@SuppressWarnings("unchecked")
	public List<String> getPatientNames() {
		this.em = EMService.getEM();
		this.transaction = EMService.getTransaction();
		this.transaction.begin();
		Query q = em.createNativeQuery("select * from person", PersonEntity.class);
		Collection<PatientEntity> persons = q.getResultList();
		this.closeConnection();
		List<String> names = new ArrayList<>();
		for (PatientEntity pers : persons) {
			names.add(pers.getLastName() + " " + pers.getFirstName());
		}
		return names;
	}

	private void closeConnection() {
		this.em.flush();
		this.transaction.commit();
	}

	private CalendarTileEntity createDefaultEntity(Date date) {
		CalendarTileEntity entity = new CalendarTileEntity();
		entity.setDate(date);
		entity.setKommentar("");
		entity.setPatient("");
		return entity;
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
