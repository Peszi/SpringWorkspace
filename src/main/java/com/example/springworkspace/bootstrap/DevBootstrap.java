package com.example.springworkspace.bootstrap;

import com.example.springworkspace.data.UserData;
import com.example.springworkspace.model.Credentials;
import com.example.springworkspace.model.User;
import com.example.springworkspace.service.UserAuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(DevBootstrap.class);

    private final Credentials userCredentials = new Credentials("John", "1234");
    private final Credentials userCredentials2 = new Credentials("Bob", "1234");

    private UserAuthorizationService authorizationService;

    public DevBootstrap(UserAuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.initData();
    }

    private void initData() {

        logger.info("registering1...");
        this.authorizationService.registerUser(userCredentials);

        Optional<User> apiKey = this.authorizationService.authorizeUser(userCredentials);
        logger.info("login attempt1 OK...");
        logger.info(" - result:" + apiKey.get().getApiKey());

        logger.info("login attempt2 FAIL...");
        logger.info(" - result:" + this.authorizationService.authorizeUser(userCredentials2));

        logger.info("registering2...");
        this.authorizationService.registerUser(userCredentials2);

        logger.info("login attempt1 OK...");
        logger.info(" - result:" + this.authorizationService.authorizeUser(userCredentials));

        logger.info("login attempt2 OK...");
        logger.info(" - result:" + this.authorizationService.authorizeUser(userCredentials2));

        logger.info("removing1...");
        logger.info(" - result:" + this.authorizationService.deregisterUser(apiKey.get().getApiKey()));

        logger.info("login attempt1 FAIL...");
        logger.info(" - result:" + this.authorizationService.authorizeUser(userCredentials));

        logger.info("login attempt2 OK...");
        logger.info(" - result:" + this.authorizationService.authorizeUser(userCredentials2));

        logger.info("registering1...");
        this.authorizationService.registerUser(userCredentials);

        logger.info("login attempt1 OK...");
        logger.info(" - result:" + this.authorizationService.authorizeUser(userCredentials));

        logger.info("login attempt2 OK...");
        logger.info(" - result:" + this.authorizationService.authorizeUser(userCredentials2));
    }
}
