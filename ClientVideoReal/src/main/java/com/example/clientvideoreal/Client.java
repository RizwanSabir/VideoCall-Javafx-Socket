package com.example.clientvideoreal;


import com.github.sarxos.webcam.Webcam;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Client {

    Socket socket;

    Webcam webcam;


    private Button b1;

    Image ic;

    Hyperlink la;

    private ImageView imageHolder;
    DataOutputStream dataOutputStream;

    DataOutputStream dataOutputStream1;

    Label label;


    public Client(Socket socket, Button b1, ImageView view, Webcam webcam, Hyperlink label) throws IOException {
        try {
            this.socket = socket;
            this.imageHolder = view;
            this.webcam = webcam;


            this.b1 = b1;
            this.la = label;
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("f");

        }

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    label.setText("Video is displayed below.");
                    b1.setText("Connecting To Server");
                    callVideoCall();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }

    public void callVideoCall() throws IOException, InterruptedException {


        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    sendMessageServer();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 20);
    }


    public void sendMessageServer() throws IOException {
        byte[] bytes;

        try {
            if (socket.isClosed()) {
                System.out.println("closed connectionn");
                return;
            }

            BufferedImage bi = webcam.getImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            bytes = baos.toByteArray();
            ic = SwingFXUtils.toFXImage(bi, null);
            imageHolder.setImage(ic);

            dataOutputStream1 = new DataOutputStream(socket.getOutputStream());
            System.out.println("length is " + bytes.length);

            dataOutputStream1.writeInt(bytes.length);
            dataOutputStream1.flush();
            dataOutputStream1.write(bytes);
            dataOutputStream1.flush();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}









