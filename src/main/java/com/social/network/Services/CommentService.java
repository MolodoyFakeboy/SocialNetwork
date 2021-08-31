package com.social.network.Services;

import com.social.network.Dao.GenericDao;
import com.social.network.Dao.IUserDao;
import com.social.network.Dto.CommentDTO;
import com.social.network.Facade.CommentFacade;
import com.social.network.Model.Comment;
import com.social.network.Model.Publication;
import com.social.network.Model.User;
import com.social.network.Services.Interfaces.ICommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService implements ICommentService {

    private GenericDao<Comment> commentDao;

    private GenericDao<Publication> publicationDao;

    private IUserDao userDao;

    private Logger log;

    private CommentFacade commentFacade;

    @Autowired
    public CommentService(GenericDao<Comment> commentDao, GenericDao<Publication> publicationDao,IUserDao userDao) {
        this.commentDao = commentDao;
        this.publicationDao = publicationDao;
        this.userDao = userDao;
        log = LogManager.getLogger(CommentService.class);
    }

    @Autowired
    public void setCommentFacade(CommentFacade commentFacade) {
        this.commentFacade = commentFacade;
    }

    @Override
    public Comment writeCommentPublication(CommentDTO commentDTO, int publicationID, Principal principal) {
        Comment comment = new Comment(commentDTO.getMessage());
        try {
            Publication publication = publicationDao.find(publicationID);
            User user = userDao.findByName(principal.getName());
            comment.setPublication(publication);
            comment.setUser(user);
            commentDao.add(comment);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("Comment was added");
        return comment;
    }

    @Override
    public List<CommentDTO> openComments(int publicationID) {
        Publication publication = publicationDao.find(publicationID);
        if (publication != null) {
            return publication.getComments().stream().map(commentFacade::commentDTO)
                    .sorted(Comparator.comparing(CommentDTO::getSendTime))
                    .collect(Collectors.toList());
        } else {
            return null;
        }

    }

}
