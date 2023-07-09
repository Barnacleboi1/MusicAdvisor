package advisor;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class CommandManager {
    private Set<Command> commands;
    public Map<String, String> categoriesMap = new HashMap<>();

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
        String[] input = scanner.nextLine().split(" ");
        for (Command command : commands) {
            if (input[0].equalsIgnoreCase(command.getName())) {
                command.execute(Arrays.copyOfRange(input, 1, input.length));
                return;
            }
        }
        System.out.println("This command does not exist");
    }

    public String findID(String categoryName) {
        String id = categoriesMap.get(categoryName);

        return id;
    }
    public Set<String> getCategoriesSet(String jsonString) {
        JsonObject jo = JsonParser.parseString(jsonString).getAsJsonObject();
        for (JsonElement item : jo.getAsJsonObject("categories").getAsJsonArray("items")) {
            categoriesMap.put(item.getAsJsonObject().get("name").getAsString(), item.getAsJsonObject().get("id").getAsString());
        }
        return new HashSet<>(categoriesMap.keySet());
    }
    public  HttpResponse<String> httpRequest(String APIendpoint) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + Authorisation.ACCESS_TOKEN)
                .uri(URI.create(CommandManager.APIurl + APIendpoint))
                .GET()
                .build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response;
        try {
            response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

        return response;
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