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
import java.io.FileInputStream;
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
        try {
            ServerSocket serverSocket = new ServerSocket(2000);
            Socket socket = null;
            DataOutputStream send = null;
            BufferedReader received = null;
            String message = null;
            Scanner scanner = null;
            DataInputStream inputFile = null;
            while (true) {
                socket = serverSocket.accept();
                if (socket.isConnected()) {
                    System.out.println("Client Accept!");
                }
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                System.out.println("If You want to send file please input \'f\' \n==========================================================================");
                received = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (true) {
                    send = new DataOutputStream(socket.getOutputStream());
                    message = received.readLine();

                    if (message != null || !"".equals(message)) {
                        if (message.charAt(0) == 'f' && message.length() == 1) {
                            String fileName = received.readLine();
                            System.out.println(fileName);
                            fileName = "F:\\" + findName(fileName);
                            System.out.println("File Upload From client : " + fileName);
                           
                            File file = new File(fileName);

                            send = new DataOutputStream(new FileOutputStream(file));
                            DataOutputStream saveFile = new DataOutputStream(new FileOutputStream(file));
                            byte[] buffer = new byte[7000];
                            int len = -1;

                            while ((len = dataInputStream.read(buffer)) != -1) {
                                saveFile.write(buffer, 0, len);
                                break;
                            }
                            System.out.println("Success");
                            continue;
                        }

                    }

                    System.out.println("From Client : " + message);
                    System.out.print("Input Message : ");
                    scanner = new Scanner(System.in);
                    String txt = scanner.nextLine();
                    send.writeBytes(txt + "\n");

                    if (txt.equals("f")) {
                        Scanner scanFile = new Scanner(System.in);
                        System.out.print("Upload file : ");
                        String fileName = scanFile.nextLine();
                        send.writeBytes(fileName + "\n");
                        File file = new File(fileName);
                        inputFile = new DataInputStream(new FileInputStream(file));
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = inputFile.read(buffer)) != -1) {
                            send.write(buffer, 0, len);
                        }
                        System.out.println("Success");
                        continue;
                    }

                }
            }
        } catch (Exception e) {
        }
    }

    private static String findName(String fileName) {

        String name = "";
        for (int i = 0; i < fileName.length(); i++) {
            char c = fileName.charAt(i);
            if (c == '\\') {
                name = "";
                continue;
            }
            name += c;
        }
        return name;
    }

}
