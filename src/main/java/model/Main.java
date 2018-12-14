package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

//		EntityManagerFactory emfactory = Persistence.
//				createEntityManagerFactory( "ch.bfh.btx8081.w2018.blue" );
//				EntityManager entitymanager = emfactory.
//				createEntityManager( );
//				entitymanager.getTransaction( ).begin( );


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ch.bfh.btx8081.w2018.blue");
        EntityManager em = factory.createEntityManager();


        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        PatientModel pers1 = new PatientModel();


//em.getTransaction().begin();
        pers1.setFirstName("Hansi");
        pers1.setLastName("Muster");
        pers1.setBirthdate("01.01.1980");
//					pers1.setInsurance("Assura");
//					pers1.setAhvNr("73458373");
        em.persist(pers1);
        em.flush();
        transaction.commit();

    }


//				entitymanager.persist(pers1);
//				entitymanager.getTransaction().commit();
//				entitymanager.close();
//				emfactory.close();


}
