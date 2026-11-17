package main.serverclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class Server {
    public void server() {


        Path file = Path.of("messages.txt");

        try(ServerSocket serverSocket = new ServerSocket(5000)){
            System.out.println("Server started and waiting for client");

            while (true){

                try(Socket client = serverSocket.accept()) {

                    BufferedReader in = new BufferedReader
                            (new InputStreamReader
                                    (client.getInputStream()));
                    PrintWriter out = new PrintWriter
                            (client.getOutputStream(),true);
                    System.out.println("A client connected");
                    String message;
                        while ((message = in.readLine()) != null){

                            String response = Protocol.respond(message);

                            System.out.println("Message recieved from client: " + response);
                                if (response.equalsIgnoreCase("exit")){
                                    out.println("Byee");
                                    return;
                                }

                            Files.writeString(
                                    file,
                                    message
                            );

                        }


                    System.out.println("Message saved");

                } catch (IOException e) {
                System.out.println("Client error " + e.getMessage());
            }
            }
        } catch (IOException e) {
            System.out.println("Server error " + e.getMessage());
        }
    }
}