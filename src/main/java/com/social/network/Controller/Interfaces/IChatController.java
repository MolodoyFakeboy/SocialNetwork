package com.social.network.Controller.Interfaces;

import com.social.network.Dto.MessageDTO;
import com.social.network.Model.Chat;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface IChatController {

    ResponseEntity<Chat> startNewChat(Principal principal, int userID);

    ResponseEntity<List<MessageDTO>> getMessagesInChat(int chatId,Principal principal);

    ResponseEntity<List<Chat>> getAllUserChats(Principal principal);
}
