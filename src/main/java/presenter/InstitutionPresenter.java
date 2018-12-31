
/**
 *date: 22.11.2018   -  time: 15:15:53
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package presenter;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;

import entity.Address;
import model.InstitutionModel;
import view.InstitutionViewInterface;

public class InstitutionPresenter implements InstitutionViewInterface {


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