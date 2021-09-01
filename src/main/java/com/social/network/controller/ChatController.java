package com.social.network.controller;

import com.social.network.controller.interfac.IChatController;
import com.social.network.dto.MessageDTO;
import com.social.network.model.Chat;
import com.social.network.service.interfac.IChatSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class ChatController implements IChatController {

    private IChatSerivce chatSerivce;

    @Autowired
    public ChatController(IChatSerivce chatSerivce) {
        this.chatSerivce = chatSerivce;
    }

    @Override
    @PostMapping("chat/{userID}")
    public ResponseEntity<Chat> startNewChat(Principal principal, @PathVariable int userID) {
        Chat chat = chatSerivce.startNewChat(principal, userID);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @Override
    @GetMapping("chat/{chatId}")
    public ResponseEntity<List<MessageDTO>> getMessagesInChat(@PathVariable int chatId,Principal principal) {
        List<MessageDTO> messages = chatSerivce.getMessagesInChat(chatId,principal);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @Override
    @GetMapping("user/chats")
    public ResponseEntity<List<Chat>> getAllUserChats(Principal principal) {
        List<Chat> chats = chatSerivce.getAllUserChats(principal);
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }
}
