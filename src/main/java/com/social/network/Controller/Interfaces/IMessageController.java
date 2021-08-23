package com.social.network.Controller.Interfaces;

import com.social.network.Dto.MessageDTO;
import com.social.network.PayLoad.response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface IMessageController {

    ResponseEntity<MessageResponse> writeMessageToUser(MessageDTO message, Principal principal, int chatID);
}
