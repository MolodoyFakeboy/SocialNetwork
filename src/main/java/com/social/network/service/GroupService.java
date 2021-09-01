package com.social.network.service;

import com.social.network.dao.GenericDao;
import com.social.network.dao.IUserDao;
import com.social.network.dto.PublicationDTO;
import com.social.network.exceptions.GroupNotFoundException;
import com.social.network.mapper.PublicationMapper;
import com.social.network.model.Group;
import com.social.network.model.User;
import com.social.network.service.interfac.IGroupService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Service
public class GroupService implements IGroupService {

    private GenericDao<Group> groupGenericDao;

    private IUserDao userDao;

    private final Logger log;

    private PublicationMapper publicationMapper = Mappers.getMapper(PublicationMapper.class);

    @Autowired
    public GroupService(GenericDao<Group> groupGenericDao, IUserDao userDao) {
        this.groupGenericDao = groupGenericDao;
        this.userDao = userDao;
        this.log = LogManager.getLogger(GroupService.class);
    }

    @Override
    public Group createNewGroup(Group group, Principal principal) {
        User user = userDao.findByName(principal.getName());
        user.getCommunities().add(group);
        group.setSubscribers(1);
        groupGenericDao.add(group);
        log.info("Группа успешна создана " + group.getName());
        return group;
    }

    @Override
    public Group findGroupByID(int groupID) {
        Group group = groupGenericDao.find(groupID);
        if (group != null) {
            return group;
        } else {
            throw new GroupNotFoundException("No Group with such " + groupID);
        }
    }

    @Override
    public List<Group> findall() {
        return groupGenericDao.findAll();
    }

    @Override
    public Group subcribeOnGroup(int groupID, Principal principal) {
        Group group = groupGenericDao.find(groupID);
        try {
            User user = userDao.findByName(principal.getName());
            user.getCommunities().add(group);
            group.setSubscribers(group.getUsers().size() + 1);
            groupGenericDao.update(group);
        } catch (Exception e) {
            log.error("Group error,you cant subscribe");
        }
        return group;
    }

    @Override
    public String unsubscribeFromGroup(int groupID, Principal principal) {
        Group group = groupGenericDao.find(groupID);
        User user = userDao.findByName(principal.getName());
        user.getCommunities().remove(group);
        group.setSubscribers(group.getUsers().size() - 1);
        groupGenericDao.update(group);
        return "Group removed from your list";
    }

    @Override
    public List<Group> findGroupByName(String name) {
        List<Group> groups = null;
        try {
            EntityManager em = groupGenericDao.getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Group> query = cb.createQuery(Group.class);
            Root<Group> groupRoot = query.from(Group.class);
            Predicate userPredicate = cb.equal(groupRoot.get("name"), name);
            query.select(groupRoot).where(userPredicate);
            groups = em.createQuery(query).getResultList();
        } catch (Exception e) {
            log.error("Cannot find Group with this name");
        }
        return groups;
    }

    @Override
    public List<PublicationDTO> getAllPostInGroup(int groupID) {
        Group group = findGroupByID(groupID);
        return group.getPublications().stream().map(publicationMapper::publicationToPublicationDto)
                .sorted(Comparator.comparing(PublicationDTO::getCreatedTime))
                .collect(Collectors.toList());
    }

    @Override
    public Group updateGroup(Group group) {
        return groupGenericDao.update(group);
    }

}
