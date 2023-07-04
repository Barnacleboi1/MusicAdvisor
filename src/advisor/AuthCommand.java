package advisor;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class AuthCommand implements Command{
    private CommandManager cm;
    private String server;
    public AuthCommand(CommandManager cm) {
        this.cm = cm;
        this.server = server;
    }

    @Override
    public String getName() {
        return "auth";
    }

    @Override
    public void execute() {
        Authorisation auth = new Authorisation();
        auth.getAccessCode();


        cm.setAuthorized(true);
    }
}