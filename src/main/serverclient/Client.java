package main.serverclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        try(Socket socket = new Socket("localhost",5000)) {
            PrintWriter out = new PrintWriter
                    (socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader
                    (new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);
            System.out.println("Connected to the server");

            while (true){
                System.out.println("Write a message");
                String message = scanner.nextLine();
                out.println(message);
                String response = in.readLine();

                if (response == null){
                    System.out.println("Server error");
                    break;
                }
                /*

                if (response.equalsIgnoreCase("Byee")){

                    break;
                }

                 */

            }
        } catch (IOException e){
            System.out.println("Client failed to connect" + e.getMessage());
        }

    }
}
