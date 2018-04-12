package com.example.springworkspace.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageService {

    private static final String DEFAULT_MESSAGE = "Message not supported!";

    private MessageSource messageSource;

    public MessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String code, Locale locale) {
        return this.messageSource.getMessage(code, null, MessageService.DEFAULT_MESSAGE, locale);
    }
}
