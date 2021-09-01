package com.social.network.service.interfac;

import com.social.network.dto.PublicationDTO;
import com.social.network.model.Publication;

public interface IPublicationService {

    Publication createNewPublicationGroup(PublicationDTO publicationFromRequest, int groupID);

    boolean deleatePublication(int postID);

    PublicationDTO findById(int id);

    Publication repost(int publicationID,int groupID);
}
