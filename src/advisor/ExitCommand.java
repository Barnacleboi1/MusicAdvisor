package advisor;

import java.util.List;

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
    public List<Output> execute(String[] args) {
        if (args.length > 0) {
            System.out.println("This command does not have arguments");
            return null;
        }
        cm.setFinished(true);
        System.out.println("---GOODBYE!---");
        return null;
    }
}