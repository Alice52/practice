package top.hubby.principles.lsp.after;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zack <br>
 * @create 2021-09-13<br>
 * @project pattern <br>
 */
public interface Quadrilateral {
    Logger log = LoggerFactory.getLogger(Quadrilateral.class);

    double getLength();

    double getWidth();

    /**
     * 打印长方形的长和宽
     *
     * @param rectangle
     */
    default void printLengthAndWidth(Quadrilateral rectangle) {
        log.info("{}", rectangle.getLength());
        log.info("{}", rectangle.getWidth());
    }
}
