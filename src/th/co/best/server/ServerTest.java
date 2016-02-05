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
        ServerSocket serverSocket = new ServerSocket(9091);
        Socket socket = null;

        try {
            while (true) {
                socket = serverSocket.accept();
                System.out.println("Client Accept.!");
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());


                String fileName = fromClient.readLine();

                System.out.println(fileName);
                System.out.println("Accept filenname \n");
                outputStream.writeBytes("Request Complete \n");
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                String sName[] = fileName.split("\\\\");
                fileName = sName[sName.length-1];
                fileName = "F:\\"+fileName;

                System.out.println("Before file"+fileName);
                File file = new File(fileName);
                DataOutputStream outputStream1 = new DataOutputStream(new FileOutputStream(file));
                
                byte[] buffer = new byte[4096];
                int len = -1;
                while ((len = dataInputStream.read(buffer)) != -1) {
                    outputStream1.write(buffer, 0, len);
                }
                System.out.println("Success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
