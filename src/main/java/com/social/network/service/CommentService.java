package com.social.network.service;

import com.social.network.dao.GenericDao;
import com.social.network.dao.IUserDao;
import com.social.network.dto.CommentDTO;
import com.social.network.mapper.CommentMapper;
import com.social.network.model.Comment;
import com.social.network.model.Publication;
import com.social.network.model.User;
import com.social.network.service.interfac.ICommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
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

    private CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);

    @Autowired
    public CommentService(GenericDao<Comment> commentDao, GenericDao<Publication> publicationDao,IUserDao userDao) {
        this.commentDao = commentDao;
        this.publicationDao = publicationDao;
        this.userDao = userDao;
        log = LogManager.getLogger(CommentService.class);
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
            return publication.getComments().stream().map(commentMapper::commentToCommentDto)
                    .sorted(Comparator.comparing(CommentDTO::getSendTime))
                    .collect(Collectors.toList());
        } else {
            return null;
        }

    }

}
