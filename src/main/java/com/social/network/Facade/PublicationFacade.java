package com.social.network.Facade;

import com.social.network.Dto.PublicationDTO;
import com.social.network.Model.Publication;
import org.springframework.stereotype.Component;

@Component
public class PublicationFacade {

    public PublicationDTO publicationToDto(Publication publication){
        PublicationDTO publicationDTO =  new PublicationDTO();
        publicationDTO.setCreatedTime(publication.getCreatedTime());
        publicationDTO.setInfo(publication.getInfo());
        return publicationDTO;
    }
}
