package advisor;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class AuthCommand implements Command{
    private CommandManager cm;
    public AuthCommand(CommandManager cm) {
        this.cm = cm;
    }


    @Override
    public String getName() {
        return "auth";
    }

    @Override
    public String getAPIendpoint() {
        return null;
    }

    @Override
    public void execute() {
        Authorisation auth = new Authorisation();
        auth.getAccessCode();
        auth.getAccessToken();

        cm.setAuthorized(true);
    }
}