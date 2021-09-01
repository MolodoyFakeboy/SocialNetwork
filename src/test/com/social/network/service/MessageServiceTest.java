package com.social.network.service;

import com.social.network.Config.TestConfig;
import com.social.network.dao.ChatDao;
import com.social.network.dao.MessageDao;
import com.social.network.dao.UserDao;
import com.social.network.dto.MessageDTO;
import com.social.network.model.Chat;
import com.social.network.model.Message;
import com.social.network.model.User;
import com.social.network.TestModel.TestPrincipal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class MessageServiceTest {

    @Mock
    private MessageDao messageDao;

    @Mock
    private ChatDao chatDao;

    private MessageService messageService;

    @Mock
    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.messageService = new MessageService(messageDao, chatDao,userDao);
    }

    @Test
    void writeMessageToUser() {
        TestPrincipal testPrincipal = new TestPrincipal();
        MessageDTO message = new MessageDTO();
        message.setSendText("Привет я все еще пишу тесты *(!");
        Chat chat = new Chat("какой-то чат");
        chat.setIdChat(1);
        User user = new User();
        user.setId(1);
        Mockito.when(chatDao.find(chat.getIdChat())).thenReturn(chat);
        Mockito.when(userDao.findByName(testPrincipal.getName())).thenReturn(user);
        Message message1 = messageService.writeMessageToUser(message, testPrincipal, 53);
        Mockito.verify(messageDao).add(message1);
        Mockito.verify(messageDao, Mockito.times(1)).add(message1);

        Assertions.assertEquals(message.getSendText(), message1.getSendText());
    }
}