package top.hubby.principles.lod.v2.after.model;

import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-12-21 2:44 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class Document {
    private Html html;
    private String url;

    public Document(String url, Html html) {
        this.html = html;
        this.url = url;
    }
}
