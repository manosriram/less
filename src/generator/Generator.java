package generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Generator {
    public String IndexHtmlTemplate() throws FileNotFoundException {
        File f = new File("/Users/manosriram/dev/staticsitegenerator/static_site_generator/src/generator/IndexGenerator.html");
        Scanner s = new Scanner(f);

        String content = s.nextLine();
        s.close();
        return content;
    }

    public String ArticleTemplate() throws FileNotFoundException {
        File f = new File("/Users/manosriram/dev/staticsitegenerator/static_site_generator/src/generator/ArticleTemplateGenerator.html");
        Scanner s = new Scanner(f);

        String content = s.nextLine();
        s.close();
        return content;
    }
}