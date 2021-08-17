package com.social.network.Services;

import com.social.network.Dao.GenericDao;
import com.social.network.Model.Group;
import com.social.network.Model.User;
import com.social.network.exceptions.GroupNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.security.Principal;
import java.util.List;

@Transactional
@Service
public class GroupService implements IGroupService {

    private GenericDao<Group> groupGenericDao;

    private Logger log;

    @Autowired
    public GroupService(GenericDao<Group> groupGenericDao) {
        this.groupGenericDao = groupGenericDao;
        log = LogManager.getLogger(GroupService.class);
    }

    @Override
    public Group createNewGroup(Group group) {
        groupGenericDao.add(group);
        return group;
    }

    @Override
    public Group findGroupByID(int groupID) {
        Group group = groupGenericDao.find(groupID);
        if (group != null){
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
        groupGenericDao.update(group);
        return group;
    }

    @Override
    public boolean deleateGroup(int groupID){
       Group group = groupGenericDao.find(groupID);
       if(group != null){
           log.info( group.getName() + " was deleted ");
           groupGenericDao.delete(groupID);
           return true;
       } else {
          throw new GroupNotFoundException("no Group with such ID" + groupID);
       }
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
    public List<Group> findGroupByName(String name){
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
            log.error("Cannot find User with this name");
        }
        return groups;
    }
}
