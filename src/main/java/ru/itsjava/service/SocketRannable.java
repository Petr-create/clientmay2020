package ru.itsjava.service;

import lombok.SneakyThrows;
import java.net.Socket;


public class SocketRannable implements Runnable {

    private final MessageInputService messageInputService;
    private final Socket socket;

    @SneakyThrows
    public SocketRannable(Socket socket) {
        this.socket = socket;
        this.messageInputService = new MessageInputService(socket.getInputStream());
    }

    @SneakyThrows
    @Override
    public void run() {

        String messageFromServer;
        while((messageFromServer = messageInputService.readMessage()) != null){
            if(messageFromServer.equals("401")){
                socket.close();
                System.exit(-1);
            }
            System.out.println(messageFromServer);
        }
    }
}
