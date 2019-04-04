package com.noahfranck;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Host {
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private String message;
    private Socket client;
    public static void main(String[] args) {
        Host c = new Host();
        c.runclient();
    }

    private void runclient() {
        try {
            client = new Socket("127.0.0.1", 12345);
            output = new ObjectOutputStream(client.getOutputStream());
            input = new ObjectInputStream(client.getInputStream());
            message = (String) input.readObject();
            System.out.println("Recieved: " + message);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}