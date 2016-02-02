/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.best.chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author BestKung
 */
public class ServerChat {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1999);
        Socket socket = null;
        DataOutputStream send = null;
        BufferedReader received = null;
        String message = null;
        Scanner scanner = null;

        while (true) {
            socket = serverSocket.accept();
            if (socket.isConnected()) {
                System.out.println("Client Accept!");
            }
            System.out.println("If You want to send file please input \'f\' \n==========================================================================");
            received = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                send = new DataOutputStream(socket.getOutputStream());
                message = received.readLine();

                if (message.charAt(0) == 'f' && message.length() == 1) {
                    String fileName = received.readLine();
                    fileName = "F:\\" + fileName.substring(3);
                    System.out.println("File Upload From client : " + fileName);
                    File file = new File(fileName);
                    send = new DataOutputStream(new FileOutputStream(file));
                    DataOutputStream saveFile = new DataOutputStream(new FileOutputStream(file));
                    System.out.println("Success");
                    continue;

                }
                System.out.println("From Client : " + message);
                System.out.print("Input Message : ");
                scanner = new Scanner(System.in);
                String txt = scanner.nextLine();
                send.writeBytes(txt + "\n");
            }
        }
    }
}
