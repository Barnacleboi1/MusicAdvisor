package advisor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
    public void execute(String[] args) {
        if (args.length > 0) {
            System.out.println("This command does not have arguments");
            return;
        }
        if (cm.isAuthorized()) {
            HttpResponse<String> response = CommandManager.HttpRequest(APIendpoint);
            if (response.statusCode() == 404) {
                String errorMSG = JsonParser.parseString(response.body()).getAsJsonObject().get("message").getAsString();
                System.out.println(errorMSG);
                return;
            }
            Set<String> categoriesSet = CommandManager.getCategoriesSet(response.body());
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