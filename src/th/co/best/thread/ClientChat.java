/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.best.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author BestKung
 */
public class ClientChat {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1111);
       InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader in = new BufferedReader(inputStreamReader);
        System.out.println("Server Respon" + in.readLine());
    }
}
