package com.social.network.Controller;

import com.social.network.Controller.Interfaces.IMessageController;
import com.social.network.Dto.MessageDTO;
import com.social.network.PayLoad.response.MessageResponse;
import com.social.network.Services.Interfaces.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MessageController implements IMessageController {

    private IMessageService messageService;

    @Autowired
    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    @PostMapping("message/{chatID}")
    public ResponseEntity<MessageResponse> writeMessageToUser(@RequestBody MessageDTO message, Principal principal, @PathVariable int chatID) {
        messageService.writeMessageToUser(message, principal, chatID);
        return new ResponseEntity<>(new MessageResponse("Сообщение отправлено"), HttpStatus.OK);
    }
}
