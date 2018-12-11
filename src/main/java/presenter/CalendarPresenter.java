
/**
 *date: 11.12.2018   -  time: 20:07:15
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package presenter;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;

import model.Address;
import model.InstitutionModel;

public class CalendarPresenter {

	private static final String PERSISTENCE_UNIT_NAME = "ch.bfh.btx8081.w2018.blue";

	public void buttonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate(e.getSource().getText()));
	}

	public String getInstitutionData() {
		EntityManager em = this.getEM();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		String institutionData = "";
		Query q = em.createNativeQuery("select * from institution where institutionid = 1", InstitutionModel.class);
		if (q.getResultList().size() > 0) {
			InstitutionModel model = (InstitutionModel) q.getSingleResult();
			institutionData = model.getInstitutionName();
		}
		q = em.createNativeQuery(
				"select * from address where addressid = (select addressid from institution where institutionid = 1)",
				Address.class);
		if (q.getResultList().size() > 0) {
			Address address = (Address) q.getSingleResult();
			institutionData = institutionData + "\n" + address.getStreet() + " " + address.getStreetNr() + "\n"
					+ address.getZipCode() + " " + address.getCity();
		}
		return institutionData;
	}

	private EntityManager getEM() {
		return Persistence.createEntityManagerFactory(CalendarPresenter.PERSISTENCE_UNIT_NAME).createEntityManager();
	}

}
