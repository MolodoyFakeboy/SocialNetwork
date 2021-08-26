package com.social.network.Services;

import com.social.network.Dao.GenericDao;
import com.social.network.Dto.PublicationDTO;
import com.social.network.Facade.PublicationFacade;
import com.social.network.Model.Group;
import com.social.network.Model.Publication;
import com.social.network.Services.Interfaces.IPublicationService;
import com.social.network.exceptions.PostNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PublicationService implements IPublicationService {

    private Logger log;

    private GenericDao<Publication> publicationGenericDao;

    private GenericDao<Group> groupGenericDao;

    private PublicationFacade publicationFacade;

    public PublicationService(GenericDao<Publication> publicationGenericDao, GenericDao<Group> groupGenericDao) {
        this.publicationGenericDao = publicationGenericDao;
        this.groupGenericDao = groupGenericDao;
        this.log = LogManager.getLogger(PublicationService.class);
    }

    @Autowired
    public void setPublicationFacade(PublicationFacade publicationFacade) {
        this.publicationFacade = publicationFacade;
    }

    @Override
    public Publication createNewPublicationGroup(PublicationDTO publicationFromRequest, int groupID) {
        Group group = groupGenericDao.find(groupID);
        Publication publication = new Publication(publicationFromRequest.getInfo());
        publication.getListGroup().add(group);
        publicationGenericDao.add(publication);
        return publication;
    }

    @Override
    public boolean deleatePublication(int postID) {
        Publication publication = publicationGenericDao.find(postID);
        if (publication != null) {
            publication.getImages().clear();
            publicationGenericDao.delete(postID);
            log.info("Publication with id: " + postID + "was deleted");
            return true;
        } else {
            throw new PostNotFoundException("Publication with id: " + postID + "not exist");
        }
    }

    @Override
    public PublicationDTO findById(int id) {
        Publication publication = publicationGenericDao.find(id);
        if (publication != null) {
            return publicationFacade.publicationToDto(publication);
        } else {
            throw new PostNotFoundException("Publication with id: " + id + "not exist");
        }

    }

    @Override
    public Publication repost(int publicationID,int groupID){
        Publication publication = publicationGenericDao.find(publicationID);
        Group group = groupGenericDao.find(groupID);
        publication.getListGroup().add(group);
        publicationGenericDao.update(publication);
        return publication;
    }

}
