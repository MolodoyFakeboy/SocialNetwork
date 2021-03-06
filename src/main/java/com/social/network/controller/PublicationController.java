package com.social.network.controller;

import com.social.network.controller.interfac.IPublicationController;
import com.social.network.dto.PublicationDTO;
import com.social.network.model.Publication;
import com.social.network.payLoad.response.MessageResponse;
import com.social.network.service.interfac.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PublicationController implements IPublicationController {

    private IPublicationService publicationService;

    @Autowired
    public PublicationController(IPublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @Override
    @PostMapping("publication/{groupID}")
    public ResponseEntity<MessageResponse> createNewPublicationGroup(@RequestBody PublicationDTO publication, @PathVariable int groupID) {
        publicationService.createNewPublicationGroup(publication, groupID);
        return new ResponseEntity<>(new MessageResponse("Публикация успешно добавлена"), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("publication/{postID}")
    public ResponseEntity<MessageResponse> deleatePublication(@PathVariable int postID) {
        publicationService.deleatePublication(postID);
        return new ResponseEntity<>(new MessageResponse("Публикация успешно удалена"), HttpStatus.OK);
    }

    @Override
    @GetMapping("publication/{id}")
    public ResponseEntity<PublicationDTO> findById(@PathVariable int id) {
        PublicationDTO publicationDTO = publicationService.findById(id);
        return new ResponseEntity<>(publicationDTO, HttpStatus.OK);
    }

    @Override
    @PutMapping("publication/{publicationID}/{groupID}")
    public ResponseEntity<MessageResponse> repost(@PathVariable int publicationID, @PathVariable int groupID) {
        Publication publication = publicationService.repost(publicationID, groupID);
        return new ResponseEntity<>(new MessageResponse("Публикация успешно репостнута: " + publication.getId()), HttpStatus.OK);
    }
}
