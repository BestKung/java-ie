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
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author BestKung
 */
public class ClientChat {
//192.168.10.210

    public static void main(String[] args) throws IOException {
        try {
            Socket socket = new Socket("localhost", 2000);
            DataOutputStream send = null;
            DataInputStream inputFile = null;
            BufferedReader received = null;
            Scanner scanner = null;
            String message = null;
            System.out.println("If You want to send file please input \'f\' \n==========================================================================");
            while (true) {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                send = new DataOutputStream(socket.getOutputStream());
                System.out.print("Input text : ");
                scanner = new Scanner(System.in);
                String txt = scanner.nextLine();
                send.writeBytes(txt + "\n");
                if (txt.equals("exit")) {
                    socket.close();
                }
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
                        break;
                    }
                    System.out.println("Success");
                    continue;
                }
                received = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                message = received.readLine();

                if (message.charAt(0) == 'f' && message.length() == 1) {
                    String fileName = received.readLine();
                    fileName = "F:\\" + findName(fileName);
                    System.out.println("File Upload From client : " + fileName);
                    File file = new File(fileName);

                    send = new DataOutputStream(new FileOutputStream(file));
                    DataOutputStream saveFile = new DataOutputStream(new FileOutputStream(file));
                    byte[] buffer = new byte[512];
                    int len = -1;

                    while ((len = dataInputStream.read(buffer)) != -1) {
                        saveFile.write(buffer, 0, len);
//                            break;
                    }
                    System.out.println("Success");
                    continue;
                }
                System.out.println("From Server : " + message);

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
