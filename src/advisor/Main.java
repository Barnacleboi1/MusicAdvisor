package advisor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length > 1 && args[0].equals("-access")) {
            Authorisation.SERVER_PATH = args[1];
        }
        CommandManager cm = new CommandManager();
        while (!cm.isFinished()) {
            cm.read();
        }
    }
}
