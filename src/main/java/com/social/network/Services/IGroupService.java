package com.social.network.Services;

import com.social.network.Model.Group;

import java.security.Principal;
import java.util.List;

public interface IGroupService {

    Group createNewGroup(Group group);

    Group findGroupByID(int groupID);

    List<Group> findall();

    Group subcribeOnGroup(int groupID, Principal principal);

    boolean deleateGroup(int groupID);

    List<Group> findGroupByName(String name);

}
