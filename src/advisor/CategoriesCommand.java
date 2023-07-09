package advisor;

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
            HttpResponse<String> response = cm.httpRequest(APIendpoint);

            Set<String> categoriesSet = cm.getCategoriesSet(response.body());
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