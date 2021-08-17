package com.social.network.Services;

import com.social.network.Model.Publication;

public interface IPublicationService {

    Publication createNewPublicationGroup(Publication publication, int groupID);

    boolean deleatePublication(int postID);
}
