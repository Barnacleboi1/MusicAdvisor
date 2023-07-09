package advisor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Set;

public class CategoriesCommand implements Command{
    private CommandManager cm;
    private String APIendpoint;

    public CategoriesCommand(CommandManager cm) {
        this.cm = cm;
        APIendpoint = "/v1/browse/categories";
    }
    @Override
    public String getName() {
        return "categories";
    }

    @Override
    public void execute() {
        if (cm.isAuthorized()) {
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
                return;
            }
            Set<String> categoriesSet = CategoriesAndIDFinder.getCategoriesSet(response.body());
            for (String s : categoriesSet) {
                System.out.println(s);
            }
        } else {
            System.out.println("Please, provide access for application.");
        }
    }
    @Override
    public String getAPIendpoint() {
        return APIendpoint;
    }
}