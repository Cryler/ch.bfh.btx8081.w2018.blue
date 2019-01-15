
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
import entity.PatientEntity;
import entity.PersonEntity;
import entity.UserEntity;
import service.EMService;
import service.UserService;


/**
 * The Class CalendarModel.
 * 
 * @author gundy1
 */
public class CalendarModel {

	/** The Formatter that brings the Date in the Form yyyy-MM-dd. */
	private SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");

	private EntityTransaction transaction;
	
	private EntityManager em;


	/**
	 * Gets the institution data from the DB.
	 * If there is a {@code NullPointerException} a Default Institution is created with a Default Name and a Default Address.
	 *
	 * @return the institution data
	 */
	public String getInstitutionData() {
		UserEntity currentUser = UserService.getUser();
		try {
			String address = currentUser.getInstitution().getInstitutionName()+"\n"+currentUser.getInstitution().getInstitutionAddress().toString();
			return address;
		} catch (NullPointerException e) {
			return "Default_Name\n"+this.createDefaultAddress().toString();
		} 
	}
	
	

	/**
	 * Gets the data of a single Calendar entry. The Entry is searched after by the Date.
	 * If there is a {@code NoResultException} a empty Entry is created and returned.
	 *
	 * @param date the date of the Entry.
	 * @return the data of entry
	 */
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
	
	
	/**
	 * Sets the data of entry. If there is no Entry yet a {@code NoResultException} is thrown and afterwards a new Entry is created.
	 *
	 * @param patient the patient that the Entry belongs to
	 * @param kommentar the comment if there is anything special to remember.
	 * @param date the date of the entry.
	 */
	public void setDataOfEntry(PatientEntity patient, String kommentar, Date date) {
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


	/**
	 * Gets the all Patients that are stored in the DB.
	 *
	 * @return the patients.
	 */
	@SuppressWarnings("unchecked")
	public List<PatientEntity> getPatientNames() {
		this.em = EMService.getEM();
		this.transaction = EMService.getTransaction();
		this.transaction.begin();
		Query q = em.createNativeQuery("select * from person", PersonEntity.class);
		Collection<PatientEntity> persons = q.getResultList();
		this.closeConnection();
		List<PatientEntity> data = new ArrayList<>();
		for(PatientEntity pat : persons) {
			data.add(pat);
		}
		return data;
	}

	/**
	 * Closes the current connection to the db.
	 */
	private void closeConnection() {
		this.em.flush();
		this.transaction.commit();
	}

	/**
	 * Creates the default entity.
	 *
	 * @param date the date
	 * @return the calendar tile entity
	 */
	private CalendarTileEntity createDefaultEntity(Date date) {
		CalendarTileEntity entity = new CalendarTileEntity();
		entity.setDate(date);
		entity.setKommentar("");
		entity.setPatient(null);
		return entity;
	}

	/**
	 * Creates the default address.
	 *
	 * @return the address
	 */
	private Address createDefaultAddress() {
		Address defaultAddress = new Address();
		defaultAddress.setStreet("Default_Street");
		defaultAddress.setStreetNr(1);
		defaultAddress.setCity("Default_City");
		defaultAddress.setZipCode(1234);
		return defaultAddress;
	}

	
}
