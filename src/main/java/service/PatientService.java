
/**
 *date: 10.01.2019   -  time: 13:49:50
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package service;

import entity.PatientEntity;

/**
 * The Class PatientService. This Class simply acts as a reference storage while switching between views.
 * The {@value patient} will be overwritten if a new Patient gets selected in the {@code PatientFilterView}.
 * 
 * @author gundy1.
 */
public  class PatientService {

	/** The current chosen patient. */
	private static PatientEntity patient;
	
	/**
	 * Gets the patient.
	 *
	 * @return the patient
	 */
	public static PatientEntity getPatient() {
		return PatientService.patient;
	}
	
	/**
	 * Sets the patient.
	 *
	 * @param tempPatient the new selected patient
	 */
	public static void setPatient(PatientEntity tempPatient) {
		PatientService.patient = tempPatient;
	}
}
