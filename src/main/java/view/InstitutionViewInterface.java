
/**
 *date: 14.12.2018   -  time: 15:05:32
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;

import entity.Address;

public interface InstitutionViewInterface {
	
	public void buttonClicked(ClickEvent<Button> e);
	
	public String getInstitutionName();
	
	public Address getInstitutionAddress();
}
