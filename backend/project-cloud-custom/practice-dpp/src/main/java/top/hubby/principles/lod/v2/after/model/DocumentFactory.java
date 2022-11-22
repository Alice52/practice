package top.hubby.principles.lod.v2.after.model;

import lombok.extern.slf4j.Slf4j;
import top.hubby.principles.lod.v2.after.HtmlDownloader;

/**
 * 入口
 *
 * @author asd <br>
 * @create 2021-12-21 3:06 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class DocumentFactory {
    private HtmlDownloader downloader;

    public DocumentFactory(HtmlDownloader downloader) {
        this.downloader = downloader;
    }

    public Document createDocument(String url) {
        Html html = downloader.downloadHtml(url);
        return new Document(url, html);
    }
}
