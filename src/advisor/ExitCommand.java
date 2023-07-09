package advisor;

public class ExitCommand implements Command {
    private CommandManager cm;

    public ExitCommand(CommandManager cm) {
        this.cm = cm;
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getAPIendpoint() {
        return null;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 0) {
            System.out.println("This command does not have arguments");
            return;
        }
        System.out.println("---GOODBYE!---");
        cm.setFinished(true);
    }
}