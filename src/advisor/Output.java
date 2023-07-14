package advisor;

public class Output {
    private String name = null;
    private String category = null;
    private String link = null;
    private String artists = null;

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setArtists(String artists) {this.artists = artists;}

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getLink() {
        return link;
    }

    public String getArtists() {
        return artists;
    }

    @Override
    public String toString() {
        String nameString = name == null ? "" : name + "\n";
        String categoryString = category == null ? "" : category;
        String artistString = artists == null ? "" : artists + "\n";
        String linkString = link == null ? "" : link + "\n";

        return nameString + categoryString + artistString + linkString + "\n";
    }
}
