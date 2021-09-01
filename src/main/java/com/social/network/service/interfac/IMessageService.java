package com.social.network.service.interfac;

import com.social.network.dto.MessageDTO;
import com.social.network.model.Message;

import java.security.Principal;

public interface IMessageService {
    Message writeMessageToUser(MessageDTO messageFromRequest, Principal principal, int chatID);
}
