/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.best.thread;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BestKung
 */
public class ServerChat implements Runnable {

    ServerSocket serverSocket = null;
    Socket socket = null;
    DataOutputStream send = null;
    BufferedReader reseived = null;
    Thread showMessage = null, sendMessage = null;
    String input = "", output = "";

    public ServerChat() {
        try {
            serverSocket = new ServerSocket(1111);
            System.out.println(".....................Server Start.......................");
            InetAddress address = InetAddress.getLocalHost();
            System.out.println("Server open on port number : "+serverSocket.getLocalPort()  + " Server IP : "+address);
            System.out.println("...................Server is running......................");
            socket = serverSocket.accept();

            showMessage = new Thread(this);
            sendMessage = new Thread(this);

        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        try {
            if (Thread.currentThread() == showMessage) {
                while (true) {
                    send = new DataOutputStream(socket.getOutputStream());
                    Scanner scanner = new Scanner(System.in);
                    input = scanner.nextLine();
                    send.writeBytes(input + "\n");
                }
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) throws IOException {
        ServerChat chat = new ServerChat();
    }

}
