/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.best.server;

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
public class ServerTest {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3333);
        Socket socket = null;
        DataOutputStream outputStream = null;
        DataInputStream dataInputStream = null;
        DataOutputStream outputStream1 = null;
        BufferedReader fromClient = null;
        socket = serverSocket.accept();
        System.out.println("Client Accept.!");
        fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outputStream = new DataOutputStream(socket.getOutputStream());
        try {
            while (true) {
                System.out.println("Wait...");
                String fileName = fromClient.readLine();
                System.out.println(fileName);
                System.out.println("Accept filenname \n");
                outputStream.writeBytes("Request Complete \n");
                dataInputStream = new DataInputStream(socket.getInputStream());
                fileName = "F:\\" + fileName.substring(3);
                File file = new File(fileName);
                outputStream1 = new DataOutputStream(new FileOutputStream(file));

                byte[] buffer = new byte[1024];
                int len = -1;
                while ((len = dataInputStream.read(buffer)) != 1) {
                    outputStream1.write(buffer, 0, len);
                    if (len < 1024) {
                        break;
                    }
                }
                System.out.println("Save Success");
                continue;
            }

        } catch (Exception e) {
        } finally {
            socket.close();
            outputStream.close();
            dataInputStream.close();
            outputStream1.close();
            fromClient.close();
        }
    }
}
