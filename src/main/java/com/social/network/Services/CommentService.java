package com.social.network.Services;

import com.social.network.Dao.GenericDao;
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

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService implements ICommentService {

    private GenericDao<Comment> commentDao;

    private GenericDao<Publication> publicationDao;

    private Logger log;

    private CommentFacade commentFacade;

    @Autowired
    public CommentService(GenericDao<Comment> commentDao, GenericDao<Publication> publicationDao) {
        this.commentDao = commentDao;
        this.publicationDao = publicationDao;
        log = LogManager.getLogger(CommentService.class);
    }

    @Autowired
    public void setCommentFacade(CommentFacade commentFacade) {
        this.commentFacade = commentFacade;
    }

    @Override
    public Comment writeCommentPublication(CommentDTO commentDTO, int publicationID, Principal principal) {
        Comment comment = new Comment(commentDTO.getMessage());
        Publication publication = publicationDao.find(publicationID);
        User user = findByPrincipal(principal.getName());
        comment.setPublication(publication);
        comment.setUser(user);
        commentDao.add(comment);
        return comment;
    }

    @Override
    public List<CommentDTO> openComments(int publicationID) {
        Publication publication = publicationDao.find(publicationID);

        return publication.getComments().stream().map(commentFacade::commentDTO)
                .sorted(Comparator.comparing(CommentDTO::getSendTime))
                .collect(Collectors.toList());
    }

    private User findByPrincipal(String name) {
        User timeUser = null;
        try {
            EntityManager em = commentDao.getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> user = query.from(User.class);
            Predicate userPredicate = cb.equal(user.get("username"), name);
            query.select(user).where(userPredicate);
            timeUser = em.createQuery(query).setMaxResults(1).getSingleResult();
        } catch (Exception e) {
            log.error("Cannot find User with this name");
        }
        return timeUser;
    }
}
