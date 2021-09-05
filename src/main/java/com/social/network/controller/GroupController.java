package com.social.network.controller;

import com.social.network.controller.interfac.IGroupController;
import com.social.network.dto.PublicationDTO;
import com.social.network.model.Group;
import com.social.network.payLoad.response.MessageResponse;
import com.social.network.service.interfac.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class GroupController implements IGroupController {

    private IGroupService groupService;

    @Autowired
    public GroupController(IGroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    @PostMapping("group")
    public ResponseEntity<Group> createNewGroup(@RequestBody Group group, Principal principal) {
        Group group1 = groupService.createNewGroup(group, principal);
        return new ResponseEntity<>(group1, HttpStatus.OK);
    }

    @Override
    @GetMapping("group/{groupID}")
    public ResponseEntity<Group> findGroupByID(@PathVariable int groupID) {
        Group group = groupService.findGroupByID(groupID);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @Override
    @GetMapping("groups")
    public ResponseEntity<List<Group>> findall() {
        List<Group> groups = groupService.findall();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @Override
    @PutMapping("group/{groupID}")
    public ResponseEntity<Group> subcribeOnGroup(@PathVariable int groupID, Principal principal) {
        Group group = groupService.subcribeOnGroup(groupID, principal);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @Override
    @GetMapping("groups/{name}")
    public ResponseEntity<List<Group>> findGroupByName(@PathVariable String name) {
        List<Group> groups = groupService.findGroupByName(name);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @Override
    @GetMapping("groupPost/{groupID}")
    public ResponseEntity<List<PublicationDTO>> getAllPostInGroup(@PathVariable int groupID) {
        List<PublicationDTO> publications = groupService.getAllPostInGroup(groupID);
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }

    @Override
    @PutMapping("group")
    public ResponseEntity<Group> updateGroup(@RequestBody Group group) {
        Group group1 = groupService.updateGroup(group);
        return new ResponseEntity<>(group1, HttpStatus.OK);
    }

    @Override
    @PutMapping("groupUnSub/{groupID}")
    public ResponseEntity<MessageResponse> unsubscribeFromGroup(@PathVariable int groupID, Principal principal) {
        String answer = groupService.unsubscribeFromGroup(groupID, principal);
        return new ResponseEntity<>(new MessageResponse(answer), HttpStatus.OK);
    }
}
