package com.social.network.service.interfac;

import com.social.network.dto.MessageDTO;
import com.social.network.model.Chat;

import java.security.Principal;
import java.util.List;

public interface IChatSerivce {

    Chat startNewChat(Principal principal, int userID);

    List<MessageDTO> getMessagesInChat(int chatId,Principal principal);

    List<Chat>getAllUserChats(Principal principal);
}
