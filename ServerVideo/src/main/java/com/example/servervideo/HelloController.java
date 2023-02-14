package com.example.servervideo;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ResourceBundle;

public class HelloController  implements Initializable {
    ServerSocket serverSocket;

    @FXML
    Button b1;

    @FXML
    private ImageView imageHolder;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            serverSocket = new ServerSocket(1234);
                            Server server = new Server(serverSocket,imageHolder);
                            b1.setText("Waiting...");
                            server.reciveMessageFromClient();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }).start();

            }
        });


    }


}
