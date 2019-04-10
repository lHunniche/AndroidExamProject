package dk.danskpilsner.amiibo.models;

public class Amiibo {

    private String amiiboSeries;
    private String character;
    private String gameSeries;
    private String head;
    private String image;
    private String name;
    private Release release;
    private String tail;
    private String type;



    public String getAmiiboSeries() {
        return amiiboSeries;
    }

    public void setAmiiboSeries(String amiiboSeries) {
        this.amiiboSeries = amiiboSeries;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getGameSeries() {
        return gameSeries;
    }

    public void setGameSeries(String gameSeries) {
        this.gameSeries = gameSeries;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
