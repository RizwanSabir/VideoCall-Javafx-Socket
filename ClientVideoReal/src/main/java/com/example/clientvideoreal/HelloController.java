package com.example.clientvideoreal;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ResourceBundle;

public class HelloController  implements Initializable {
    @FXML
    private Button b1;

    @FXML
    private ImageView imageHolder;

    Webcam webcam;

    @FXML
    private Hyperlink la;

    Socket socket;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webcam=Webcam.getDefault();
        webcam.setViewSize(new Dimension(640,480));
        webcam.open();

        try {
          socket=new Socket("localhost",1234);
          Client client=new Client(socket,b1,imageHolder,webcam,la);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }




}