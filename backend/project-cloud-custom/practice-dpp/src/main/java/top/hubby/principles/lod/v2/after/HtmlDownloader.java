package top.hubby.principles.lod.v2.after;

import lombok.extern.slf4j.Slf4j;
import top.hubby.principles.lod.v2.after.model.DocumentFactory;
import top.hubby.principles.lod.v2.after.model.Html;
import top.hubby.principles.lod.v2.after.model.HtmlRequest;

/**
 * @author asd <br>
 * @create 2021-12-21 2:44 PM <br>
 * @project pattern <br>
 * @see DocumentFactory
 */
@Slf4j
public class HtmlDownloader {
    private NetworkTransporter transporter; // 通过构造函数或IOC注入

    public Html downloadHtml(String url) {
        HtmlRequest htmlRequest = new HtmlRequest(url);
        Byte[] rawHtml =
                transporter.send(htmlRequest.getAddress(), htmlRequest.getContent().getBytes());
        return new Html(rawHtml);
    }
}
