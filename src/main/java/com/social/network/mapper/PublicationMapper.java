package com.social.network.mapper;

import com.social.network.dto.PublicationDTO;
import com.social.network.model.Publication;
import org.mapstruct.Mapper;

@Mapper
public interface PublicationMapper {
    PublicationDTO publicationToPublicationDto(Publication publication);
}
