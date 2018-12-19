
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
import view.InstitutionViewInterface;

public class InstitutionPresenter implements InstitutionViewInterface {

	private static final String PERSISTENCE_UNIT_NAME = "ch.bfh.btx8081.w2018.blue";
	

	@Override
	public void buttonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate(e.getSource().getText()));
	}

	public String getInstitutionName() {
		InstitutionModel model = new InstitutionModel();
		return model.getInstitutionName();
	}

	public Address getInstitutionAddress() {
		InstitutionModel model = new InstitutionModel();
		return model.getInstitutionAddress();
	}
	

}