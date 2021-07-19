package org.project.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Component
public class JPAUtility {

    private  EntityManagerFactory emFactory ;

    @Autowired
    public JPAUtility(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public EntityManager getEntityManager(){
        return emFactory.createEntityManager();
    }

}
