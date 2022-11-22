package top.hubby.principles.lod.v2.before;

import lombok.extern.slf4j.Slf4j;
import top.hubby.principles.lod.v2.before.model.Html;
import top.hubby.principles.lod.v2.before.model.HtmlRequest;

/**
 * @author asd <br>
 * @create 2021-12-21 2:44 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class HtmlDownloader {
    private NetworkTransporter transporter; // 通过构造函数或IOC注入

    public Html downloadHtml(String url) {
        Byte[] rawHtml = transporter.send(new HtmlRequest(url));
        return new Html(rawHtml);
    }
}
