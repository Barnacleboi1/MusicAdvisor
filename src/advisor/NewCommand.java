package advisor;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
    public void execute(String[] args) {
        if (args.length > 0) {
            System.out.println("This command does not have arguments");
            return;
        }
        if (cm.isAuthorized()) {
            HttpResponse<String> response = cm.httpRequest(getAPIendpoint());
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();

            for (JsonElement item : jsonResponse.getAsJsonObject("albums").getAsJsonArray("items")) {
                StringBuilder artists = new StringBuilder();
                for (JsonElement elem : item.getAsJsonObject().get("artists").getAsJsonArray()) {
                    artists.append(elem.getAsJsonObject().get("name").getAsString()).append(", ");
                }
                System.out.println(item.getAsJsonObject().get("name").getAsString().replace("\"", ""));
                System.out.println("[" + artists.substring(0, artists.length() - 2) + "]");
                System.out.println(item.getAsJsonObject()
                        .get("external_urls").getAsJsonObject()
                        .get("spotify").getAsString());
                System.out.println();


            }
        } else {
            System.out.println("Please, provide access for application.");
        }
    }
}