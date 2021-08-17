package com.social.network.Services;

import com.social.network.Configs.Config;
import com.social.network.Model.Publication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
class PublicationServiceTest {

    private IPublicationService publicationService;

    @Autowired
    public PublicationServiceTest(IPublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @Test
    void createNewPublicationGroup() {
        Publication publication = new Publication("я покушал");
        publicationService.createNewPublicationGroup(publication,1);

    }


}