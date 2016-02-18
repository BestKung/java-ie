/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.best.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author BestKung
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5555);
        DataOutputStream dataOutputStream = null;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                System.out.print("Input text : ");
                scanner = new Scanner(System.in);
                String txt = scanner.nextLine();
                
                File file = new File(txt);
                DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
                byte[] bs = new byte[512];
                
                dataOutputStream.writeBytes(txt + "\n");
            }
        }
    }
}
