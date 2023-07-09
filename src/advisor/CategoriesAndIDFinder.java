package advisor;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashSet;
import java.util.Set;

public class CategoriesAndIDFinder {
    public static Set<String> categoriesSet = new HashSet<>();
    public static String findID(String name) {
        return null;
    }
    public static Set<String> getCategoriesSet(String jsonString) {
        JsonObject jo = JsonParser.parseString(jsonString).getAsJsonObject();
        Set<String> categories = new HashSet<>();

        for (JsonElement item : jo.getAsJsonObject("categories").getAsJsonArray("items")) {
            categories.add(item.getAsJsonObject().get("name").getAsString());
        }
        categoriesSet = categories;
        return categories;
    }
}
