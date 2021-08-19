package com.social.network.Services;

import com.social.network.Config.TestConfig;
import com.social.network.Dto.MessageDTO;
import com.social.network.Model.Chat;
import com.social.network.Services.Interfaces.IChatSerivce;
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
      List<MessageDTO> messages = iChatSerivce.getMessagesInChat(1);
      Assertions.assertNotNull(messages);

      messages.forEach(System.out::println);
   }

   @Test
    void getAllChatFromUser() {
       List<Chat>chats = iChatSerivce.getAllUserChats(50);

       Assertions.assertNotNull(chats);
   }
}