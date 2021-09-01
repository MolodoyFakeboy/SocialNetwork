package com.social.network.controller.interfac;

import com.social.network.dto.MessageDTO;
import com.social.network.model.Chat;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface IChatController {

    ResponseEntity<Chat> startNewChat(Principal principal, int userID);

    ResponseEntity<List<MessageDTO>> getMessagesInChat(int chatId,Principal principal);

    ResponseEntity<List<Chat>> getAllUserChats(Principal principal);
}
