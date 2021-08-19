package com.social.network.Services.Interfaces;

import com.social.network.Model.Message;

import java.security.Principal;

public interface IMessageService {

    Message writeMessageToUser(Message message, Principal principal, int chatID);

}
