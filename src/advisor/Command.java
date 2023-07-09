package advisor;

public interface Command {
    String getName();
    String getAPIendpoint();
    void execute();
}
