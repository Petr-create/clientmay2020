package ru.itsjava.service;

import lombok.SneakyThrows;

import java.io.PrintWriter;
import java.net.Socket;

public class ClientService {
    public final static String HOST= "localhost";
    public final static int PORT= 8081;
    private final MessageInputService consoleInputService = new MessageInputService(System.in);

    @SneakyThrows
    public void start(){
        Socket socket = new Socket(HOST, PORT);
        if(socket.isConnected()){
            System.out.println("Client connected");
            new Thread(new SocketRannable(socket)).start();
            PrintWriter writerForServer = new PrintWriter(socket.getOutputStream());

            System.out.println("Привет дорогой!!" +
                    "\nВведи свой логин:");
            String login = consoleInputService.readMessage();
            System.out.println("Введи свой пароль:");
            String password = consoleInputService.readMessage();
            //writerForServer.println("!@#$autho" + login + " : " + password);
            writerForServer.println("!@#$reg" + login + " : " + password);
            writerForServer.flush();

            String consoleInput;
            while ((consoleInput = consoleInputService.readMessage()) != null){
                writerForServer.println(consoleInput);
                writerForServer.flush();

            }
        }
    }
}
