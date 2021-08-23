package com.social.network.Services;

import com.social.network.Dao.GenericDao;
import com.social.network.Dto.PostDTO;
import com.social.network.Facade.PostFacade;
import com.social.network.Model.Post;
import com.social.network.Model.User;
import com.social.network.Services.Interfaces.IPostService;
import com.social.network.exceptions.NoPermission;
import com.social.network.exceptions.PostNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.security.Principal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService implements IPostService {

    private Logger log;

    private GenericDao<Post> postGenericDao;

    private PostFacade postFacade;

    @Autowired
    public PostService(GenericDao<Post> postGenericDao) {
        this.postGenericDao = postGenericDao;
        log = LogManager.getLogger(PostService.class);
    }

    @Autowired
    public void setPostFacade(PostFacade postFacade) {
        this.postFacade = postFacade;
    }

    @Override
    public PostDTO createNewPostFromUser(PostDTO postFromRequest, Principal principal) {
        User timeUser = findByPrincipal(principal.getName());
        Post post = new Post(postFromRequest.getTitle());
        post.setUser(timeUser);
        postGenericDao.add(post);
        log.info("User " + timeUser.getUsername() + "create new post");
        PostDTO postDTO = postFacade.postToPostDTO(post);
        return postDTO;
    }

    @Override
    public boolean deleatePost(int postID,Principal principal) {
        User user = findByPrincipal(principal.getName());
        Post post = postGenericDao.find(postID);
        if (post != null) {
            if(post.getUser().equals(user)){
                postGenericDao.delete(postID);
                return true;
            } else {
                throw new NoPermission("You cant do it");
            }
        } else {
            throw new PostNotFoundException("Did not found post with such id: " + postID);
        }
    }

    @Override
    public PostDTO findPostByID(int postID) {
        Post post = postGenericDao.find(postID);
        if (post != null) {
            return postFacade.postToPostDTO(post);
        } else {
            throw new PostNotFoundException("Did not found post with such id: " + postID);
        }
    }

    @Override
    public List<PostDTO> openNews(Principal principal){
        User user = findByPrincipal(principal.getName());
        List<User> users = getFriendsToOpenNews(user);
        Set<Post> posts = new HashSet<>();
        for (User us : users) {
            posts.addAll(us.getPosts());
        }
        return posts.stream().map(postFacade::postToPostDTO).
                sorted(Comparator.comparing(PostDTO::getCreatedDate)).collect(Collectors.toList());
    }

    private List<User> getFriendsToOpenNews(User userTest){
        EntityManager em = postGenericDao.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);
        Join<User, User> friendsJoin = user.join("friends");
        Predicate userPredicate = cb.equal(friendsJoin.get("id"), userTest.getId());
        query.select(user).where(userPredicate);
        List<User> users = em.createQuery(query).getResultList();
        users.removeIf(us -> !userTest.getFriends().contains(us));
        return users;
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
