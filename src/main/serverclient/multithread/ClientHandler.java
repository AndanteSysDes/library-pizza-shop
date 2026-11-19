package main.serverclient.multithread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final List<PrintWriter> clients;

    public ClientHandler(Socket socket, List<PrintWriter> clients) {
        this.socket = socket;
        this.clients = clients;
    }


    @Override
    public void run() {

        PrintWriter out = null;
        String name = "Okänd";

        try (Socket client = socket) {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);

            String userName = in.readLine();

            if (userName == null || userName.isBlank()) {
                return;
            }

            name = userName.trim();
            // forgot to add
            clients.add(out);

            out.println("Välkommen " + name + " Skriv /quit för att lämna chatten");

            System.out.println(name + " anslöts " +  " Online: " + clients.size());

            String userMessage;

            while ((userMessage = in.readLine()) != null) {

                if (userMessage.equalsIgnoreCase("/quit")) {
                    break;
                }
                for (PrintWriter reciever : clients) {
                    synchronized (reciever) {
                        reciever.println(name + " : " + userMessage);
                    }
                }
            }
        }catch (IOException e){
            System.out.println("Client error: " + e.getMessage());
        } finally {
            if (out != null){
                clients.remove(out);
            }
        }
        System.out.println(name + " Lämnade chatten " + " Online just nu: " + clients.size());
    }
}
