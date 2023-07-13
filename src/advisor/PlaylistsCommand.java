package advisor;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class PlaylistsCommand implements Command {
    private CommandManager cm;
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
    public List<Output> execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the category name as an argument");
            return null;
        }

        String categoryID = cm.findID(String.join(" ", args));
        this.APIendpoint = "/v1/browse/categories/"+ categoryID + "/playlists";

        if (cm.isAuthorized()) {
            HttpResponse<String> response = cm.httpRequest(APIendpoint);
            if (response.body().contains("error")) {
                String errorMSG = JsonParser.parseString(response.body()).getAsJsonObject()
                        .get("error").getAsJsonObject()
                        .get("message").getAsString();
                System.out.println(errorMSG);
                return null;
            }

            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            List<Output> outputList = new ArrayList<>();
            for (JsonElement playlist : jsonResponse.getAsJsonObject("playlists").getAsJsonArray("items")) {
                Output o = new Output();
                o.setName(playlist.getAsJsonObject().get("name").getAsString().replace("\"", ""));
                o.setLink(playlist.getAsJsonObject()
                        .get("external_urls").getAsJsonObject()
                        .get("spotify").getAsString());
                outputList.add(o);
            }
            return outputList;
        } else {
            System.out.println("Please, provide access for application.");
            return null;
        }
    }
}