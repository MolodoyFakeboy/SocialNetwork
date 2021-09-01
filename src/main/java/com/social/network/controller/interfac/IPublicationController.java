package com.social.network.controller.interfac;

import com.social.network.dto.PublicationDTO;
import com.social.network.payLoad.response.MessageResponse;
import org.springframework.http.ResponseEntity;

public interface IPublicationController {

    ResponseEntity<MessageResponse>createNewPublicationGroup(PublicationDTO publication, int groupID);

    ResponseEntity<MessageResponse> deleatePublication(int postID);

    ResponseEntity<PublicationDTO> findById(int id);

    ResponseEntity <MessageResponse> repost (int publicationID,int groupID);

}
