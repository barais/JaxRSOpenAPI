package fr.istic.taa.jaxrs;

import fr.istic.taa.jaxrs.dao.generic.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JpaTest {
    public static void main(String[] args) {

        EntityManager manager = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();


        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();


        manager.close();
        EntityManagerHelper.closeEntityManagerFactory();
        //		factory.close();
    }
}