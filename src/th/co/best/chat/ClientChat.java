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
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author BestKung
 */
public class ClientChat {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1999);
        DataOutputStream send = null;
        DataInputStream inputFile = null;
        BufferedReader received = null;
        Scanner scanner = null;
        String message = null;
        System.out.println("If You want to send file please input \'f\' \n==========================================================================");
        while (true) {
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
                byte[] buffer = new byte[512];
                int len = -1;
                while ((len = inputFile.read(buffer)) != -1) {
                    send.write(buffer, 0, len);
                }
                System.out.println("Success");
                continue;
            }
            received = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            message = received.readLine();
            System.out.println("From Server : " + message);
        }
    }
}
