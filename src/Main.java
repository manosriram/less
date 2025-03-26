import generator.Generator;
import parser.Article;
import parser.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

class Main {
    public static void main(String[] args) throws IOException {
        Parser p = new Parser("content");
        Generator g = new Generator();
        List<Article> articles = p.Parse();

        new File("dist").mkdir();

        FileWriter indexWriter = new FileWriter("dist/index.html");
        for (Article article: articles) {
            System.out.println(Objects.equals(article.infosegment.getTitle(), "homepage"));
            if (Objects.equals(article.infosegment.getTitle(), "homepage")) {
                indexWriter.write(g.IndexHtmlTemplate().replace("{content}", article.contentsegment.getContent()));
            } else {
                FileWriter writer = new FileWriter(String.format("dist/%s.html", article.infosegment.getTitle()));
                writer.write(g.ArticleTemplate().replace("{title}", article.infosegment.getTitle()).replace("{content}", article.contentsegment.getContent()).replace("{published_at}", article.infosegment.getPublishedAt()).replace("{author}", article.infosegment.getAuthor()));
                indexWriter.append(String.format("<p><a href='%s.html'>%s</a> (%s)</p>", article.infosegment.getTitle(), article.infosegment.getTitle(), article.infosegment.getAuthor()));
                writer.close();
            }
        }
        indexWriter.close();
    }
}