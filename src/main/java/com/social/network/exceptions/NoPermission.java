package com.social.network.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoPermission extends RuntimeException {
    public NoPermission(String message) {
        super(message);
    }
}
