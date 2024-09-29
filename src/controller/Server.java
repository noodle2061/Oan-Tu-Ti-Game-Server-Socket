/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class Server {

    public static List<ServerSocketHandle> listSocket = new ArrayList<>();

    public Server() {
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9989);
            while (true) {
                Socket socket= serverSocket.accept();
                ServerSocketHandle serverSocketHandle = new ServerSocketHandle(socket);
                listSocket.add(serverSocketHandle);
                serverSocketHandle.run();
            }
        } catch (Exception e) {
        }

    }
}
