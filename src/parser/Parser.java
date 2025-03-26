package parser;

import java.io.File;
import java.util.*;

public class Parser {
    String path;
    public Parser(String path) {
        this.path = path;
    }

    public Article ParseDir(String filepath) {
        Article a = new Article(filepath);
        return a.Build();
    }

    public List<Article> Parse() {
        List<Article> parsedContent = new ArrayList<>();
        File f = new File(this.path);
        for (File file : Objects.requireNonNull(f.listFiles())) {
            Article a = ParseDir(file.getAbsolutePath());
            parsedContent.add(a);
        }
        return parsedContent;
    }
}