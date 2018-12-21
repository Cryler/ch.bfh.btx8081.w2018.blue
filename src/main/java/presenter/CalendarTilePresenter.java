
/**
 *date: 13.12.2018   -  time: 13:44:20
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package presenter;

import java.util.Date;

import model.CalendarModel;

public class CalendarTilePresenter {

	private Date date;
	private CalendarModel model;

	public CalendarTilePresenter(Date date) {
		this.date = date;
		this.model = new CalendarModel();
	}

	public String getKommentar() {
		return model.getKommentar(this.date);
	}

	public void setKommentar(String kommentar) {
		this.model.setKommentar(kommentar, this.date);
	}

	public void getPatientName() {
		// TODO
	}

	public void setPatientName(String newName) {
		// TODO
	}

}
