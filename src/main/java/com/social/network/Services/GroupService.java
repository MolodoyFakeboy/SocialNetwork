package com.social.network.Services;

import com.social.network.Dao.GenericDao;
import com.social.network.Dto.PostDTO;
import com.social.network.Dto.PublicationDTO;
import com.social.network.Facade.PostFacade;
import com.social.network.Facade.PublicationFacade;
import com.social.network.Model.Group;
import com.social.network.Model.Publication;
import com.social.network.Model.User;
import com.social.network.Services.Interfaces.IGroupService;
import com.social.network.exceptions.GroupNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Service
public class GroupService implements IGroupService {

    private GenericDao<Group> groupGenericDao;

    private final Logger log;

    private PublicationFacade publicationFacade;

    @Autowired
    public GroupService(GenericDao<Group> groupGenericDao) {
        this.groupGenericDao = groupGenericDao;
        log = LogManager.getLogger(GroupService.class);
    }

    @Autowired
    public void setPublicationFacade(PublicationFacade publicationFacade) {
        this.publicationFacade = publicationFacade;
    }

    @Override
    public Group createNewGroup(Group group,Principal principal) {
        User user = findByPrincipal(principal.getName());
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
        User user = findByPrincipal(principal.getName());
        user.getCommunities().add(group);
        group.setSubscribers(group.getUsers().size() + 1);
        groupGenericDao.update(group);
        return group;
    }

    @Override
    public String unsubscribeFromGroup (int groupID, Principal principal){
        Group group = groupGenericDao.find(groupID);
        User user = findByPrincipal(principal.getName());
        user.getCommunities().remove(group);
        group.setSubscribers(group.getUsers().size() - 1);
        groupGenericDao.update(group);
        return "Group removed from your list";
    }

    private User findByPrincipal(String name) {
        User timeUser = null;
        try {
            EntityManager em = groupGenericDao.getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> user = query.from(User.class);
            Predicate userPredicate = cb.equal(user.get("username"), name);
            query.select(user).where(userPredicate);
            timeUser = em.createQuery(query).setMaxResults(1).getSingleResult();
        } catch (Exception e) {
            log.error("Cannot find User with this name");
        }
        return timeUser;
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
        return group.getPublications().stream().map(publicationFacade::publicationToDto)
                .sorted(Comparator.comparing(PublicationDTO::getCreatedTime))
                .collect(Collectors.toList());
    }

    @Override
    public Group updateGroup(Group group){
        return groupGenericDao.update(group);
    }

}
