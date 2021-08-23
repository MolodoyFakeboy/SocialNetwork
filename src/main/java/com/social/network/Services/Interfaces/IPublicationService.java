package com.social.network.Services.Interfaces;

import com.social.network.Dto.PublicationDTO;
import com.social.network.Model.Publication;

public interface IPublicationService {

    Publication createNewPublicationGroup(PublicationDTO publicationFromRequest, int groupID);

    boolean deleatePublication(int postID);

    PublicationDTO findById(int id);
}
