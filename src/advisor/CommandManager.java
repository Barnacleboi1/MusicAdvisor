package advisor;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CommandManager {
    private Set<Command> commands;
    private boolean authorized;
    private boolean finished;
    public static String APIurl = "https://api.spotify.com";
    public CommandManager() {
        authorized = false;
        finished = false;

        commands = new HashSet<>();
        commands.add(new AuthCommand(this));
        commands.add(new CategoriesCommand(this));
        commands.add(new ExitCommand(this));
        commands.add(new FeaturedCommand(this));
        commands.add(new NewCommand(this));
        commands.add(new PlaylistsCommand(this));
    }
    public void read() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        for (Command command : commands) {
            if (input.equalsIgnoreCase(command.getName())) {
                command.execute();
            }
        }
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isFinished() {
        return finished;
    }
}