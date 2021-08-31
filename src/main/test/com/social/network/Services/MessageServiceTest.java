package com.social.network.Services;

import com.social.network.Config.TestConfig;
import com.social.network.Dao.ChatDao;
import com.social.network.Dao.MessageDao;
import com.social.network.Dao.UserDao;
import com.social.network.Dto.MessageDTO;
import com.social.network.Model.Chat;
import com.social.network.Model.Message;
import com.social.network.Model.User;
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