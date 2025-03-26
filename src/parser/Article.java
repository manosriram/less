package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Article {
    public InfoSegment infosegment;
    public ContentSegment contentsegment;
    String path;
    StringBuilder fileContent;
    File f;
    Scanner reader;

    public Article(String path) {
        this.infosegment = new InfoSegment();
        this.contentsegment = new ContentSegment();
        this.path = path;
        this.fileContent = new StringBuilder();
        try {
            this.f = new File(path);
            this.reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        String s = "";
        s += this.infosegment.getAuthor() + "\n";
        s += this.infosegment.getTitle() + "\n";
        s += this.infosegment.getPublishedAt() + "\n";
        s += this.contentsegment.getContent() + "\n";
        return s;
    }

    public void BuiltInfoSegment() {
        if (!this.reader.hasNextLine() || (this.reader.hasNextLine() && !Objects.equals(this.reader.nextLine(), "---"))) {
            throw new RuntimeException("Incorrect article format");
        }

        while (this.reader.hasNextLine()) {
            String c = this.reader.nextLine();
            if (Objects.equals(c, "---")) {
                break;
            }
            fileContent.append(c).append('\n');
        }
        String[] x = fileContent.toString().split("\n");

        for (String z: x) {
            String[] line = z.split(":");
            if (line.length < 2) {
                throw new RuntimeException("Incorrect article format");
            }switch (line[0]) {
                case "title":
                    this.infosegment.setTitle(line[1].trim());
                    break;
                case "author":
                    this.infosegment.setAuthor(line[1].trim());
                    break;
                case "published_at":
                    this.infosegment.setPublishedAt(line[1].trim());
                    break;
            }
        }
    }

    public void BuiltContentSegment() {
        StringBuilder c = new StringBuilder();
        while (reader.hasNextLine()) {
            c.append(String.format("<p>%s</p>",reader.nextLine())).append("\n");
        }
        this.contentsegment.setContent(c.toString());
    }

    public Article Build() {
        BuiltInfoSegment();
        BuiltContentSegment();
        return this;
    }
}