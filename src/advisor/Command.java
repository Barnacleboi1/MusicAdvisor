package advisor;

import java.util.List;

public interface Command {
    String getName();
    String getAPIendpoint();
    List<Output> execute(String[] args);
}
