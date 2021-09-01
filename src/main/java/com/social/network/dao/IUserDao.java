package com.social.network.dao;

import com.social.network.model.User;


public interface IUserDao extends GenericDao<User>  {
    User findByName(String name);
}
