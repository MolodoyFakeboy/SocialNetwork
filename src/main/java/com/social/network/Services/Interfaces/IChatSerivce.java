package com.social.network.Services.Interfaces;

import com.social.network.Dto.MessageDTO;
import com.social.network.Model.Chat;
import com.social.network.Model.Message;

import java.security.Principal;
import java.util.List;

public interface IChatSerivce {

    Chat startNewChat(Principal principal, int userID);

    List<MessageDTO> getMessagesInChat(int chatId,Principal principal);

    List<Chat>getAllUserChats(Principal principal);
}
