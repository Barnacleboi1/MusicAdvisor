package advisor;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

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
    public List<Output> execute(String[] args) {
        if (args.length > 0) {
            System.out.println("This command does not have arguments");
            return null;

        }
        if (cm.isAuthorized()) {
            HttpResponse<String> response = cm.httpRequest(APIendpoint);
            List<String> categoriesList = cm.getCategoriesList(response.body());
            List<Output> outputList = new ArrayList<>();
            for (int i = 0; i < categoriesList.size(); i++) {
                Output o = new Output();
                o.setCategory(categoriesList.get(i));
                outputList.add(o);
            }
            return outputList;
        } else {
            System.out.println("Please, provide access for application.");
            return null;
        }
    }
    @Override
    public String getAPIendpoint() {
        return APIendpoint;
    }
}