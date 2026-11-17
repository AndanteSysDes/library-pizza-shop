package main.serverclient;

public class Protocol {


    public static String respond(String message){

        if (message == null || message.isBlank()){
            return "ERROR empty command";
        }

        switch (message){
            case "PING":
                return "PONG";

            case "EXIT":
                return "BYE";

            default:
                return "ERROR unknown command";
        }
    }
}
