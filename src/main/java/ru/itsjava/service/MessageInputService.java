package ru.itsjava.service;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MessageInputService {
    private final BufferedReader reader;

    public MessageInputService(InputStream inputStream){
        reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @SneakyThrows
    public String readMessage(){
        return reader.readLine();
    }

}
