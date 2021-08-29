package com.social.network.Facade;

import com.social.network.Dto.MessageDTO;
import com.social.network.Model.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageFacade {

    public MessageDTO messageToDto(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setSendText(message.getSendText());
        messageDTO.setSendTime(message.getSendTime());
        messageDTO.setUsername(message.getUser().getUsername());
        return messageDTO;
    }
}
