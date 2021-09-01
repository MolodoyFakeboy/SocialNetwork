package com.social.network.controller.interfac;

import com.social.network.dto.MessageDTO;
import com.social.network.payLoad.response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface IMessageController {

    ResponseEntity<MessageResponse> writeMessageToUser(MessageDTO message, Principal principal, int chatID);
}
