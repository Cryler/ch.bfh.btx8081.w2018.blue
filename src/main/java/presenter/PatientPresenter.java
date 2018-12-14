package presenter;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



import model.PatientModel;

public class PatientPresenter {
	private static final String PERSISTENCE_UNIT_NAME = "ch.bfh.btx8081.w2018.blue";
	
	public PatientPresenter() {
		
	}
	
//	public void setPatientFirstName(String PatientFirstName) {
//		EntityManager em = this.getEM();
//		EntityTransaction transaction = em.getTransaction();
//		transaction.begin();
//		InstitutionModel model = new InstitutionModel();
//		
//		model.setInstitutionName(PatientFi);
//		em.persist(model);
//		em.flush();
//		transaction.commit();
//	}
	
	public void setPatient(PatientModel newPatient) {
		EntityManager em = this.getEM();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		PatientModel patient = new PatientModel();
		
		patient.setFirstName(patient.getFirstName());
		
		em.persist(patient);
		em.flush();
		transaction.commit();
	}

	
	private EntityManager getEM() {
		return Persistence.createEntityManagerFactory(PatientPresenter.PERSISTENCE_UNIT_NAME).createEntityManager();
	}

}
                