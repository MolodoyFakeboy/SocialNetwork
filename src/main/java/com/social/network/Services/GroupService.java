package com.social.network.Services;

import com.social.network.Dao.GenericDao;
import com.social.network.Model.Group;
import com.social.network.exceptions.GroupNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class GroupService implements IGroupService {

    private GenericDao<Group> groupGenericDao;

    private Logger log;

    @Autowired
    public GroupService(GenericDao<Group> groupGenericDao) {
        this.groupGenericDao = groupGenericDao;
        log = LogManager.getLogger(GroupService.class);
    }

    public Group createNewGroup(Group group) {
        groupGenericDao.add(group);
        return group;
    }

    public Group findGroupByID(int groupID) {
        Group group = groupGenericDao.find(groupID);
        if (group != null){
            return group;
        } else {
            throw new GroupNotFoundException("No Group with such " + groupID);
        }
    }


}
