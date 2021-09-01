package com.social.network.service;

import com.social.network.dao.GenericDao;
import com.social.network.dao.IUserDao;
import com.social.network.dto.PostDTO;
import com.social.network.exceptions.NoPermission;
import com.social.network.exceptions.PostNotFoundException;
import com.social.network.mapper.PostMapper;
import com.social.network.model.Post;
import com.social.network.model.User;
import com.social.network.service.interfac.IPostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
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

    private IUserDao userDao;

    private PostMapper postMapper = Mappers.getMapper(PostMapper.class);

    @Autowired
    public PostService(GenericDao<Post> postGenericDao, IUserDao userDao) {
        this.postGenericDao = postGenericDao;
        this.userDao = userDao;
        log = LogManager.getLogger(PostService.class);
    }

    @Override
    public PostDTO createNewPostFromUser(PostDTO postFromRequest, Principal principal) {
        User timeUser = userDao.findByName(principal.getName());
        Post post = new Post(postFromRequest.getTitle());
        post.setUser(timeUser);
        postGenericDao.add(post);
        log.info("User " + timeUser.getUsername() + "create new post");
        PostDTO postDTO = postMapper.postToPostDto(post);
        return postDTO;
    }

    @Override
    public boolean deleatePost(int postID,Principal principal) {
        User user = userDao.findByName(principal.getName());
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
            return postMapper.postToPostDto(post);
        } else {
            throw new PostNotFoundException("Did not found post with such id: " + postID);
        }
    }

    @Override
    public List<PostDTO> openNews(Principal principal){
        User user = userDao.findByName(principal.getName());
        List<User> users = getFriendsToOpenNews(user);
        Set<Post> posts = new HashSet<>();
        for (User us : users) {
            posts.addAll(us.getPosts());
        }
        return posts.stream().map(postMapper::postToPostDto).
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

}
