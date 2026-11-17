package main.serverclient;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Main implements HttpHandler {

    HashMap<String, String> users = new HashMap<>();
    public static void main(String[] args) throws IOException {


        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 5000),0);

        server.createContext("/", new Main());

        server.start();
        System.out.println("Server started");
        System.out.println("Server running on http://localhost:5000/");

    }
    @Override
    public synchronized void handle(HttpExchange  exchange) throws IOException {

        String path = exchange.getRequestURI().getPath();

        String id = getParam(exchange, "id");
        String name = getParam(exchange, "name");


        System.out.println("1");
        String response = "";

        if (path.equals("/add") && id != null && name != null){

            System.out.println("2");

            if (users.containsKey(id)){
                response = "ID finns redan";
            } else {
                users.put(id,name);
                response = "User added";
            }

        }

        byte[] data = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, data.length);


        try (OutputStream os = exchange.getResponseBody()) {
            os.write(data);
        }



    }


    private String getParam(HttpExchange exchange, String key) {
        String query = exchange.getRequestURI().getRawQuery();

        if (query == null) {
            return null;
        }
        for (String part: query.split("&")) {
            String[] pair = part.split("=", 2);
            if (pair.length == 2 && pair[0].equals(key)) {
                return URLDecoder.decode(pair[1], StandardCharsets.UTF_8);
            }
        }
        return null;

    }
}

