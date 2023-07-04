package advisor;

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
    public void execute() {
        System.out.println("https://accounts.spotify.com/authorize?client_id=a19ee7dbfda443b2a8150c9101bfd645&redirect_uri=http://localhost:8080&response_type=code\n");
        System.out.println("---SUCCESS---");
        cm.setAuthorized(true);
    }
}