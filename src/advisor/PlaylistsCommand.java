package advisor;

import com.google.gson.JsonParser;

import java.net.http.HttpResponse;

public class PlaylistsCommand implements Command {
    private CommandManager cm;
    private String categoryID;
    private String APIendpoint;

    public PlaylistsCommand(CommandManager cm) {
        this.cm = cm;

    }
    @Override
    public String getName() {
        return "playlists";
    }

    @Override
    public String getAPIendpoint() {
        return APIendpoint;
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
        this.categoryID = cm.findID(args[0]);
        this.APIendpoint = "/v1/browse/categories/"+ categoryID + "/playlists";

        if (cm.isAuthorized()) {
            HttpResponse<String> response = cm.httpRequest(APIendpoint);
            if (response.statusCode() == 404) {
                String errorMSG = JsonParser.parseString(response.body()).getAsJsonObject()
                        .get("error").getAsJsonObject()
                        .get("message").getAsString();
                System.out.println(errorMSG);
                return;
            }
            System.out.println(response.body());
        } else {
            System.out.println("Please, provide access for application.");
        }
    }
}