
/**
 *date: 22.11.2018   -  time: 17:49:24
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

public class InstitutionPresenterAdmin extends InstitutionPresenter {

	private InstitutionModel model;
	private static final String PERSISTENCE_UNIT_NAME = "ch.bfh.btx8081.w2018.blue";

	public InstitutionPresenterAdmin() {
		super();
		this.model = new InstitutionModel();
	}

	public void settingsButtonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate("Settings"));
	}

	public void setInstitutionName(String institutionName, Address address) {
		EntityManager em = Persistence.createEntityManagerFactory(InstitutionPresenterAdmin.PERSISTENCE_UNIT_NAME)
				.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		InstitutionModel model = new InstitutionModel();
		Query q = em.createNativeQuery("select * from institution where institution.address_addressid = 1",
				InstitutionModel.class);
		if (q.getResultList().size() > 0) {
			model = (InstitutionModel) q.getSingleResult();
			model.setInstitutionName(institutionName);
			model.setAddress(address);
		} else {
			model.setInstitutionName(institutionName);
			model.setAddress(address);
		}
		em.persist(model);
		em.flush();
		transaction.commit();
	}

	public void setInstitutionAddress(Address address) {
		EntityManager em = Persistence.createEntityManagerFactory(InstitutionPresenterAdmin.PERSISTENCE_UNIT_NAME)
				.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(address);
		em.flush();
		transaction.commit();
	}

}
