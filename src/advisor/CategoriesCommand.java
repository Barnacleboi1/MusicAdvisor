package advisor;

public class CategoriesCommand implements Command{
    private CommandManager cm;

    public CategoriesCommand(CommandManager cm) {
        this.cm = cm;
    }
    @Override
    public String getName() {
        return "categories";
    }

    @Override
    public void execute() {
        if (cm.isAuthorized()) {
            System.out.println("""
                    ---CATEGORIES---
                    Top Lists
                    Pop
                    Mood
                    Latin""");
        } else {
            System.out.println("Please, provide access for application.");
        }
    }
}