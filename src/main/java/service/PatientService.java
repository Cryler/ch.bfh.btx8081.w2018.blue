
/**
 *date: 10.01.2019   -  time: 13:49:50
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package service;

import entity.PatientEntity;
import entity.PersonEntity;

public  class PatientService {

	private static PatientEntity patient;
	
	public static PatientEntity getPatient() {
		return PatientService.patient;
	}
	
	public static void setPatient(PatientEntity tempPatient) {
		PatientService.patient = tempPatient;
	}
}
