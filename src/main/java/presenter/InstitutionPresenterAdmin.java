
/**
 *date: 22.11.2018   -  time: 17:49:24
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package presenter;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;

import model.Address;
import model.InstitutionModel;

public class InstitutionPresenterAdmin extends InstitutionPresenter {

	public InstitutionPresenterAdmin() {
		super();
	}

	public void settingsButtonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate("Settings"));
	}

	public void setInstitutionName(String institutionName) {
		EntityManager em = super.getEM();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		InstitutionModel model;
		Query q = em.createNativeQuery("select * from institution where institutionid = 1", InstitutionModel.class);
		if (q.getResultList().size() > 0) {
			model = (InstitutionModel) q.getSingleResult();
		} else {
			model = new InstitutionModel();
			model.setAddress(this.createDefaultAddress());
		}
		model.setInstitutionName(institutionName);
		em.persist(model);
		em.flush();
		transaction.commit();
	}

	public void setInstitutionAddress(Address newAddress) {
		EntityManager em = super.getEM();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();		
		Query q = em.createNativeQuery(
				"select * from address where addressid = (select institution.address_addressid from institution where institutionid = 1)",
				Address.class);
		if (q.getResultList().size() > 0) {
			Address address = (Address) q.getSingleResult();
			address.setStreet(newAddress.getStreet());
			address.setCity(newAddress.getCity());
			address.setStreetNr(newAddress.getStreetNr());
			address.setZipCode(newAddress.getZipCode());
			em.persist(address);
		}	
		em.flush();
		transaction.commit();
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
