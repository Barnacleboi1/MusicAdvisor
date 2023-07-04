package advisor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CommandManager cm = new CommandManager();
        while (!cm.isFinished()) {
            cm.read();
        }
    }
}
