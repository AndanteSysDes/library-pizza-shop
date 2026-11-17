package main.serverclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(5000)){
            System.out.println("Server started and waiting for client");
            try(Socket client = serverSocket.accept()) {

                BufferedReader in = new BufferedReader
                        (new InputStreamReader
                                (client.getInputStream()));
                PrintWriter out = new PrintWriter
                        (client.getOutputStream(),true);
                System.out.println("A client connected");
                String input = in.readLine();
                if (input != null) {
                    System.out.println("Client sent: " + input);
                    out.println("Message recieved");
                }
            } catch (IOException e){
                System.out.println("Client error" + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Server error" + e.getMessage());
        }





    }
}
