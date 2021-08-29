package com.social.network.Services.Interfaces;

import com.social.network.Dto.MessageDTO;
import com.social.network.Model.Message;

import java.security.Principal;

public interface IMessageService {
    Message writeMessageToUser(MessageDTO messageFromRequest, Principal principal, int chatID);
}
