package com.social.network.Services.Interfaces;

import com.social.network.Dto.PublicationDTO;
import com.social.network.Model.Group;

import java.security.Principal;
import java.util.List;

public interface IGroupService {

    Group createNewGroup(Group group,Principal principal);

    Group findGroupByID(int groupID);

    List<Group> findall();

    Group subcribeOnGroup(int groupID, Principal principal);


    List<Group> findGroupByName(String name);

    List<PublicationDTO> getAllPostInGroup(int groupID);

    Group updateGroup(Group group);

}
