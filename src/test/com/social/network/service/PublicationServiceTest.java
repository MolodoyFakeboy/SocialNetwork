package com.social.network.service;

import com.social.network.Config.TestConfig;
import com.social.network.dao.GroupDao;
import com.social.network.dao.PublicationDao;
import com.social.network.dto.PublicationDTO;
import com.social.network.model.Group;
import com.social.network.model.Publication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class PublicationServiceTest {

    @Mock
    private GroupDao groupDao;

    @Mock
    private PublicationDao publicationDao;

    @PersistenceContext
    private EntityManager entityManager;

    private PublicationService publicationService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.publicationService = new PublicationService(publicationDao, groupDao);
        Mockito.when(groupDao.getEntityManager()).thenReturn(entityManager);
    }


    @Test
    void createNewPublicationGroup() {
        PublicationDTO publication = new PublicationDTO();
        publication.setInfo("я покушал");
        Group group = new Group("Orel", "OrelNews");
        group.setIdGroup(1);
        Mockito.when(groupDao.find(group.getIdGroup())).thenReturn(group);
        Publication publication1 = publicationService.createNewPublicationGroup(publication, group.getIdGroup());
        Mockito.verify(publicationDao).add(publication1);
        Mockito.verify(publicationDao, Mockito.times(1)).add(publication1);
    }


    @Test
    void deleatePublication() {
        Publication publication = new Publication("я покушал");
        publication.setId(1);
        Mockito.when(publicationDao.find(publication.getId())).thenReturn(publication);
        publicationService.deleatePublication(publication.getId());
        Mockito.verify(publicationDao).delete(publication.getId());
    }

    @Test
    void findById() {
        Publication publication = new Publication("я покушал");
        publication.setId(1);
        Mockito.when(publicationDao.find(publication.getId())).thenReturn(publication);
        PublicationDTO publicationDTO = publicationService.findById(publication.getId());
        Mockito.verify(publicationDao).find(publication.getId());

        Assertions.assertEquals(publication.getInfo(), publicationDTO.getInfo());
    }
}