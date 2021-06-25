package org.project.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtility {

    private static EntityManagerFactory emFactory ;

    public static EntityManagerFactory getEmFactory (){
        try {
            return emFactory = Persistence.createEntityManagerFactory("Project");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emFactory;
    }

    public static EntityManager getEntityManager(){
        return emFactory.createEntityManager();
    }

    public static void close(){
        emFactory.close();
    }
}
