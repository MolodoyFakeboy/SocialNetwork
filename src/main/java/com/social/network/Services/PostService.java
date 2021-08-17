package com.social.network.Services;

import com.social.network.Dao.GenericDao;
import com.social.network.Model.Post;
import com.social.network.Model.User;
import com.social.network.exceptions.PostNotFoundException;
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

@Service
@Transactional
public class PostService implements IPostService {

    private Logger log;

    private GenericDao<Post> postGenericDao;

    @Autowired
    public PostService(GenericDao<Post> postGenericDao) {
        this.postGenericDao = postGenericDao;
        log = LogManager.getLogger(PostService.class);
    }

    @Override
    public Post createNewPostFromUser(Post post, Principal principal) {
        User timeUser = findByPrincipal(principal.getName());
        timeUser.getPosts().add(post);
        postGenericDao.add(post);
        log.info("User " + timeUser.getUsername() + "create new post");
        return post;
    }

    @Override
    public boolean deleatePost(int postID) {
        Post post = postGenericDao.find(postID);
        if (post != null) {
            postGenericDao.delete(postID);
            return true;
        } else {
            throw new PostNotFoundException("Did not found post with such id: " + postID);
        }
    }

    @Override
    public Post findPostByID(int postID) {
        Post post = postGenericDao.find(postID);
        if (post != null) {
            return post;
        } else {
            throw new PostNotFoundException("Did not found post with such id: " + postID);
        }
    }

     private User findByPrincipal(String name) {
        User timeUser = null;
        try {
            EntityManager em = postGenericDao.getEntityManager();
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
