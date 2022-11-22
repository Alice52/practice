package top.hubby.principles.lod.v2.after;

import lombok.extern.slf4j.Slf4j;
import top.hubby.principles.lod.v2.after.model.Document;
import top.hubby.principles.lod.v2.after.model.DocumentFactory;

/**
 * @author asd <br>
 * @create 2021-12-21 3:21 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class TestClient {
    public static void main(String[] args) {
        DocumentFactory factory = new DocumentFactory(new HtmlDownloader());
        Document url = factory.createDocument("url");
    }
}
