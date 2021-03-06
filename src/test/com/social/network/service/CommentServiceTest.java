package com.social.network.service;

import com.social.network.config.TestConfig;
import com.social.network.dto.CommentDTO;
import com.social.network.model.Comment;
import com.social.network.service.interfac.ICommentService;
import com.social.network.testModel.TestPrincipal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class CommentServiceTest {

    private ICommentService commentService;

    @Autowired
    public CommentServiceTest(ICommentService commentService) {
        this.commentService = commentService;
    }

    @Test
    void writeCommentPublication() {
        TestPrincipal testPrincipal = new TestPrincipal();
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setMessage("Текст");
        Comment comment  = commentService.writeCommentPublication(commentDTO,1,testPrincipal);

        Assertions.assertNotNull(comment.getUser());
        Assertions.assertNotNull(comment.getPublication());
    }

    @Test
    void openComments() {
        List<CommentDTO>commentDTOS = commentService.openComments(1);

        Assertions.assertTrue(commentDTOS.size()>10);
    }
}