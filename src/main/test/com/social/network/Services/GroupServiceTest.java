package com.social.network.Services;

import com.social.network.Configs.Config;
import com.social.network.Model.Group;
import com.social.network.Model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.security.Principal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
class GroupServiceTest {

    private IGroupService groupService;

    @Autowired
    public GroupServiceTest(IGroupService groupService) {
        this.groupService = groupService;
    }


    @Test
    void findGroupByID() {
      groupService.findGroupByID(1);
    }

    @Test
    void createNewGroup() {
        Group group = new Group("Orelnews","All info about Orel");
        groupService.createNewGroup(group);
    }

    @Test
    void subcribeOnGroup() {

    }

    @Test
    void findGroupByName() {
        List<Group> groups = groupService.findGroupByName("Orel");
        groups.forEach(System.out::println);
    }
}