
/**
 *date: 22.11.2018   -  time: 15:15:53
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

public class InstitutionPresenter  {

	private static final String PERSISTENCE_UNIT_NAME = "ch.bfh.btx8081.w2018.blue";

	
	public void buttonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate(e.getSource().getText()));
	}

	public String getInstitutionName() {
		EntityManager em = this.getEM();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Query q = em.createNativeQuery("select * from institution where institutionid = 1", InstitutionModel.class);
		if (q.getResultList().size() > 0) {
			InstitutionModel model = (InstitutionModel) q.getSingleResult();
			return model.getInstitutionName();
		}
		return "Default_Name";
	}

	public Address getInstitutionAddress() {
		EntityManager em = this.getEM();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Query q = em.createNativeQuery("select * from address where addressid = (select addressid from institution where institutionid = 1)", Address.class);
		if (q.getResultList().size() > 0) {
		 return (Address) q.getSingleResult();
		}
		return this.createDefaultAddress();
	}

	protected EntityManager getEM() {
		return Persistence.createEntityManagerFactory(InstitutionPresenter.PERSISTENCE_UNIT_NAME).createEntityManager();
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