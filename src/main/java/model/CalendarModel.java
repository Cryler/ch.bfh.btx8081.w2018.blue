
/**
 *date: 11.12.2018   -  time: 20:44:35
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import entity.Address;
import entity.CalendarTileEntity;
import entity.InstitutionEntity;
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
			Query q = this.em.createNativeQuery("select * from institution where institutionid = 1", InstitutionEntity.class);
			InstitutionEntity model = (InstitutionEntity) q.getSingleResult();
			return model.getInstitutionName() + "\n" + model.getInstitutionAddress().toString();
		} catch (NoResultException e) {
			return "Default_Institution\n" + this.createDefaultAddress().toString();
		} finally {
			this.closeConnection();
		}
	}

	public String getKommentar(Date date) {
		this.em = EMService.getEM();
		this.transaction = em.getTransaction();
		this.transaction.begin();
		try {
			Query q = em.createNativeQuery(
					"select * from calendartile where date = '" + this.dateformatter.format(date) + "'",
					CalendarTileEntity.class);
			CalendarTileEntity model = (CalendarTileEntity) q.getSingleResult();
			return model.getKommentar();
		}catch(NoResultException e) {
			return "";
		}finally {
			this.closeConnection();
		}	
	}
	
	public void setKommentar(String kommentar, Date date) {
		this.em = EMService.getEM();
		this.transaction = em.getTransaction();
		this.transaction.begin();
		try {
			Query q = em.createNativeQuery(
					"select * from calendartile where date = '" + this.dateformatter.format(date) + "'",
					CalendarTileEntity.class);
			CalendarTileEntity entity = (CalendarTileEntity) q.getSingleResult();
			entity.setKommentar(kommentar);
			this.em.persist(entity);
		}catch(NoResultException e) {
			CalendarTileEntity entity = new CalendarTileEntity();
			entity.setDate(date);
			entity.setKommentar(kommentar);
			this.em.persist(entity);
		}finally {
			this.closeConnection();
		}		
	}
	
	private void closeConnection() {
		this.em.flush();
		this.transaction.commit();
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
