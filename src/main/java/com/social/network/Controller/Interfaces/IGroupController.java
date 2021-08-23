package com.social.network.Controller.Interfaces;

import com.social.network.Dto.PublicationDTO;
import com.social.network.Model.Group;
import com.social.network.PayLoad.response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface IGroupController {

    ResponseEntity<Group> createNewGroup(Group group, Principal principal);

    ResponseEntity<Group> findGroupByID(int groupID);

    ResponseEntity<List<Group>> findall();

    ResponseEntity<Group> subcribeOnGroup(int groupID, Principal principal);

    ResponseEntity<List<Group>> findGroupByName(String name);

    ResponseEntity<List<PublicationDTO>>  getAllPostInGroup(int groupID);

    ResponseEntity<Group> updateGroup(Group group);

    ResponseEntity<MessageResponse> unsubscribeFromGroup (int groupID, Principal principal);
}
