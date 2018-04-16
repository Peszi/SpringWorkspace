package com.example.springworkspace.bootstrap;

import com.example.springworkspace.command.Credentials;
import com.example.springworkspace.service.RoomService;
import com.example.springworkspace.service.UserAuthService;
import com.example.springworkspace.service.UserRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    public static final Credentials CREDENTIALS = new Credentials("Admin", "1234");
    public static final String IP = "local";

    private RoomService roomService;
    private UserRoomService userRoomService;
    private UserAuthService authService;

    public DevBootstrap(RoomService roomService, UserRoomService userRoomService, UserAuthService authService) {
        this.roomService = roomService;
        this.userRoomService = userRoomService;
        this.authService = authService;
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.initData();
    }

    private void initData() {

        this.authService.registerUser(IP, CREDENTIALS);
        final String api = this.authService.authorizeUser(IP, CREDENTIALS).getApiKey();
        log.info("API " + api);
        this.roomService.createRoom(IP, api);

        this.userRoomService.joinRoom(api, 1L);
        this.roomService.getAllRooms();

//        this.roomService.deleteRoom(IP, api);


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
//        logger.info(" - result:" + this.authorizationService.deleteUser(apiKey.get().getApiKey()));
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
