
/**
 *date: 13.12.2018   -  time: 13:44:20
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package presenter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.CalendarTileModel;
import model.PatientModel;

public class CalendarTilePresenter {

	private static final String PERSISTENCE_UNIT_NAME = "ch.bfh.btx8081.w2018.blue";
	SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");
	private Date date;

	public String getPatientName() {
		EntityManager em = this.getEM();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Query q = em.createNativeQuery(
				"select * from person where person.id = (select patient.id from patient where patient.id = (select patient_id from calendartile where date = '"
						+ this.dateformatter.format(this.date) + "' limit 1))",
				PatientModel.class);
		if (q.getResultList().size() > 0) {
			PatientModel patient = (PatientModel) q.getSingleResult();
			return patient.getLastName() + " " + patient.getFirstName();
		}
		return "";

	}

	public void setPatientName(String newName) {
		// TODO
	}

	public String getKommentar() {
		EntityManager em = this.getEM();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Query q = em.createNativeQuery(
				"select * from calendartile where date = '" + this.dateformatter.format(this.date) + "'",
				CalendarTileModel.class);
		if (q.getResultList().size() > 0) {
			CalendarTileModel model = (CalendarTileModel) q.getSingleResult();
			return model.getKommentar();
		}
		return "";
	}

	public void setKommentar(String kommentar) {
//TODO
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
		
	}
	private EntityManager getEM() {
		return Persistence.createEntityManagerFactory(CalendarTilePresenter.PERSISTENCE_UNIT_NAME)
				.createEntityManager();
	}
}
