package com.example.springworkspace.service.util;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

@Service
public class UdpService {

    public static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UdpService.class);

    private byte[] buf = new byte[256];

    DatagramSocket socket;
    DatagramPacket packet;

//    @Autowired
//    private MessageChannel sender;

    private Message message;

    public void init() {
        try {
            this.socket = new DatagramSocket(4445);
            logger.info("Server started!");
        } catch (SocketException e) {
            e.printStackTrace();
        }
        this.packet = new DatagramPacket(buf, buf.length);
    }

    public void receiveData(Message message) {
//        this.message = message;

//        for (String key : message.getHeaders().keySet())
//            logger.info(key + ":" + message.getHeaders().get(key));
        String data = new String((byte[]) message.getPayload());
        logger.info(" ? " + data);
        Message msg = MessageBuilder.withPayload("Data".getBytes()).copyHeaders(message.getHeaders()).build();

//        Message message2 = MessageBuilder.fromMessage(message).setHeader("1234", IpHeaders.PORT).build();
//
//        sender.send(message2);
        this.sendReply(msg);

    }

    private void sendReply(Message message) {
        try {
            int port = (Integer) message.getHeaders().get("ip_port");
            String ip = (String) message.getHeaders().get("ip_address");
            InetAddress IPAddress = InetAddress.getByName(ip);

            byte[] sentence = (byte[]) message.getPayload();
            byte[] sendData = new byte[sentence.length];
            sendData = sentence;
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            DatagramSocket clientSocket = new DatagramSocket();
            clientSocket.send(sendPacket);
            clientSocket.close();
        } catch (Exception ex) {
            throw new RuntimeException("KO");
        }
    }

    @Scheduled(fixedRate = 2000)
    public void updateData() {
//        logger.info("update!");
//        try {
//            socket.receive(packet);
//            logger.info(">" + new String(packet.getData()));
//            socket.send(packet);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @PreDestroy
    public void destroy() {
//        socket.close();
    }

}
