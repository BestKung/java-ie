/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.best.gui;


import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.JTextPane;

/**
 *
 * @author BestKung
 */
public class ManageFile {

    public String sendFile(String fileName, DataInputStream inputFile, DataOutput send) {
        File file = new File(fileName);
        try {
            inputFile = new DataInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = inputFile.read(buffer)) != -1) {
                send.write(buffer, 0, len);
            }
            return "Send Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Send Faile";
        }
    }

    public String reseivedFile(String fileName, DataOutputStream saveFile, DataInputStream inputFile) {
        File file = new File(fileName);
        try {
            saveFile = new DataOutputStream(new FileOutputStream(file));
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = inputFile.read(buffer)) != 1) {
                saveFile.write(buffer, 0, len);
                System.out.println(len);
                if (len < 1024) {
                    break;
                }
            }
            return "Save Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Save Faile";
        }
    }

    public static String findName(String fileName) {
        String name = "";
        for (int i = 0; i < fileName.length(); i++) {
            char c = fileName.charAt(i);
            if (c == '/') {
                name = "";
                continue;
            }
            name += c;
        }
        return name;
    }

}
