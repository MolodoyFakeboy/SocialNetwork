package com.social.network.Services;

import com.social.network.Model.Group;

public interface IGroupService {

    Group createNewGroup(Group group);

    Group findGroupByID(int groupID);
}
