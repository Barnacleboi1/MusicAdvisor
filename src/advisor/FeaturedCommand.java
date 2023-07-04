package advisor;

public class FeaturedCommand implements Command{
    @Override
    public String getName() {
        return "featured";
    }

    @Override
    public void execute() {
        System.out.println("""
                                ---FEATURED---
                                Mellow Morning
                                Wake Up and Smell the Coffee
                                Monday Motivation
                                Songs to Sing in the Shower""");
    }
}
