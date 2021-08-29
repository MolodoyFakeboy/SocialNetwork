package com.social.network.Services;

import com.social.network.Config.TestConfig;
import com.social.network.Dao.GroupDao;
import com.social.network.Dto.PublicationDTO;
import com.social.network.Model.Group;
import com.social.network.Model.Publication;
import com.social.network.Services.Interfaces.IGroupService;
import com.social.network.TestModel.TestPrincipal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class GroupServiceTest {

    @Mock
    private GroupDao groupDao;

    @PersistenceContext
    private EntityManager entityManager;

    private GroupService groupService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.groupService = new GroupService(groupDao);
        Mockito.when(groupDao.getEntityManager()).thenReturn(entityManager);
    }

    @Test
    void createNewGroup(@Autowired IGroupService groupService) {
        TestPrincipal testPrincipal = new TestPrincipal();
        Group group = new Group("Orel","Daily news");
        Group group1 = groupService.createNewGroup(group,testPrincipal);

        Assertions.assertNotNull(group1.getUsers());
        Assertions.assertEquals(1,group1.getSubscribers());
    }

    @Test
    void findGroupByID() {
        Group group = new Group("Orel","Daily news");
        group.setIdGroup(2);
        Mockito.when(groupDao.find(group.getIdGroup())).thenReturn(group);
        Group group1 = groupService.findGroupByID(group.getIdGroup());

        Assertions.assertEquals(group,group1);

    }

    @Test
    void findall() {
        Group group = new Group("Orel","Daily news");
        List<Group> groupList = Collections.singletonList(group);
        Mockito.when(groupDao.findAll()).thenReturn(groupList);
        List<Group>groups = groupService.findall();

        Assertions.assertEquals(groups,groupList);

    }

    @Test
    void subcribeOnGroup(@Autowired IGroupService groupService) {
        TestPrincipal testPrincipal = new TestPrincipal();
        int groupID = 1; // cмотреть в бд
        Group group = groupService.subcribeOnGroup(groupID,testPrincipal);
        int numberSubcriber = 2;

        Assertions.assertEquals(group.getSubscribers(),numberSubcriber);
    }


    @Test
    void findGroupByName() {
        Group group = new Group("Orel","Daily news");
        List<Group> testGroup = groupService.findGroupByName(group.getName());

        Assertions.assertEquals(group.getName(),testGroup.get(0).getName());
    }

    @Test
    void getAllPostInGroup(@Autowired IGroupService groupService) {
       List<PublicationDTO> publications = groupService.getAllPostInGroup(1);
       Assertions.assertNotNull(publications);
       publications.forEach(System.out::println);
    }

    @Test
    void updateGroup() {
        Group group = new Group("Orel","Daily news");
        Mockito.when(groupDao.update(group)).thenReturn(group);
        groupService.updateGroup(group);
        Mockito.verify(groupDao).update(group);
        Mockito.verify(groupDao, Mockito.times(1)).update(group);
    }
}