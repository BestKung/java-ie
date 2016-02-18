/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.best.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author BestKung
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);
        Socket socket = null;
        DataOutputStream dataOutputStream = null;
        BufferedReader bufferedReader = null;

        while (true) {
            String message = "";
            socket = serverSocket.accept();
            if (socket.isConnected()) {
                System.out.println("Client Accept..");
            }
            while (true) {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                message = bufferedReader.readLine();
                System.out.println("From Client : " + message);
            }
        }
    }
}
