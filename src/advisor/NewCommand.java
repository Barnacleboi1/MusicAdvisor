package advisor;

public class NewCommand implements Command{
    private CommandManager cm;

    public NewCommand(CommandManager cm) {
        this.cm = cm;
    }
    @Override
    public String getName() {
        return "new";
    }

    @Override
    public String getAPIendpoint() {
        return "/v1/browse/new-releases";
    }

    @Override
    public void execute() {
        if (cm.isAuthorized()) {
            System.out.println("""
                    ---NEW RELEASES---
                    Mountains [Sia, Diplo, Labrinth]
                    Runaway [Lil Peep]
                    The Greatest Show [Panic! At The Disco]
                    All Out Life [Slipknot]""");
        } else {
            System.out.println("Please, provide access for application.");
        }
    }
}