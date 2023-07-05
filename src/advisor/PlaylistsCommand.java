package advisor;

public class PlaylistsCommand implements Command {
    private CommandManager cm;

    public PlaylistsCommand(CommandManager cm) {
        this.cm = cm;
    }
    @Override
    public String getName() {
        return "playlists mood";
    }

    @Override
    public void execute() {
        if (cm.isAuthorized()) {
            System.out.println("""
                    ---MOOD PLAYLISTS---
                    Walk Like A Badass \s
                    Rage Beats \s
                    Arab Mood Booster \s
                    Sunday Stroll""");
        } else {
            System.out.println("Please, provide access for application.");
        }
    }
}