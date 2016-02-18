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
        Socket socket = new Socket("localhost", 3333);
        String modifierSentence = null;
        BufferedReader bufferedReader = null;
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;
        
        try {
             while (true) {
            String fileName = "";
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("Upload File.");
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner scanner = new Scanner(System.in);
            System.out.print("Input Path File : ");
            fileName = scanner.nextLine();

            System.out.println(fileName);
            dataOutputStream.writeBytes(fileName + "\n");
            modifierSentence = bufferedReader.readLine();
            System.out.println("From Server" + modifierSentence);

         
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            File file = new File(fileName);
            System.out.println(file);
            System.out.println("th.co.best.server.client.ClientTest.main()");
            dataInputStream = new DataInputStream(new FileInputStream(file));

            System.out.println("th.co.best.server.client.ClientTest.main()");
            System.out.println();
            byte[] buffer = new byte[512];
            int len = -1;
            while ((len = dataInputStream.read(buffer)) != -1) {
                dataOutputStream.write(buffer, 0, len);
            }
        }
        } finally{
            socket.close();
        bufferedReader.close();
        dataInputStream.close();
        dataOutputStream.close();
        }
    }
}
