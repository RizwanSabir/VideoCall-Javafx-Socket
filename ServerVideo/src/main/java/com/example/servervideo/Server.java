package com.example.servervideo;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    @FXML
    private ImageView imageHolder;
    private ServerSocket serverSocket;
    Socket socket;


    DataInputStream dataInputStream;


    public Server(ServerSocket serverSocket, ImageView imageHolder) {

        try {

            this.serverSocket = serverSocket;
            this.imageHolder = imageHolder;
            this.socket = serverSocket.accept();
            System.out.println("wait");
            System.out.println("Connect !");

        } catch (IOException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }

    }


    public void reciveMessageFromClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()) {


                    System.out.println("send pic");

                    System.out.println("waiting");

                    System.out.println("wait over");

                    System.out.println("1");
                    int length;
                    try {
                        dataInputStream = new DataInputStream(socket.getInputStream());
                        int a;
                        length = dataInputStream.readInt();
                        System.out.println("lenght is " + length);
                        byte[] bytes = new byte[length];
                        dataInputStream.readFully(bytes, 0, length);
                        Image image;

                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                        BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
                        image = SwingFXUtils.toFXImage(bufferedImage, null);

                        imageHolder.setImage(image);
                        System.out.println(image.getWidth());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }).start();
    }

}