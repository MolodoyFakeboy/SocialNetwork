package com.social.network.service;

import com.social.network.Config.TestConfig;
import com.social.network.dto.MessageDTO;
import com.social.network.model.Chat;
import com.social.network.service.interfac.IChatSerivce;
import com.social.network.TestModel.TestPrincipal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class ChatServiceTest {

   private IChatSerivce iChatSerivce;

   @Autowired
   public ChatServiceTest(IChatSerivce iChatSerivce) {
        this.iChatSerivce = iChatSerivce;
    }

    @Test
    void startNewChat() {
        TestPrincipal testPrincipal = new TestPrincipal();
        Chat chat = iChatSerivce.startNewChat(testPrincipal,50);

        Assertions.assertNotNull(chat);
        Assertions.assertNotNull(chat.getUsers());
    }

    @Test
    void getMessagesInChat() {
        TestPrincipal testPrincipal = new TestPrincipal();
       List<MessageDTO> messages = iChatSerivce.getMessagesInChat(1,testPrincipal);
      Assertions.assertNotNull(messages);

      messages.forEach(System.out::println);
   }

   @Test
    void getAllChatFromUser() {
       TestPrincipal testPrincipal = new TestPrincipal();
       List<Chat>chats = iChatSerivce.getAllUserChats(testPrincipal);
       Assertions.assertNotNull(chats);

       chats.forEach(System.out::println);
   }
}