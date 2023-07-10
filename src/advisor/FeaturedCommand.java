package advisor;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.http.HttpResponse;

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
    public String getAPIendpoint() {
        return "/v1/browse/featured-playlists";
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 0) {
            System.out.println("This command does not have arguments");
            return;
        }
        if (cm.isAuthorized()) {
            HttpResponse<String> response = cm.httpRequest(getAPIendpoint());
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            for (JsonElement featured : jsonResponse.getAsJsonObject("playlists").getAsJsonArray("items")) {
                System.out.println(featured.getAsJsonObject().get("name").getAsString().replace("\"", ""));
                System.out.println(featured.getAsJsonObject()
                        .get("external_urls").getAsJsonObject()
                        .get("spotify").getAsString());
                System.out.println();
            }
        } else {
            System.out.println("Please, provide access for application.");
        }
    }
}
