package advisor;

public class PlaylistsCommand implements Command {
    private CommandManager cm;
    private String categoryID;
    private String APIendpoint;

    public PlaylistsCommand(CommandManager cm) {
        this.cm = cm;
        this.categoryID = "";
        this.APIendpoint = "/v1/browse/categories/{category_id}/playlists";
    }
    @Override
    public String getName() {
        return "playlists";
    }

    @Override
    public String getAPIendpoint() {
        return "/v1/browse/categories/{category_id}/playlists";
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the category name as an argument");
            return;
        }
        if (args.length > 1) {
            System.out.println("This command does not have arguments");
            return;
        }
        categoryID = CommandManager.findID(args[1]);
        if (cm.isAuthorized()) {
            String response = CommandManager.HttpRequest(APIendpoint).body();
            System.out.println(response);
        } else {
            System.out.println("Please, provide access for application.");
        }
    }
}