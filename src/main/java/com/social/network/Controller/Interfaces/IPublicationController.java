package com.social.network.Controller.Interfaces;

import com.social.network.Dto.PublicationDTO;
import com.social.network.Model.Publication;
import com.social.network.PayLoad.response.MessageResponse;
import org.springframework.http.ResponseEntity;

public interface IPublicationController {

    ResponseEntity<MessageResponse>createNewPublicationGroup(PublicationDTO publication, int groupID);

    ResponseEntity<MessageResponse> deleatePublication(int postID);

    ResponseEntity<PublicationDTO> findById(int id);

}
