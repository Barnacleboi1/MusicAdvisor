package advisor;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class NewCommand implements Command{
    private CommandManager cm;

    public NewCommand(CommandManager cm) {
        this.cm = cm;
    }
    @Override
    public String getName() {
        return "new";
    }

    @Override
    public String getAPIendpoint() {
        return "/v1/browse/new-releases";
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

            for (JsonElement item : jsonResponse.getAsJsonObject("albums").getAsJsonArray("items")) {
                StringBuilder artists = new StringBuilder();
                Output o = new Output();
                for (JsonElement elem : item.getAsJsonObject().get("artists").getAsJsonArray()) {
                    artists.append(elem.getAsJsonObject().get("name").getAsString()).append(", ");
                }
                o.setName(item.getAsJsonObject().get("name").getAsString().replace("\"", ""));
                o.setArtists("[" + artists.substring(0, artists.length() - 2) + "]");
                o.setLink(item.getAsJsonObject()
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