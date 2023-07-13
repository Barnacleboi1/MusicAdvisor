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
}
