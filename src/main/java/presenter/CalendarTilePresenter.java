
/**
 *date: 13.12.2018   -  time: 13:44:20
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package presenter;

import java.util.Date;

import model.CalendarTileModel;

public class CalendarTilePresenter {

	private Date date;
	private CalendarTileModel model;

	public CalendarTilePresenter(Date date) {
		this.date = date;
		this.model = new CalendarTileModel(this.date);
	}

	public String getKommentar() {
		return model.getKommentar();
	}

	public void setKommentar(String kommentar) {
		this.model.setKommentar(kommentar);
	}

	public void getPatientName() {
		// TODO
	}

	public void setPatientName(String newName) {
		// TODO
	}

}
