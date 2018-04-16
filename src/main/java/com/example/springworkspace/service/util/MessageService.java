package com.example.springworkspace.service.util;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Component
public class MessageService {

    private static final String DEFAULT_MESSAGE = "Message not supported!";

    private MessageSource messageSource;

    public MessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String code) {
        return this.messageSource.getMessage(code, null, MessageService.DEFAULT_MESSAGE, Locale.US);
    }
}
