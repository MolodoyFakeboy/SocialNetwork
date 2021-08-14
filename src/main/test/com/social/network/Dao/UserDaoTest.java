package com.social.network.Dao;

import com.social.network.Configs.Config;
import com.social.network.Model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
class UserDaoTest {

    GenericDao<User> userGenericDao;

    @Autowired
    public UserDaoTest(GenericDao<User> userGenericDao) {
        this.userGenericDao = userGenericDao;
    }

    @Test
    void add() {
        Date date = Date.valueOf("2002-11-16");
        User user = new User("Zahar","Testds","Zahasdsdhar@gmail.com",date);
        userGenericDao.add(user);
    }

    @Test
    void update() {

    }

    @Test
    void delete() {

    }

    @Test
    void find() {

    }
}