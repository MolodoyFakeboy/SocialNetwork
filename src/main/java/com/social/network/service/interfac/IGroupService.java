package com.social.network.service.interfac;

import com.social.network.dto.PublicationDTO;
import com.social.network.model.Group;

import java.security.Principal;
import java.util.List;

public interface IGroupService {

    Group createNewGroup(Group group,Principal principal);

    Group findGroupByID(int groupID);

    List<Group> findall();

    Group subcribeOnGroup(int groupID, Principal principal);

    String unsubscribeFromGroup (int groupID, Principal principal);

    List<Group> findGroupByName(String name);

    List<PublicationDTO> getAllPostInGroup(int groupID);

    Group updateGroup(Group group);

}
