/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dal.UserDAO;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import model.User;

/**
 *
 * @author admin
 */
public class ServerSocketHandle implements Runnable {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private User user;

    public ServerSocketHandle(Socket socket) {
        try {
            this.socket = socket;
            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            System.out.println(e);
        }        
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    

    
    // gửi gói tin đến Client
    public void write(String message) {
        String userName = "guess";
        if (user != null) userName = user.getName();
        System.out.println("Server to "+ userName +" : " + message);
        writer.println(message);
        writer.flush();
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            
            // khai bao đối tượng DAO 
            UserDAO udb = new UserDAO();
            
            // luôn lắng nghe Client
            while (true) {
                String receiveMessage = reader.readLine();
                String[] req = receiveMessage.split("\\$");
                String type = req[0];
                
                System.out.println("Client: " + receiveMessage);
                
                System.out.println(type);
                
            // phần này Khanh code
                
                //xu ly yeu cau dang nhap
                if (type.equals("login-request")) {
                    System.out.println("hello");
                    String name = req[1];
                    String password = req[2];
                    User u = udb.VerifyUser(name, password);
                    
//                    System.out.println(u.toString());
                    
                    if (u != null) {
                        write("login-response$success$" + u.toString());
                    } else {
                        write("login-response$fail");
                    }
                }
                
                if (type.equals("register-request")) {
                    
                }
                
            // phần này Yến code
                
            // Phần này Hải và Anh code
                
                
            }
        } catch (Exception e) {
        }

    }

}
