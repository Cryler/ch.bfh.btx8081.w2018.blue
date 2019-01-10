
/**
 *date: 10.01.2019   -  time: 13:49:50
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package service;

import entity.PersonEntity;

public  class PatientService {

	private static PersonEntity patient;
	
	public static PersonEntity getPatient() {
		return PatientService.patient;
	}
	
	public static void setPatient(PersonEntity tempPatient) {
		PatientService.patient = tempPatient;
	}
}
