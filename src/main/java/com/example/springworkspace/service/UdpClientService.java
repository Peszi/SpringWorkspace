package com.example.springworkspace.service;

import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.*;

public class UdpClientService {

    private byte[] buf = new byte[256];

    DatagramSocket socket;
    DatagramPacket packet;

    @PostConstruct
    public void init() {

        final byte[] PACKET_DATA = "PingPong".getBytes();
        for (int i = 0; i < PACKET_DATA.length; i++)
            this.buf[i] = PACKET_DATA[i];

        try {
            this.socket = new DatagramSocket();
            this.packet = new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), 4445);
            UdpService.logger.info("Client started!");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 1000)
    public void updateData() {
        UdpService.logger.info("client update");
        try {
            UdpService.logger.info("Client sending...");
            socket.send(packet);
            socket.receive(packet);
            UdpService.logger.info("Client recived...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        socket.close();
    }

}
