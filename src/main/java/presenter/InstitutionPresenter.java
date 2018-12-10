
/**
 *date: 22.11.2018   -  time: 15:15:53
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package presenter;

import java.util.Observable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;

import model.Address;
import model.InstitutionModel;

public class InstitutionPresenter extends Observable {

	private static final String PERSISTENCE_UNIT_NAME = "ch.bfh.btx8081.w2018.blue";

	public InstitutionPresenter() {

	}

	public void buttonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate(e.getSource().getText()));
	}

	public String getInstitutionName() {
		EntityManager em = Persistence.createEntityManagerFactory(InstitutionPresenter.PERSISTENCE_UNIT_NAME)
				.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Query q = em.createNativeQuery("select * from institution where institution.address_addressid = 1",InstitutionModel.class);
		if(q.getResultList().size() > 0) {
			InstitutionModel model = (InstitutionModel) q.getSingleResult();
			return model.getInstitutionName();
		}
		return "Default_Name";
		
	}

	public Address getInstitutionAddress() {
		EntityManager em = Persistence.createEntityManagerFactory(InstitutionPresenter.PERSISTENCE_UNIT_NAME)
				.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Query q = em.createQuery("select a from Address a where a.addressID = 1", Address.class);
		if(q.getResultList().size() > 0) {
			return (Address) q.getSingleResult();
		}
		Address defaultAddress = new Address();
		defaultAddress.setStreet("Default_Street");
		defaultAddress.setStreetNr(1);
		defaultAddress.setCity("Default_City");
		defaultAddress.setZipCode(1234);
		return defaultAddress;
	}
}