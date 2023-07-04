package advisor;

public class FeaturedCommand implements Command{
    private CommandManager cm;

    public FeaturedCommand(CommandManager cm) {
        this.cm = cm;
    }
    @Override
    public String getName() {
        return "featured";
    }

    @Override
    public void execute() {
        if (cm.isAuthorized()) {
            System.out.println("""
                    ---FEATURED---
                    Mellow Morning
                    Wake Up and Smell the Coffee
                    Monday Motivation
                    Songs to Sing in the Shower""");
        } else {
            System.out.println("Please, provide access for application.");
        }
    }
}
