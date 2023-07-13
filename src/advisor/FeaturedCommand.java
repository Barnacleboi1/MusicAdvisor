package advisor;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

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
    public List<Output> execute(String[] args) {
        if (args.length > 0) {
            System.out.println("This command does not have arguments");
            return null;
        }
        if (cm.isAuthorized()) {
            HttpResponse<String> response = cm.httpRequest(getAPIendpoint());
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            List<Output> outputList = new ArrayList<>();
            for (JsonElement featured : jsonResponse.getAsJsonObject("playlists").getAsJsonArray("items")) {
                Output o = new Output();
                o.setName(featured.getAsJsonObject().get("name").getAsString().replace("\"", ""));
                o.setLink(featured.getAsJsonObject()
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
