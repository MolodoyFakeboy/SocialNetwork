package org.project.Util;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;

@Component
public class JPAUtility {

    private static EntityManagerFactory emFactory ;

    @PostConstruct
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
