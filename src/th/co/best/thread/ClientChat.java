/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.best.thread;

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
public class ClientChat implements Runnable {

    private Socket socket;
    private String input, output;
    private Thread sendMessage, showMessage;
    private DataOutputStream send;
    private BufferedReader reseived;
    private DataInputStream inputFile;

    public ClientChat(String ip, int port) throws IOException {
        System.out.println("If You want to send file please input \'f\' \n==========================================================================");
        showMessage = new Thread(this);
        sendMessage = new Thread(this);
        socket = new Socket(ip, port);
        inputFile = new DataInputStream(socket.getInputStream());
        sendMessage.start();
        showMessage.start();
    }

    @Override
    public void run() {
        try {
            if (Thread.currentThread() == sendMessage) {
                while (true) {
                    send = new DataOutputStream(socket.getOutputStream());
                    Scanner scanner = new Scanner(System.in);
                    input = scanner.nextLine();
                    send.writeBytes(input + "\n");
                    if (input.equalsIgnoreCase("f")) {
                        System.out.print("Upload file : ");
                        String fileName = scanner.nextLine();
                        send.writeBytes(fileName + "\n");
                        System.out.println(new ManageFile().sendFile(fileName, inputFile, send));
                        continue;
                    }
                }
            } else {
                while (true) {
                    reseived = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    output = reseived.readLine();
                    if ((output.charAt(0) == 'f' || output.charAt(0) == 'F') && output.length() == 1) {
                        String fileName = reseived.readLine();
                        System.out.println(fileName);
                        fileName = "/Users/engineer/Desktop/tmpFile/" + new ManageFile().findName(fileName);
                        System.out.println("File Upload From Server : " + fileName);
                        System.out.println(new ManageFile().reseivedFile(fileName, send, inputFile));

                    } else {
                        System.out.println("Server : " + output);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input ip : ");
        String ip = scanner.nextLine();
        System.out.print("Input port number : ");
        int port = scanner.nextInt();
        ClientChat clientChat = new ClientChat(ip, port);
    }

}
