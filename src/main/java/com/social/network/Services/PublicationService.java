package com.social.network.Services;

import com.social.network.Dao.GenericDao;
import com.social.network.Model.Group;
import com.social.network.Model.Publication;
import com.social.network.exceptions.PostNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PublicationService implements IPublicationService {

    private Logger log;

    private GenericDao<Publication> publicationGenericDao;

    private GenericDao<Group> groupGenericDao;

    @Autowired
    public PublicationService(GenericDao<Publication> publicationGenericDao, GenericDao<Group> groupGenericDao) {
        this.publicationGenericDao = publicationGenericDao;
        this.groupGenericDao = groupGenericDao;
        this.log = LogManager.getLogger(PublicationService.class);
    }

    @Override
    public Publication createNewPublicationGroup(Publication publication,int groupID) {
        Group group = new Group();
        group.setIdGroup(groupID);
        publication.getListGroup().add(group);
        publicationGenericDao.add(publication);
        return publication;
    }

    @Override
    public boolean deleatePublication(int postID){
        Publication publication = publicationGenericDao.find(postID);
        if(publication != null){
           publication.getImages().clear();
           publicationGenericDao.delete(postID);
           log.info("Publication with id: " + postID + "was deleted");
           return true;
        } else {
            throw new PostNotFoundException("Publication with id: " + postID + "not exist");
        }
    }

}
