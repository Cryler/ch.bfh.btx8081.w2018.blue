
/**
 *date: 13.12.2018   -  time: 13:44:20
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package presenter;

import java.util.Date;
import java.util.List;

import entity.CalendarTileEntity;
import entity.PatientEntity;
import model.CalendarModel;

public class CalendarTilePresenter {

	private Date date;
	private CalendarModel model;

	public CalendarTilePresenter(Date date) {
		this.date = date;
		this.model = new CalendarModel();
	}

	public List<PatientEntity> getPatientNames() {
		return this.model.getPatientNames();
	}

	public CalendarTileEntity getDataOfEntry() {
		return this.model.getDataOfEntry(this.date);
	}
	
	public void setDataOfEntry(PatientEntity patient, String kommentar) {
		this.model.setDataOfEntry(patient, kommentar, this.date);
	}
}
