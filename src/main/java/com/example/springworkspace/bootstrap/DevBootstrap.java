package com.example.springworkspace.bootstrap;

import com.example.springworkspace.command.Credentials;
import com.example.springworkspace.data.UserDTO;
import com.example.springworkspace.model.Room;
import com.example.springworkspace.model.User;
import com.example.springworkspace.repository.RoomRepository;
import com.example.springworkspace.repository.UserRepository;
import com.example.springworkspace.service.UserAuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(DevBootstrap.class);

    private final Credentials userCredentials = new Credentials("John", "1234");
    private final Credentials userCredentials2 = new Credentials("Bob", "1234");

    private UserAuthorizationService authorizationService;

    private UserRepository userRepository;
    private RoomRepository roomRepository;

    public DevBootstrap(UserAuthorizationService authorizationService, UserRepository userRepository, RoomRepository roomRepository) {
        this.authorizationService = authorizationService;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.initData();
    }

    private void initData() {

        Room room = new Room();
        room.setId(1L);
        this.roomRepository.saveAndFlush(room);
        this.userRepository.saveAndFlush(new User("1", "0"));
        this.userRepository.saveAndFlush(new User("2", "0"));
        this.userRepository.saveAndFlush(new User("3", "0"));

        room.addUser(this.userRepository.findById(1L).get());
        this.roomRepository.flush(); this.userRepository.flush();




//        logger.info("registering1...");
//        this.authorizationService.registerUser(userCredentials);
//
//        Optional<UserDTO> apiKey = this.authorizationService.authorizeUser(userCredentials);
//        logger.info("login attempt1 OK...");
//        logger.info(" - result:" + apiKey.get().getApiKey());
//
//        logger.info("login attempt2 FAIL...");
//        logger.info(" - result:" + this.authorizationService.authorizeUser(userCredentials2));
//
//        logger.info("registering2...");
//        this.authorizationService.registerUser(userCredentials2);
//
//        logger.info("login attempt1 OK...");
//        logger.info(" - result:" + this.authorizationService.authorizeUser(userCredentials));
//
//        logger.info("login attempt2 OK...");
//        logger.info(" - result:" + this.authorizationService.authorizeUser(userCredentials2));
//
//        logger.info("removing1...");
//        logger.info(" - result:" + this.authorizationService.deregisterUser(apiKey.get().getApiKey()));
//
//        logger.info("login attempt1 FAIL...");
//        logger.info(" - result:" + this.authorizationService.authorizeUser(userCredentials));
//
//        logger.info("login attempt2 OK...");
//        logger.info(" - result:" + this.authorizationService.authorizeUser(userCredentials2));
//
//        logger.info("registering1...");
//        this.authorizationService.registerUser(userCredentials);
//
//        logger.info("login attempt1 OK...");
//        logger.info(" - result:" + this.authorizationService.authorizeUser(userCredentials));
//
//        logger.info("login attempt2 OK...");
//        logger.info(" - result:" + this.authorizationService.authorizeUser(userCredentials2));
    }
}
