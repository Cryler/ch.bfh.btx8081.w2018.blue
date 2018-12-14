package model;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import entity.SessionEntity;
import service.EMService;

public class SessionModel {

	public String getDescription() {
		try {
			EntityManager em = EMService.getEM();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			Query q = em.createNativeQuery("select description from session where ", // richtiges Query einfügen. Kann man irgendwie die richtig SessionId einbinden?
					SessionEntity.class);
			SessionEntity entity = (SessionEntity) q.getSingleResult();
			return entity.getDescription();
		} catch (NoResultException e) {
			return "Error loading description";
		}

	}

	public Date getDate() {
		try {
			EntityManager em = EMService.getEM();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			Query q = em.createNativeQuery("select date from session where ", SessionEntity.class); // richtiges Query einfügen. Kann man irgendwie die richtig SessionId einbinden?
			SessionEntity entity = (SessionEntity) q.getSingleResult();
			return entity.getDate();
		} catch (NoResultException e) {
			return null;
		}

	}

	public int getCraving() {
		try {
			EntityManager em = EMService.getEM();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			Query q = em.createNativeQuery("select craving from session where ", // richtiges Query einfügen. Kann man irgendwie die richtig SessionId einbinden?
					SessionEntity.class);
			SessionEntity entity = (SessionEntity) q.getSingleResult();
			return entity.getCraving();
		} catch (NoResultException e) {
			return (Integer) null;
		}

	}

}
