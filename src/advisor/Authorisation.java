package advisor;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Authorisation {
    public static String SERVER_PATH = "https://accounts.spotify.com";
    public static String REDIRECT_URI = "http://localhost:8080";
    public static String CLIENT_ID = "1001f7400e724ede9a1cd4a1914fe86f";
    public static String CLIENT_SECRET = "6c273a4bc860480fae15ce984825a3de";
    public static String ACCESS_TOKEN = "";
    public static String ACCESS_CODE = "";

    public void getAccessCode() {
        HttpServer server = null;
        String uri = SERVER_PATH + "/authorize"
                + "?client_id=" + CLIENT_ID
                + "&redirect_uri=" + REDIRECT_URI
                + "&response_type=code";
        System.out.println("use this link to request the access code:");
        System.out.println(uri);
        try {
            server = HttpServer.create();
            server.bind(new InetSocketAddress(8080), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        server.start();
        server.createContext("/",
                new HttpHandler() {
                    public void handle(HttpExchange exchange) throws IOException {
                        String query = exchange.getRequestURI().getQuery();
                        if (query != null && query.contains("code")) {
                            ACCESS_CODE = query.substring(5);
                            exchange.sendResponseHeaders(200, "Got the code. Return back to your program.".length());
                            exchange.getResponseBody().write("Got the code. Return back to your program.".getBytes());
                            exchange.getResponseBody().close();
                        } else {
                            exchange.sendResponseHeaders(200, "Authorization code not found. Try again.".length());
                            exchange.getResponseBody().write("Authorization code not found. Try again.".getBytes());
                            exchange.getResponseBody().close();
                        }

                    }
                }
        );
        System.out.println("waiting for code...");
        while (ACCESS_CODE.equals("")) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        server.stop(10);
    }
    public void getAccessToken() {
        
    }
}
