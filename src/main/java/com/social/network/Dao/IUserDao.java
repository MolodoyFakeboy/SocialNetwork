package com.social.network.Dao;

import com.social.network.Model.User;


public interface IUserDao extends GenericDao<User>  {
    User findByName(String name);
}
