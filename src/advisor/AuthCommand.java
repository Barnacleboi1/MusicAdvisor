package advisor;

import java.util.List;

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
    public List<Output> execute(String[] args) {
        if (args.length > 0) {
            System.out.println("This command does not have arguments");
            return null;
        }
        Authorisation auth = new Authorisation();
        auth.getAccessCode();
        auth.getAccessToken();

        cm.setAuthorized(true);
        cm.getCategoriesList((cm.httpRequest("/v1/browse/categories").body()));
        System.out.println("---SUCCESS---");
        return null;
    }
}