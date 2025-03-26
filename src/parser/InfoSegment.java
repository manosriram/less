package parser;

public class InfoSegment {
    String title;
    String author;
    String published_at;

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedAt() {
        return published_at;
    }

    public void setPublishedAt(String published_at) {
        this.published_at = published_at;
    }
}