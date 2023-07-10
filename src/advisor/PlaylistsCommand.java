package advisor;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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

        this.categoryID = cm.findID(String.join(" ", args));
        this.APIendpoint = "/v1/browse/categories/"+ categoryID + "/playlists";

        if (cm.isAuthorized()) {
            HttpResponse<String> response = cm.httpRequest(APIendpoint);
            if (response.body().contains("error")) {
                String errorMSG = JsonParser.parseString(response.body()).getAsJsonObject()
                        .get("error").getAsJsonObject()
                        .get("message").getAsString();
                System.out.println(errorMSG);

                return;
            }
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            for (JsonElement playlist : jsonResponse.getAsJsonObject("playlists").getAsJsonArray("items")) {
                System.out.println(playlist.getAsJsonObject().get("name").getAsString().replace("\"", ""));
                System.out.println(playlist.getAsJsonObject()
                        .get("external_urls").getAsJsonObject()
                        .get("spotify").getAsString());
                System.out.println();

            }
        } else {
            System.out.println("Please, provide access for application.");
        }
    }
}