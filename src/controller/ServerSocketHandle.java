/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author admin
 */
public class ServerSocketHandle implements Runnable {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public ServerSocketHandle(Socket socket) {
        try {
            this.socket = socket;
            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            System.out.println(e);
        }        
    }
    
    // gửi gói tin đến Client
    public void write(String message) {
        writer.println(message);
        writer.flush();
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            
            // luôn lắng nghe Client
            while (true) {
                String receiveMessage = reader.readLine();
                String[] req = receiveMessage.split(" ");
                
                
            }
        } catch (Exception e) {
        }

    }

}
