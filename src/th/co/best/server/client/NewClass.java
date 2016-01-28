/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.best.server.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author BestKung
 */
public class NewClass {

    public static void main(String[] args) throws FileNotFoundException, IOException {

//        ServerSocket serverSocket = new ServerSocket(7000);
//       
//        while(true){
//             Socket socket = serverSocket.accept();
//        if(socket.isConnected()){
//            System.out.println("Accept");
//             String fileName = "F:\\\\Hi.txt";
//        File file = new File(fileName);
//        DataOutputStream outputStream1 = new DataOutputStream(new FileOutputStream(file));
//        }
//        }
//===========================================================================
//        String path = "E:\\test.txt";
//        String b = "";
//        byte[] content = new byte[1024];
//        File file = new File(path);
//        FileReader fileReader = new FileReader(file);
//        System.out.println(file);
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//        System.out.println(bufferedReader.readLine());
//        while ((b = bufferedReader.readLine()) != null) {
//            System.out.println(b);
//        }
//=============================================================================
String path  = "ygvsyvfsygfsfg/sukgh/fusef/fhsu/test.txt";

String[] str = path.split("/");

for(String s : str){
    System.out.println(s);
}

        System.out.println(str[str.length-1]);

    }
}
