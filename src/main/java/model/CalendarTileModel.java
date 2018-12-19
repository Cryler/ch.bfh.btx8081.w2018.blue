
/**
 *date: 06.12.2018   -  time: 14:55:26
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import entity.CalendarTileEntity;
import service.EMService;


public class CalendarTileModel {

	private Date date;
	private EntityManager em;
	private EntityTransaction transaction;
	private SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");
	

	public CalendarTileModel(Date date) {
		this.date = date;
	}

	public String getKommentar() {
		this.em = EMService.getEM();
		this.transaction = em.getTransaction();
		this.transaction.begin();
		try {
			Query q = em.createNativeQuery(
					"select * from calendartile where date = '" + this.dateformatter.format(this.date) + "'",
					CalendarTileEntity.class);
			CalendarTileEntity model = (CalendarTileEntity) q.getSingleResult();
			return model.getKommentar();
		}catch(NoResultException e) {
			return "";
		}finally {
			this.closeConnection();
		}	
	}
	
	public void setKommentar(String kommentar) {
		this.em = EMService.getEM();
		this.transaction = em.getTransaction();
		this.transaction.begin();
		try {
			Query q = em.createNativeQuery(
					"select * from calendartile where date = '" + this.dateformatter.format(this.date) + "'",
					CalendarTileEntity.class);
			CalendarTileEntity entity = (CalendarTileEntity) q.getSingleResult();
			entity.setKommentar(kommentar);
			this.em.persist(entity);
		}catch(NoResultException e) {
			CalendarTileEntity entity = new CalendarTileEntity();
			entity.setDate(this.date);
			entity.setKommentar(kommentar);
			this.em.persist(entity);
		}finally {
			this.closeConnection();
		}		
	}
	
	private void closeConnection() {
		this.em.flush();
		this.transaction.commit();
		this.em.close();
	}
}
