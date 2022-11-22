package top.hubby.principles.lod.v2.before.model;

import lombok.extern.slf4j.Slf4j;
import top.hubby.principles.lod.v2.before.HtmlDownloader;

/**
 * 入口: 不该有直接依赖关系的类之间，不要有依赖
 *
 * <pre>
 *     1. 构造函数中不应该有复杂逻辑: 影响代码的可测试性
 *     2. HtmlDownloader 不应该 new 来创建: 违反了基于接口而非实现编程的设计思想
 *     3. 没必要依赖 HtmlDownloader 类: 违背了迪米特法则
 *
 *     解决: 改成纯粹的 Document 对象, 那么怎么才能得到 Document[html+url] 对象呢?
 *          - 只有 HtmlDownloader 这一种途径
 *          - 所以初始化的时候就需要得到 html, 且需要应用到所有的 Document 创建过程中
 *          - 那么获取 Document 就被分成了2步: 获取 html + 创建 Document {这就是工厂方法}
 *          - DocumentFactory.create(url): HtmlDownloader + new Document
 * </pre>
 *
 * @author asd <br>
 * @create 2021-12-21 2:44 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class Document {
    private Html html;
    private String url;

    public Document(String url) {
        this.url = url;
        HtmlDownloader downloader = new HtmlDownloader();
        this.html = downloader.downloadHtml(url);
    }
}
