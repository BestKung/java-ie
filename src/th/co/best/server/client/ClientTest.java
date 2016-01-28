/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.best.server.client;

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
public class ClientTest {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.1.120", 9090);
        String modifierSentence = null;
        while (true) {

            String fileName = "";
            BufferedReader bufferedReader = null;
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("Upload File.");

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner scanner = new Scanner(System.in);
//            System.out.print("Input Path File : ");
//            fileName = scanner.nextLine();
            fileName = "/Users/engineer/Desktop/test.txt";
            System.out.println(fileName);
            dataOutputStream.writeBytes(fileName + "\n");
            modifierSentence = bufferedReader.readLine();
            System.out.println("From Server" + modifierSentence);

            File file = new File(fileName);
            System.out.println(file);
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            System.out.println();
            byte[] buffer = new byte[512];
            int len = -1;
            while ((len = dataInputStream.read(buffer)) != -1) {
                dataOutputStream.write(buffer, 0, len);
            }
            System.out.println("Upload File Success...");
        }
    }
}
