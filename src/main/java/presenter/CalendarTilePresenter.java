
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

/**
 * The Class CalendarTilePresenter.
 * 
 * @author gundy1.
 */
public class CalendarTilePresenter {

	
	private Date date;
	
	private CalendarModel model;

	/**
	 * Instantiates a new calendar tile presenter.
	 *
	 * @param date the date
	 */
	public CalendarTilePresenter(Date date) {
		this.date = date;
		this.model = new CalendarModel();
	}

	/**
	 * Gets the patient names.
	 *
	 * @return the patient names
	 */
	public List<PatientEntity> getPatientNames() {
		return this.model.getPatientNames();
	}

	/**
	 * Gets the data of entry.
	 *
	 * @return the data of entry
	 */
	public CalendarTileEntity getDataOfEntry() {
		return this.model.getDataOfEntry(this.date);
	}
	
	/**
	 * Sets the data of entry.
	 *
	 * @param patient the patient
	 * @param kommentar the comment to the entry
	 */
	public void setDataOfEntry(PatientEntity patient, String kommentar) {
		this.model.setDataOfEntry(patient, kommentar, this.date);
	}
}
