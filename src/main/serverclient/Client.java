package main.serverclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        try(Socket socket = new Socket("localhost",5000)) {
            PrintWriter out = new PrintWriter
                    (socket.getOutputStream(), true);

            BufferedReader in = new BufferedReader
                    (new InputStreamReader(socket.getInputStream()));

            System.out.println("Connected to the server");
            out.println("Hello server!");
            String response = in.readLine();
            System.out.println("Server response: " + response);
        } catch (IOException e){
            System.out.println("Client failed to connect" + e.getMessage());
        }

    }
}
