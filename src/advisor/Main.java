package advisor;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-access")) {
                Authorisation.SERVER_PATH = args[i + 1];
            } else if (args[i].equals("-resource")) {
                CommandManager.APIurl = args[i + 1];
            } else if (args[i].equals("-page")) {
                PagePrinter.entriesPerPage = Integer.parseInt(args[i + 1]);
            }
        }
        CommandManager cm = new CommandManager();
        PagePrinter printer = new PagePrinter();
        while (!cm.isFinished()) {
            printer.print(cm.read());
        }
    }
}
